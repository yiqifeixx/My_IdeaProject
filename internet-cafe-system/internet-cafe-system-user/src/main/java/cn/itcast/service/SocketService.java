package cn.itcast.service;

import cn.itcast.domain.Order;
import cn.itcast.domain.UserMessage;

import java.util.List;

public interface SocketService {

    //餐饮公告
    List<Order> queryOrder();

    //餐饮消息
    List<UserMessage> queryMessage();

    //保存消息
    Boolean saveMessage(UserMessage userMessage);

}
