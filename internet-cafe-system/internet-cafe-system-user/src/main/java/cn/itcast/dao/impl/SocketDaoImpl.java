package cn.itcast.dao.impl;

import cn.itcast.dao.SocketDao;
import cn.itcast.domain.Order;
import cn.itcast.domain.UserMessage;
import cn.itcast.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class SocketDaoImpl implements SocketDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    //查询菜单
    @Override
    public List<Order> queryOrder() {

        String sql = "select menu from tab_order";

        List<Order> list = template.query(sql, new BeanPropertyRowMapper<Order>(Order.class));

        return list;
    }

    //查询消息记录
    @Override
    public List<UserMessage> queryMessage() {

        String sql = "select username,message from tab_order_msg";
        List<UserMessage> messages = template.query(sql, new BeanPropertyRowMapper<UserMessage>(UserMessage.class));
        return messages;
    }

    //保存消息
    @Override
    public Boolean saveMessage(String username, String message) {

        String sql = "insert into tab_order_msg(username,message) values(?,?)";
        template.update(sql,username,message);
        return true;
    }
}
