package cn.itcast.service.impl;


import cn.itcast.dao.UserDao;
import cn.itcast.dao.impl.UserDaoImpl;
import cn.itcast.domain.User;
import cn.itcast.service.UserService;
import cn.itcast.utils.TransactionManager;

public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();

    //注册用户
    @Override
    public boolean regist(User user) {
        //1、根据用户名查询用户对象
        User u = userDao.findByUsername(user.getUsername());
        //判断u是否为null
        if (u != null) {
            //用户名存在，注册失败
            return false;
        } else {
            //2、保存用户信息
            userDao.save(user);
            return true;
        }
    }

    //用户登录
    @Override
    public User login(User user) {
        return userDao.findByLogin(user.getUsername(),user.getPassword());
    }

    //赠送前查询
    @Override
    public User search(User user) {
        return userDao.findByUsername(user.getUsername());
    }

    //赠送操作
    @Override
    public boolean away(User user, User present) {

    TransactionManager txManager = new TransactionManager();
    User accept = userDao.findByUsername(user.getUsername());
    try {
        //赠送金额不能为0，赠送者余额必须大于赠送金额
        if (user.getMoney() != 0 && accept.getMoney() > user.getMoney()) {
            //开启事务
            txManager.beginTransaction();
            accept.setMoney(accept.getMoney() + user.getMoney());
            present.setMoney(present.getMoney() - user.getMoney());
            userDao.away(accept, present);
            //提交事务
            txManager.commit();
            return true;
        }else {
            return false;
        }
        }catch(Exception e){
            //回滚事务
            txManager.rollback();
            return false;
        }finally{
            //释放连接
            txManager.release();
        }
    }
}
