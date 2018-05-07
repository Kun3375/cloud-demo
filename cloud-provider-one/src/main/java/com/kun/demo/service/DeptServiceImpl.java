package com.kun.demo.service;

import com.kun.demo.entity.Dept;
import com.kun.demo.mapper.DeptMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author CaoZiye
 * @version 1.0 2018/4/30 15:21
 */
@Service
public class DeptServiceImpl implements DeptService {
    
    @Autowired
    private DeptMapper deptMapper;
    
    @Override
    public Long insertOne(Dept dept) {
        deptMapper.insertOne(dept);
        return dept.getDeptNo();
    }
    
    @Override
    public Dept selectById(Long id) {
        return deptMapper.selectById(id);
    }
    
    @Override
    public List<Dept> selectAll() {
        return deptMapper.selectAll();
    }
}
