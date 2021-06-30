package cn.itcast.service.impl;

import cn.itcast.dao.BookDao;
import cn.itcast.dao.impl.BookDaoImpl;
import cn.itcast.domain.Book;
import cn.itcast.service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {

    private BookDao bookDao = new BookDaoImpl();

    //查询空闲计算机
    @Override
    public int findByStatus() {

        return bookDao.findByStatus();
    }

    //预约
    @Override
    public Boolean book(String username) {

        return bookDao.book(username);

    }

    //查询用户已预约
    @Override
    public List<Book> findByUsername(String username) {

        return bookDao.findByUsername(username);
    }


}
