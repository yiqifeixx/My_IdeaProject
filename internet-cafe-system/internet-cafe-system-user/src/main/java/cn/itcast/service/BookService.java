package cn.itcast.service;


import cn.itcast.domain.Book;
import cn.itcast.domain.Order;

import java.util.List;

public interface BookService {

    //查询空闲计算机
    int findByStatus();

    //预约操作
    Boolean book(String username);

    //查询已预约
    List<Book> findByUsername(String username);
}
