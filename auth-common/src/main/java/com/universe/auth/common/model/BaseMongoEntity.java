package com.universe.auth.common.model;


import com.fasterxml.jackson.annotation.JsonFormat;

import com.whaleal.mars.codecs.pojo.annotations.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import org.bson.BsonType;
import org.springframework.format.annotation.DateTimeFormat;


import java.io.Serializable;
import java.util.Date;

/**
 * @author: lhp
 * @Date: 2022/02/19
 * @desc 数据类基类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BaseMongoEntity implements Serializable {

    @Id(concreteClass = String.class)
    @Representation(BsonType.STRING)
    public String id;

    /**
     * 创建时间
     */
    public Date createTime;

    /**
     * 更新时间
     */
    public Date updateTime;

}
