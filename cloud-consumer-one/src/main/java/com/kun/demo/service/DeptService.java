package com.kun.demo.service;

import com.kun.demo.entity.Dept;
import rx.Observable;

import java.util.List;

/**
 * @author CaoZiye
 * @version 1.0 2018/5/7 23:02
 */
public interface DeptService {


    Long addOne(Dept dept);

    Dept queryOne(Long id);

    List<Dept> queryAll();
}
