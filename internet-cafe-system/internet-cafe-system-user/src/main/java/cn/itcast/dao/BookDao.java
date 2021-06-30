package cn.itcast.dao;


import cn.itcast.domain.Book;

import java.util.List;

public interface BookDao {

    //查询空闲计算机
    int findByStatus();

    //预约
    Boolean book(String username);

    //查询用户预约的计算机
    List<Book> findByUsername(String username);

}
