package dao;

import org.junit.jupiter.api.Test;
import user.userclass.AdminInfo;

public class DatabaseLoginTest {
    @Test
    public void loginTest(){
        AdminInfo root = DatabaseLogin.login("root", "123456");
        System.out.println(root);
    }
}
