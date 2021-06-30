package cn.itcast.dao.impl;

import cn.itcast.dao.BlacklistDao;
import cn.itcast.domain.Blacklist;
import cn.itcast.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;


public class BlacklistDaoImpl implements BlacklistDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    //查询黑名单用户
    @Override
    public Blacklist query(String username) {

        Blacklist blacklist = null;
        try{
            String sql = "select * from tab_blacklist where username = ?";
            blacklist = template.queryForObject(sql, new BeanPropertyRowMapper<Blacklist>(Blacklist.class), username);
        }catch (Exception e){
        }
        return blacklist;
    }



}
