package com.itheima;

import java.util.ArrayList;

public class demo01 {

    public static void main(String[] args) {

        ArrayList<String> list = new ArrayList<>();

        list.add("张三");
        list.add("张小明");

        list.stream().filter(name->name.startsWith("张"))
                .filter(name->name.length()==3)
                .forEach(name-> System.out.println(name));
        System.out.println("hehe");


    }
}
