package com.zp.helloworld.entity;

/**
 * Created by peng.zhang
 * description:
 * Time: 2019-04-28 23:19
 */
public class Employee {

    private String id;
    private String lastName;
    private String email;
    private String gender;

    @Override
    public String toString() {
        return "Employee{" +
                "id='" + id + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
