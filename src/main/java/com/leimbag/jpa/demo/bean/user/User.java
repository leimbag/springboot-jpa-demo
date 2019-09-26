package com.leimbag.jpa.demo.bean.user;

import com.leimbag.jpa.demo.enums.EnableDisableStatus;

import java.io.Serializable;
import java.util.Date;

/**
 * @author leimbag
 */
public class User implements Serializable {
    private Long id;

    private String name;

    private String password;

    private Integer age;

    private EnableDisableStatus enableStatus;

    private Date createdAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public EnableDisableStatus getEnableStatus() {
        return enableStatus;
    }

    public void setEnableStatus(EnableDisableStatus enableStatus) {
        this.enableStatus = enableStatus;
    }
}
