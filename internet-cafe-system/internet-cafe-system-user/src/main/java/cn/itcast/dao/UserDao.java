package cn.itcast.dao;


import cn.itcast.domain.User;

public interface UserDao {

    //根据用户名查询用户信息
    public User findByUsername(String username);

    //用户保存
    public void save(User user);

    //根据用户名和密码查询用户信息
    public User findByLogin(String username, String password);

    //修改money数据
    public void away(User user, User present);

}
