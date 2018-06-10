package com.kun.demo.service;

import com.kun.demo.entity.Dept;
import com.kun.demo.service.feign.DeptService;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

/**
 * @author CaoZiye
 * @version 1.0 2018/6/10 18:28
 */
//@Component
public class FeignDeptServiceFallback implements DeptService {

    @Override
    public Long addOne(Dept dept) {
        return -1L;
    }

    @Override
    public Dept queryOne(Long id) {
        return new Dept().setDeptNo(-1L);
    }

    @Override
    public List<Dept> queryAll() {
        return Collections.emptyList();
    }
}
