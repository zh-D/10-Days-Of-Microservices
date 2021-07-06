package com.offon.springdemo001.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class User {
    private Integer id;
    private String name;
    private Double price;
    @JsonFormat(timezone = "GMT+8")
    private Date createDate;

    public User(Integer id, String name, Double price, Date createDate) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.createDate = createDate;
    }
    public User() { }
}
