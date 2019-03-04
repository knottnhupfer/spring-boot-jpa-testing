package com.smooth.systems.solutions.boot.jpa.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Integer status;

    public User() {
    }

    public User(String name, Integer status) {
        this.name = name;
        this.status = status;
    }
}
