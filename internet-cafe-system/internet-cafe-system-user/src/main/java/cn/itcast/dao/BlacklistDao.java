package cn.itcast.dao;

import cn.itcast.domain.Blacklist;

public interface BlacklistDao {

    //查询用户是否为黑名单用户
    Blacklist query(String username);
}
