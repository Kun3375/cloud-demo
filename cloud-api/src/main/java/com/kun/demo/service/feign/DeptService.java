package com.kun.demo.service.feign;

import com.kun.demo.entity.Dept;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author CaoZiye
 * @version 1.0 2018/5/6 16:28
 */
@RequestMapping("/dept")
public interface DeptService {

    @PostMapping("/one")
    Long addOne(@RequestBody Dept dept);

    @GetMapping("/one/{id}")
    Dept queryOne(@PathVariable("id") Long id);

    @GetMapping("/list")
    List<Dept> queryAll();

}
