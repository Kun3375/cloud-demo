package com.kun.demo.service;

import com.kun.demo.service.feign.DeptService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author CaoZiye
 * @version 1.0 2018/6/10 18:18
 */
@FeignClient(name = "CLOUD-PROVIDER", fallback = FeignDeptServiceFallback.class)
public interface FeignDeptService extends DeptService {

    // 对于服务降级，可以实现该接口，并指定 @FeignClient 的 fallback 属性
    // 可以针对不同服务，指定不同的配置，需要一个不在 @ComponentScan 下的 Configuration 类
    // 可以针对请求压缩，日志输出等进行一些全局配置
}
