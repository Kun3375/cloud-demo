package com.kun.demo.controller;

import com.kun.demo.entity.Dept;
import com.kun.demo.service.DeptService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author CaoZiye
 * @version 1.0 2018/5/1 9:09
 */
@RestController
@RequestMapping("/ribbon")
public class RibbonController {

    private static final Logger log = LoggerFactory.getLogger(RibbonController.class);
    @Autowired
    private DeptService deptService;
    @Autowired
    private DiscoveryClient discoveryClient;

    @PostMapping("/dept/one")
    public Long addOne(@RequestBody Dept dept) {
        return deptService.addOne(dept);
    }

    @GetMapping("/dept/one/{id}")
    public Dept queryOne(@PathVariable("id") Long id) {
        deptService.queryOne(new Dept().setDeptNo(id));
        deptService.queryOne(new Dept().setDeptNo(id));
        deptService.queryOne(new Dept().setDeptNo(id));
        deptService.queryOne(new Dept().setDeptNo(id + 1));
        deptService.queryOne(new Dept().setDeptNo(id + 1));
        return deptService.queryOne(new Dept().setDeptNo(id + 1));
    }

    @GetMapping("/dept/batch/{ids}")
    public List<Dept> testQueryBatch(@PathVariable("ids") String ids) throws ExecutionException, InterruptedException {
        List<Future<Dept>> futureList = new ArrayList<>();
        for (String id : ids.split(",")) {
            futureList.add(deptService.queryOneToBatch(Long.parseLong(id)));
        }
        List<Dept> result = new ArrayList<>(futureList.size());
        for (Future<Dept> deptFuture : futureList) {
            result.add(deptFuture.get());
        }
        return result;
    }

    @GetMapping("/dept/list")
    public List<Dept> queryAll() {
        return deptService.queryAll();
    }

    @GetMapping("/discovery/test")
    public Object testDiscovery() {
        // all services
        List<String> serviceList = discoveryClient.getServices();
        serviceList.forEach(System.out::println);

        // test cloud-provider
        List<ServiceInstance> instanceList = discoveryClient.getInstances("CLOUD-PROVIDER");
        for (ServiceInstance instance : instanceList) {
            System.out.print(instance.getServiceId() + "\t");
            System.out.print(instance.getHost() + "\t");
            System.out.print(instance.getPort() + "\t");
            System.out.println(instance.getUri() + "\t");
        }

        return this.discoveryClient;
    }

}
