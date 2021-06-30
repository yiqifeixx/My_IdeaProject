package cn.itcast.utils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

//连接工具类，从数据库获取一个连接，并实现与线程绑定
public class ConnectionUtils {

    private ThreadLocal<Connection> tl = new ThreadLocal<Connection>();

    private DataSource dataSource = JDBCUtils.getDataSource();
    //获取当前线程上的连接
    public Connection getThreadConnection() throws SQLException {

        Connection conn = tl.get();
        //判断当前线程是否有连接
        if (conn == null){
            conn = dataSource.getConnection();
            tl.set(conn);
        }
        return conn;
    }

    //把连接与线程解绑
    public void removeConnection(){
        tl.remove();
    }
}
