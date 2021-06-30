package cn.itcast.dao.impl;

import cn.itcast.dao.UserDao;
import cn.itcast.domain.User;
import cn.itcast.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserDaoImpl implements UserDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public User findByUsername(String username) {
        User user = null;
        try{
            //1、定义sql
            String sql = "select * from tab_user where username = ?";
            //2、执行sql
            user = template.queryForObject(sql,new BeanPropertyRowMapper<User>(User.class),username);
            }catch (Exception e){
        }
        return user;
    }

    //用户注册
    @Override
    public void save(User user) {
        //1、定义sql
        String sql = "insert into tab_user(username,password,name,birthday,sex,telephone) values(?,?,?,?,?,?)";
        //2、执行sql
        template.update(sql,user.getUsername(),
                user.getPassword(),
                user.getName(),
                user.getBirthday(),
                user.getSex(),
                user.getTelephone());
    }


    //用户登录
    @Override
    public User findByLogin(String username, String password) {
        User user = null;
        try{
            //1、定义sql
            String sql = "select * from tab_user where username = ? and password = ?";
            //2、执行sql
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username,password);
        }catch (Exception e){
        }
        return user;
    }

    //网费赠送
    @Override
    public void away(User accept, User present) {

        //1、定义sql
        String sql1 = "update tab_user set money=? where username = ?";
        //2、执行sql
        template.update(sql1,accept.getMoney(),accept.getUsername());
        //1、定义sql
        String sql2 = "update tab_user set money=? where username = ?";
        //2、执行sql
        template.update(sql2,present.getMoney(),present.getUsername());
    }
}
