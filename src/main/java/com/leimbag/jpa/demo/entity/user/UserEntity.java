package com.leimbag.jpa.demo.entity.user;

import com.leimbag.jpa.demo.enums.EnableDisableStatus;
import com.leimbag.jpa.demo.enums.converter.EnableDisableStatusConverter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author leimbag
 */
@Entity
@Table(name = "user")
public class UserEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String password;

    private Integer age;

    @Column(name = "enable_status", nullable = false)
    @Convert(converter = EnableDisableStatusConverter.class)
    private EnableDisableStatus enableStatus;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", nullable = false)
    private Date updatedAt;

    @PrePersist
    public void onCreate() {
        if (this.getCreatedAt() == null) {
            Date now = new Date();
            createdAt = now;
            updatedAt = now;
        }
    }

    @PreUpdate
    public void onUpdate() {
        updatedAt = new Date();
    }

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

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public EnableDisableStatus getEnableStatus() {
        return enableStatus;
    }

    public void setEnableStatus(EnableDisableStatus enableStatus) {
        this.enableStatus = enableStatus;
    }
}
