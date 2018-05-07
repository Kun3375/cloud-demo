package com.kun.demo.mapper;

import com.kun.demo.entity.Dept;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author CaoZiye
 * @version 1.0 2018/4/29 20:16
 */
@Mapper
public interface DeptMapper {
    
    void insertOne(Dept dept);
    
    Dept selectById(Long id);
    
    List<Dept> selectAll();
    
}
