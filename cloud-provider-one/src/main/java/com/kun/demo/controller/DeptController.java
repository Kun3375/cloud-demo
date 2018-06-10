package com.kun.demo.controller;

import com.kun.demo.entity.Dept;
import com.kun.demo.service.DeptService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author CaoZiye
 * @version 1.0 2018/4/30 15:24
 */
@RestController
public class DeptController implements com.kun.demo.service.feign.DeptService {

    private static final Logger log = LoggerFactory.getLogger(DeptController.class);

    @Autowired
    private DeptService deptService;

    @Override
    public Long addOne(@RequestBody Dept dept) {
        log.info("received a request for add dept...");
        return deptService.insertOne(dept);
    }

    @Override
    public Dept queryOne(@PathVariable("id") Long id) {
        log.info("received a request for query a dept by id...");
        return deptService.selectById(id);
    }

    @GetMapping("/list/{ids}")
    public List<Dept> queryBatch(@PathVariable("ids") String ids) {
        log.info("received a request batch, id: {}", ids);
        List<Dept> result = Stream.of(ids.split(","))
                .map(Long::parseLong)
                .map(deptService::selectById)
                .collect(Collectors.toList());
        log.info("request batch result：{}", result);
        return result;
    }

    @Override
    public List<Dept> queryAll() {
        log.info("received a request for query all dept...");
        return deptService.selectAll();
    }
}
