package dao;

import org.junit.jupiter.api.Test;
import user.userclass.UserInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabaseOperationTest {
    @Test
    public void QueryUsersTest(){
        List<UserInfo> userInfos = DatabaseOperation.queryUsers(5, 10);
        for (UserInfo userInfo : userInfos) {
            System.out.println(userInfo);
        }
    }

    @Test
    public void QueryUserCount(){
        System.out.println(DatabaseOperation.queryUsersCount());
    }

    @Test
    public void Query(){
        Map<String, String> map = new HashMap<>();
        map.put("name", "王槐宸");
        map.put("email", "122091987@qq.com");

        List<UserInfo> result = DatabaseOperation.query(map);
        for (UserInfo userInfo : result) {
            System.out.println(userInfo);
        }
        System.out.println(result.size());
        System.out.println(result);
    }

    @Test
    public void addUser(){
        DatabaseOperation.addUser("王槐宸","不明",100,"火星","123456","122091987@qq.com");
    }

    @Test
    public void queryTest(){
        Map<String, String> map = new HashMap<>();
        map.put("name", "王");
        map.put("email", "A");
        System.out.println(DatabaseOperation.query(map, 0, 5));
    }
}
