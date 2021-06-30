package cn.itcast.web.servlet;

import cn.itcast.domain.Book;
import cn.itcast.domain.Order;
import cn.itcast.domain.ResultInfo;
import cn.itcast.domain.User;
import cn.itcast.service.BookService;
import cn.itcast.service.UserService;
import cn.itcast.service.impl.BookServiceImpl;
import cn.itcast.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/book/*")
public class BookServlet extends BaseServlet {

    private BookService service = new BookServiceImpl();
    //查询计算机信息
    public void queryComputer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //调用service
        int id = service.findByStatus();

        //返回结果
        ResultInfo info = new ResultInfo();
        if (id != 0){
            info.setFlag(true);
            info.setId(id);
        }else {
            info.setFlag(false);
           info.setErrorMsg("暂未查询到空闲计算机，请稍后再试");
        }
        writeValue(info,response);

    }

    //预约
    public void bookComputer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User user = (User)request.getSession().getAttribute("user");
        //调用service
        Boolean flag = service.book(user.getUsername());

        //返回结果
        ResultInfo info = new ResultInfo();
        if (flag){
            info.setFlag(true);
        }else {
            info.setFlag(false);
            info.setErrorMsg("出错了...");
        }
        writeValue(info,response);
    }

    //查询用户已预约信息
    public void findComputer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User user = (User)request.getSession().getAttribute("user");
        //调用service
        List<Book> books = service.findByUsername(user.getUsername());
        //返回结果
        writeValue(books,response);
    }
}
