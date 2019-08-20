package dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import user.userclass.UserInfo;

import java.util.List;
import java.util.Map;

public class DatabaseOperation {

    //查询指定页数的数据
    public static List<UserInfo> queryUsers( int begin, int length ){
        if (begin < 0 || length < 0) {
            return null;
        }

        JdbcTemplate jdbct = DataSourceUtils.getJdbcTemplate();

        //创建BeanPropertyRowMapper类，映射多个Java对象与查询结果集。
        RowMapper<UserInfo> rowMapper = new BeanPropertyRowMapper<>(UserInfo.class);
        return jdbct.query("select * from UserInfo limit " + begin + "," + length, rowMapper);
    }

    //查询数据总条数
    public static int queryUsersCount(){
        JdbcTemplate jdbct = DataSourceUtils.getJdbcTemplate();
        return jdbct.queryForObject("select count(*) from UserInfo", Integer.class);
    }

    //复合查询
    public static List<UserInfo> query( Map<String, String> map ){
        if (map.isEmpty()) {
            return null;
        }

        String sqlTemplate = "select * from UserInfo where ";
        StringBuilder sql = new StringBuilder(sqlTemplate);
        JdbcTemplate jdbct = DataSourceUtils.getJdbcTemplate();

        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (sql.length() != sqlTemplate.length()) {
                sql.append(" and ");
            }
            sql.append(entry.getKey().replaceAll("'", "\'"))
                    .append(" = '").append(entry.getValue().replaceAll("'", "\'")).append("'");
        }

        //创建BeanPropertyRowMapper类，映射多个Java对象与查询结果集。
        RowMapper<UserInfo> rowMapper = new BeanPropertyRowMapper<>(UserInfo.class);
        return jdbct.query(sql.toString(), rowMapper);
    }

    //部分复合查询
    public static List<UserInfo> query( Map<String, String> map, int begin, int length ){
        String sqlTemplate = "select * from UserInfo where ";
        StringBuilder sql = new StringBuilder(sqlTemplate);
        JdbcTemplate jdbct = DataSourceUtils.getJdbcTemplate();

        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (sql.length() != sqlTemplate.length()) {
                sql.append(" and ");
            }
            sql.append(entry.getKey().replace("'", "\'"))
                    .append(" like '%").append(entry.getValue().replace("'", "\'")).append("%'");
        }
        sql.append("limit ").append(begin).append(",").append(length);

        //创建BeanPropertyRowMapper类，映射多个Java对象与查询结果集。
        RowMapper<UserInfo> rowMapper = new BeanPropertyRowMapper<>(UserInfo.class);
        return jdbct.query(sql.toString(), rowMapper);
    }

    //部分复合查询结果
    public static int queryCount( Map<String, String> map, int begin, int length ){
        String sqlTemplate = "select count(*) from UserInfo where ";
        StringBuilder sql = new StringBuilder(sqlTemplate);
        JdbcTemplate jdbct = DataSourceUtils.getJdbcTemplate();

        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (sql.length() != sqlTemplate.length()) {
                sql.append(" and ");
            }
            sql.append(entry.getKey().replace("'", "\'"))
                    .append(" like '%").append(entry.getValue().replace("'", "\'")).append("%' ");
        }

        return jdbct.queryForObject(sql.toString(), Integer.class);
    }

    //添加用户
    public static void addUser( String name, String gender, int age, String birthplace, String qq_number, String email ){
        String sqlTemplate = "INSERT INTO UserInfo VALUES(NULL,?,?,?,?,?,?)";
        JdbcTemplate jdbct = DataSourceUtils.getJdbcTemplate();
        jdbct.update(sqlTemplate, name, gender, age, birthplace, qq_number, email);
    }

    //根据uid查询指定用户
    public static UserInfo queryForUser( String uid ){
        JdbcTemplate jdbct = DataSourceUtils.getJdbcTemplate();
        List<UserInfo> query = jdbct.query("select * from UserInfo where uid = ?", new Object[]{uid}, new BeanPropertyRowMapper<>(UserInfo.class));
        return query.size() == 0 ? null : query.get(0);
    }

    //修改用户
    public static void update( UserInfo user ){
        JdbcTemplate jdbct = DataSourceUtils.getJdbcTemplate();
        jdbct.update("UPDATE  UserInfo SET NAME = ? , gender = ? , age = ? , birthplace = ? , qq_number = ? , email = ? WHERE uid = ?",
                user.getName(), user.getGender(), user.getAge(), user.getBirthplace(), user.getQq_number(), user.getEmail(), user.getUid());
    }

    //删除用户
    public static void remove( String uid ){
        if (uid == null) {
            return;
        }
        try {
            Integer.valueOf(uid);
        } catch (Exception e) {
            return;
        }
        JdbcTemplate jdbct = DataSourceUtils.getJdbcTemplate();
        jdbct.update("delete from UserInfo where uid = ?", uid);
    }
}
