package cn.itcast.service.impl;

import cn.itcast.dao.SocketDao;
import cn.itcast.dao.impl.SocketDaoImpl;
import cn.itcast.domain.Order;
import cn.itcast.domain.UserMessage;
import cn.itcast.service.SocketService;
import org.springframework.beans.factory.SmartInitializingSingleton;

import java.io.IOException;
import java.io.OutputStream;
import java.net.*;
import java.util.List;
import java.util.Scanner;

public class SocketServiceImpl implements SocketService {

    private SocketDao socketDao = new SocketDaoImpl();

    //接收公告
    @Override
    public List<Order> queryOrder() {

        return socketDao.queryOrder();
    }

    //查询餐饮消息记录
    @Override
    public List<UserMessage> queryMessage() {

        return socketDao.queryMessage();
    }

    //保存消息
    @Override
    public Boolean saveMessage(UserMessage userMessage) {

        if (userMessage.getMessage() != null && userMessage.getMessage().length() >0){
            String username = userMessage.getUsername();
            String message = userMessage.getMessage();
            return socketDao.saveMessage(username,message);
        }else {
            return false;
        }
    }

}
