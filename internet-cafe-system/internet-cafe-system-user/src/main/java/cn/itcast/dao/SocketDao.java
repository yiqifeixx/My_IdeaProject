package cn.itcast.dao;

import cn.itcast.domain.Order;
import cn.itcast.domain.UserMessage;

import java.util.List;

public interface SocketDao {

    //查询餐饮信息
    List<Order> queryOrder();

    //查询消息记录
    List<UserMessage> queryMessage();

    //保存消息
    Boolean saveMessage(String username, String message);
}
