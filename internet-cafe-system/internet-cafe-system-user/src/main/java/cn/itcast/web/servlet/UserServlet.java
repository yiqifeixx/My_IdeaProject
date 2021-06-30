package cn.itcast.web.servlet;

import cn.itcast.dao.BlacklistDao;
import cn.itcast.dao.impl.BlacklistDaoImpl;
import cn.itcast.domain.Blacklist;
import cn.itcast.domain.ResultInfo;
import cn.itcast.domain.User;
import cn.itcast.service.UserService;
import cn.itcast.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/user/*")
public class UserServlet extends BaseServlet {

    private UserService service = new UserServiceImpl();

    //注册
    public void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //处理响应乱码
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        //1、获取数据
        Map<String, String[]> map = request.getParameterMap();
        //2、封装对象
        User user = new User();
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //3、调用service完成注册
        boolean flag = service.regist(user);
        ResultInfo info = new ResultInfo();
        //4、响应结果
        if (flag){
            //注册成功
            info.setFlag(true);
        }else {
            //注册失败
            info.setFlag(false);
            info.setErrorMsg("注册失败！");
        }
        String json = writeValueAsStream(info);
        //将json数据写回客户端
        //设置content-type
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }


    //登录
    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1、获取数据
        Map<String, String[]> map = request.getParameterMap();
        //2、封装对象
        User user = new User();
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        ResultInfo info = new ResultInfo();
        //判断登录用户是否为黑名单用户
        BlacklistDao blacklistDao = new BlacklistDaoImpl();
        String username = user.getUsername();
        Blacklist blacklist = blacklistDao.query(username);
        if (blacklist != null){
            info.setFlag(false);
            info.setErrorMsg("用户已锁定，详情请咨询管理员！");
        }else {
            //3、调用service完成登录
            User u = service.login(user);
            //判断用户对象是否为null
            if (u == null){
                //用户名密码错误
                info.setFlag(false);
                info.setErrorMsg("用户名或密码错误");
            }else {
                request.getSession().setAttribute("user",u);
                info.setFlag(true);
            }
        }
        //响应结果
        writeValue(info,response);
    }

    public void findOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //从session中获取登录用户
        Object user = request.getSession().getAttribute("user");
        //将user写回客户端
        writeValue(user,response);
    }

    public void exit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1、销毁session
        request.getSession().invalidate();
        //2、跳转登录页面
        response.sendRedirect(request.getContextPath()+"/login.html");
        System.out.println(request.getContextPath());
    }

    //查询余额
    public void balance(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1、获取数据
        Map<String, String[]> map = request.getParameterMap();
        //2、封装对象
        User user = new User();
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //3、调用service
        User u = service.login(user);
        writeValue(u,response);
    }

    //赠送前查询用户信息
    public void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1、获取数据
        Map<String, String[]> map = request.getParameterMap();
        //2、封装对象
        User user = new User();
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //3、调用service
        User u = service.search(user);
        writeValue(u,response);
    }

    //执行赠送
    public void away(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User session = (User) request.getSession().getAttribute("user");
        //1、获取数据
        Map<String, String[]> map = request.getParameterMap();
        //2、封装对象
        User user = new User();
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //3、调用service
        User present = (User) session;
        boolean flag = service.away(user, present);

        //4、响应结果
        ResultInfo info = new ResultInfo();
        if (flag){
            //成功
            info.setFlag(true);
            info.setErrorMsg("赠送成功！");
        }else {
            //失败
            info.setFlag(false);
            info.setErrorMsg("赠送失败！");
        }

        String json = writeValueAsStream(info);
        //将json数据写回客户端
        //设置content-type
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }

}
