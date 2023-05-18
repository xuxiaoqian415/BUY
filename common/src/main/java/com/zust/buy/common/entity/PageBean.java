package com.zust.buy.common.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zust.buy.common.config.CustomDateTimeSerializer;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 分页 Model类
 */
@Data
public class PageBean {

    /**
     * 第几页
     */
    private int pageNum;

    /**
     * 每页记录数
     */
    private int pageSize;

    /**
     * 起始页
     */
    private int start;

    /**
     * 查询参数
     */
    private String query;

    /**
     * 大类ID
     */
    private Integer bigTypeId;

    /**
     * 商品类别ID
     */
    private Integer typeId;

    private String isSwiper;

    private String dateValue;

    private Integer status;

    public PageBean() {
    }

    public PageBean(int pageNum, int pageSize, String query) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.query = query;
    }

    public PageBean(int pageNum, int pageSize) {
        super();
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public int getStart() {
        return (pageNum-1)*pageSize;
    }

}
