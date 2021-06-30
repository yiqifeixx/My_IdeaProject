package cn.itcast.service;

import cn.itcast.domain.User;

public interface UserService {

    //用户注册
    public boolean regist(User user);


    //用户登录
    public User login(User user);

    //用户查询赠送者信息
    public User search(User user);

    //赠送
    public boolean away(User user,User present);

}
