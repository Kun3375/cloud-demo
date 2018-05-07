package com.kun.demo.controller;

import com.kun.demo.entity.Dept;
import com.kun.demo.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author CaoZiye
 * @version 1.0 2018/5/1 9:09
 */
@RestController
@RequestMapping("/ribbon")
public class RibbonController {
    
    private static final String DEPT_PROVIDER_URL_PREFIX = "http://CLOUD-PROVIDER" + "/dept";
    
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
        return deptService.queryOne(id);
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
