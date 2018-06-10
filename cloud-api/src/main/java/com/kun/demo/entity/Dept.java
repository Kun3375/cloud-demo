package com.kun.demo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author CaoZiye
 * @version 1.0 2018/4/29 18:33
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class Dept {

    @JsonProperty("dept_no")
    private Long deptNo;

    @JsonProperty("dept_name")
    private String deptName;

    @JsonProperty("db_no")
    private String dbNo;

    public Dept(String deptName) {
        this.deptName = deptName;
    }
}
