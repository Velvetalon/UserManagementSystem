package dao;

import user.userclass.AdminInfo;
import org.testng.annotations.Test;

public class DatabaseLoginTest {
    @Test
    public void loginTest(){
        AdminInfo root = DatabaseLogin.login("root", "123456");
        System.out.println(root);
    }
}
