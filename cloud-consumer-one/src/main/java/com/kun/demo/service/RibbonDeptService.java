package com.kun.demo.service;

import com.kun.demo.entity.Dept;

import java.util.List;
import java.util.concurrent.Future;

/**
 * @author CaoZiye
 * @version 1.0 2018/5/7 23:02
 */
public interface RibbonDeptService {


    Long addOne(Dept dept);

    Dept queryOne(Dept dept);

    Future<Dept> queryOneToBatch(Long id);

    List<Dept> queryAll();
}
