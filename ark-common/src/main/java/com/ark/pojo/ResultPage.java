package com.ark.pojo;

import lombok.Data;

import java.util.List;

@Data
public class ResultPage<T> {
    private Long total; //总条数
    private Long totalPage; //总页数
    private List<T> items; //当前页面数据

}
