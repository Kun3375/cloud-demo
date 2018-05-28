package com.kun.demo.service;

import com.kun.demo.entity.Dept;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.command.AbstractHystrixCommand;
import com.netflix.hystrix.contrib.javanica.command.GenericSetterBuilder;
import com.netflix.hystrix.contrib.javanica.command.HystrixCommandBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import rx.Observable;

import java.util.Collections;
import java.util.List;

/**
 * @author CaoZiye
 * @version 1.0 2018/5/7 23:03
 */
@Service
public class DeptServiceImpl implements DeptService {

    private static final Logger log = LoggerFactory.getLogger(DeptServiceImpl.class);
    private static final String DEPT_PROVIDER_URL_PREFIX = "http://CLOUD-PROVIDER" + "/dept";

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Long addOne(Dept dept) {
        HystrixCommandBuilder builder = HystrixCommandBuilder.builder()
                .setterBuilder(GenericSetterBuilder.builder()
                        .groupKey("dept")
                        .commandKey("add")
                        .build())
                .build();

        return new AbstractHystrixCommand<Long>(builder) {
            @Override
            protected Long run() {
                return restTemplate.postForObject(DEPT_PROVIDER_URL_PREFIX + "/one", dept, Long.class);
            }
        }.execute();

    }

    /*
     * @HystrixCommand 注解方法的全部功能都可以通过自定义类，并继承 HystrixCommand / HystrixObservableCommand 来实现
     * See AbstractDeptCommand.class / DeptObservableCommand.class
     */

    /*
     * 使用 @HystrixCommand 使方法加入熔断器，默认超时时间 2000ms，超时与失败会触发服务降级
     * fallbackMethod：指定备选方法，在服务降级时调用
     * defaultFallback：默认的备选方法
     * groupKey：指定方法的分组名称，用于统计报告等，默认方法所在（运行期）类名
     * commandKey：指定方法的命令名称，主要和缓存相关，不同的 commandKey 使用不同的缓存池，默认为方法名，command 实现时默认为类名
     * HystrixThreadPool：指定对应的进程池，在缺省的情况下使用 groupKey 作进程池区分
     * commandProperties：
     * threadPoolProperties：
     * observableExecutionMode：LAZY -> toObservable()，EAGER -> observable()
     * ignoreExceptions：指定忽略的异常，通常除了 HystrixRuntimeException，任何异常产生都会被判定为执行失败，执行服务降级逻辑。
     * raiseHystrixExceptions：对应上条，也可以指定触发服务降级的异常，默认情况下 RuntimeException 都会触发
     */

    /*
     * 同步执行：默认即同步执行，即 HystrixCommand.execute()
     * 异步执行：可以使用 Future<V> 作为返回值，通常地会使用匿名实现，如 spring 提供的 AsyncResult<V>
     * RxJava 响应式编程：使用 Observable<V> 作为返回值，observableExecutionMode 属性对应不同的调用
     */

    @Override
    @HystrixCommand(fallbackMethod = "queryOneDefault")
    public Dept queryOne(Long id) {
        // 用于测试
        if (id == 1L) {
            throw new RuntimeException("测试异常");
        }
        return restTemplate.getForObject(DEPT_PROVIDER_URL_PREFIX + "/one/{id}", Dept.class, id);
    }

    @Override
    @HystrixCommand(fallbackMethod = "queryAllDefault")
    public List<Dept> queryAll() {
        return restTemplate
                .exchange(
                        // url
                        DEPT_PROVIDER_URL_PREFIX + "/list",
                        // method
                        HttpMethod.GET,
                        // request body and header, could be null
                        RequestEntity.EMPTY,
                        // typeReference
                        new ParameterizedTypeReference<List<Dept>>() {})
                .getBody();
    }

    /*
     * fallbackMethod 必须与对应的 @HystrixCommand 在同一个类中
     * fallbackMethod 方法不在在乎其方法修饰符
     * fallbackMethod 必须和原方法签名一致，额外的，可以接收 Throwable 包含产生的异常对象
     * 如果方法作为 defaultFallback，不允许有任何参数，返回值必须兼容 @HystrixCommand 方法
     */

    private Dept queryOneDefault(Long id, Throwable t) {
        log.info("id: {}, a exception occurred: {}", id, t.getMessage());
        return new Dept().setDeptNo(-1L);
    }

    private List<Dept> queryAllDefault() {
        return Collections.emptyList();
    }

}
