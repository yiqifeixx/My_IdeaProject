package cn.itcast.dao.impl;
import cn.itcast.dao.BookDao;
import cn.itcast.domain.Book;
import cn.itcast.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;


public class BookDaoImpl implements BookDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    private Book book = new Book();

    private int computer = 0;

    //查询可用计算机
    @Override
    public int findByStatus() {

        String sql = "select min(computer) from tab_book where status = 'n'";
        computer = template.queryForInt(sql);
        book.setComputer(computer);
        return computer;
    }

    //预约
    @Override
    public Boolean book(String username) {

        if (book.getComputer() != 0 ){
            String sql = "update tab_book set username= ? ,status = 'y' where computer = ?";
            template.update(sql,username,book.getComputer());
            return true;
        }else {
            return false;
        }

    }

    //查询用户预约的计算机
    @Override
    public List<Book> findByUsername(String username) {

        String sql = "select computer from tab_book where username = ?";
        List<Book> books = template.query(sql, new BeanPropertyRowMapper<Book>(Book.class), username);
        return books;
    }
}
