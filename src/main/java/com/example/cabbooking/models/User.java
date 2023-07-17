package com.example.cabbooking.models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class User {
    
    @NotEmpty(message = "Username is required")
    private String username;
    
    @NotEmpty(message = "Gender is required")
    private String gender;
    
    @NotNull(message = "Age is required")
    private Integer age;
    
    public User() {
    }

    public User(String username, String gender, Integer age) {
        this.username = username;
        this.gender = gender;
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
