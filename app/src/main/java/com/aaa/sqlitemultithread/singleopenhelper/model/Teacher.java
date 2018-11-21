package com.aaa.sqlitemultithread.singleopenhelper.model;

public class Teacher {
    public String id;
    public String name;
    public String address;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("id=").append(id).append("  name=").append(name).append("  addr=").append(address);
        return sb.toString();
    }
}
