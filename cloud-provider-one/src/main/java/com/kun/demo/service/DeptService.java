package com.kun.demo.service;

import com.kun.demo.entity.Dept;

import java.util.List;

/**
 * @author CaoZiye
 * @version 1.0 2018/4/30 15:21
 */
public interface DeptService {
    
    
    Long insertOne(Dept dept);
    
    Dept selectById(Long id);
    
    List<Dept> selectAll();
}
