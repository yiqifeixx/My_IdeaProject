package cn.itcast.web.servlet;

import cn.itcast.domain.Order;
import cn.itcast.domain.ResultInfo;
import cn.itcast.domain.User;
import cn.itcast.domain.UserMessage;
import cn.itcast.service.SocketService;
import cn.itcast.service.impl.SocketServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/socket/*")
public class SocketServlet extends BaseServlet {

    private SocketService service = new SocketServiceImpl();

    public void queryOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //调用service 完成餐饮查询
        List<Order> list = service.queryOrder();

        //返回结果
        writeValue(list,response);
    }

    //餐饮消息记录
    public void queryMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //调用service处理数据
        List<UserMessage> message = service.queryMessage();
        //返回结果
        writeValue(message,response);
    }

    //保存消息
    public void saveMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //接收数据
        User user = (User)request.getSession().getAttribute("user");
        String username = user.getUsername();
        String message = request.getParameter("message");
        //将数据封装进usermessage
        UserMessage userMessage = new UserMessage();
        userMessage.setUsername(username);
        userMessage.setMessage(message);
        //调用service处理数据
        Boolean flag = service.saveMessage(userMessage);
        //返回结果
        ResultInfo info = new ResultInfo();
        if (flag){
            info.setFlag(true);
            info.setErrorMsg("发送成功");
        }else {
            info.setFlag(false);
            info.setErrorMsg("发送消息不能为空");
        }
        writeValue(info,response);
    }

}
