package dao;

import user.userclass.AdminInfo;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class DatabaseLogin {
    public static AdminInfo login( String username, String password){
        JdbcTemplate jdbct = DataSourceUtils.getJdbcTemplate();
        List<AdminInfo> list = jdbct.query("select * from AdminInfo where username = ? and password = MD5(?)", new Object[]{username, password},
                new BeanPropertyRowMapper<>(AdminInfo.class));
        if(list.size() == 0){
            return null;
        }else{
            return list.get(0);
        }
    }
}
