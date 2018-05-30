package com.kun.demo.controller;

import com.kun.demo.entity.Dept;
import com.kun.demo.service.feign.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author CaoZiye
 * @version 1.0 2018/5/6 16:38
 */
@RestController
@RequestMapping("/feign")
public class FeignController {

    @Autowired
    private DeptService deptService;

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

}
