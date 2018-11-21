package com.aaa.sqlitemultithread.singleopenhelper.model;

public class Student {
    public String id;
    public String name;
    public int age;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("id=").append(id).append("  name=").append(name).append("  age=").append(age);
        return sb.toString();
    }
}
