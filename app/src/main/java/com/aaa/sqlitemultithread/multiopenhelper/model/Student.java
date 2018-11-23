package com.aaa.sqlitemultithread.multiopenhelper.model;

public class Student {
    public String id;
    public String name;
    public int age;

    public Student() {
    }

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("id=").append(id).append("  name=").append(name).append("  age=").append(age);
        return sb.toString();
    }
}
