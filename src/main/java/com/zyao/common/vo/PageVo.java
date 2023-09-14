package com.zyao.common.vo;

import lombok.Data;

import java.util.List;

@Data
public class PageVo {
    private int total;
    private Object data;


    public PageVo(int total,Object list) {
        this.data = list;
        this.total = total;
    }
}
