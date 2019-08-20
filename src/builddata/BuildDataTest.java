package builddata;

import org.junit.jupiter.api.Test;

import java.util.List;

public class BuildDataTest {
    @Test
    public void test(){
        BuildData bd = new BuildData();
        List<String> insertSQL = bd.getInsertSQL("INSERT INTO UserInfo VALUES(NULL,?,?,?,?,?,?)", 10000);
        for (String s : insertSQL) {
            System.out.println(s);
        }
    }
}
