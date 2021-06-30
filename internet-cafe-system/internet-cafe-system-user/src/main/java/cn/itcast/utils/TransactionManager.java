package cn.itcast.utils;

import java.sql.SQLException;

//事务管理相关的工具类
public class TransactionManager {

    private ConnectionUtils connectionUtils = new ConnectionUtils();

    //开启事务
    public void beginTransaction() {
        try {
            connectionUtils.getThreadConnection().setAutoCommit(false);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //提交事务
    public void commit() {
        try {
            connectionUtils.getThreadConnection().commit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //回滚事务
    public void rollback() {
        try {
            connectionUtils.getThreadConnection().rollback();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    //关闭连接
    public void release() {
        try {
            connectionUtils.getThreadConnection().close();
            connectionUtils.removeConnection();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
