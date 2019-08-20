package builddata;

import dao.DataSourceUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BuildData {
    private static final String CODE_STRING = "QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm1234567890";
    private static final int QQ_MAX_SIZE = 9;
    private static final int MIN_AGE = 18;
    private static final int MAX_AGE = 45;

    private List<String> name_list = new ArrayList<>();
    private List<String> gender_list = new ArrayList<>();
    private List<String> birthplace_list = new ArrayList<>();

    BuildData(){
        name_list.add("刘一");
        name_list.add("陈二");
        name_list.add("张三");
        name_list.add("李四");
        name_list.add("王五");
        name_list.add("赵六");

        gender_list.add("男");
        gender_list.add("女");

        birthplace_list.add("武汉");
        birthplace_list.add("上海");
        birthplace_list.add("深圳");
        birthplace_list.add("西安");
        birthplace_list.add("北京");
        birthplace_list.add("南京");
        birthplace_list.add("广州");
    }

    public List<List<String>> getRandomData( int lenght ){
        List<List<String>> datalist = new ArrayList<>();
        for (int i = 0; i < lenght; i++) {
            List<String> data = new ArrayList<>();
            data.add(getRandomName());
            data.add(getRandomGender());
            data.add(String.valueOf(getRandomAge()));
            data.add(getRandomBirthplace());
            data.add(String.valueOf(getRandomQQ()));
            data.add(getRandomEmail());

            datalist.add(data);
        }
        return datalist;
    }

    public List<String> getInsertSQL( String sql, int num ){
        List<String> sqlist = new ArrayList<>();
        List<List<String>> datalist = getRandomData(num);
        Connection con = DataSourceUtils.getConnection();
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (List<String> data : datalist) {
            for (int k = 0; k < data.size(); k++) {
                try {
                    ps.setString(k + 1, data.get(k));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            sqlist.add(ps.toString().split(":")[1].strip() + ";");
//            try {
//                ps.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
        }
        return sqlist;
    }

    private String getRandomBirthplace(){
        return birthplace_list.get(new Random().nextInt(birthplace_list.size()));
    }

    public String getRandomName(){
        return name_list.get(new Random().nextInt(name_list.size()));
    }

    public String getRandomGender(){
        return gender_list.get(new Random().nextInt(gender_list.size()));
    }

    public int getRandomAge(){
        return new Random().nextInt(MAX_AGE - MIN_AGE) + MIN_AGE + 1;
    }

    public int getRandomQQ(){
        Random random = new Random();
        int num = random.nextInt(9) + 1;
        for (int i = 0; i < QQ_MAX_SIZE - 1; i++) {
            num = num * 10 + random.nextInt(10);
        }
        return num;
    }

    public String getRandomString( int min_lenght, int max_lenght ){
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        int lengh = random.nextInt(max_lenght - min_lenght) + min_lenght + 1;
        for (int i = 0; i < lengh; i++) {
            sb.append(CODE_STRING.charAt(random.nextInt(CODE_STRING.length())));
        }
        return sb.toString();
    }

    public String getRandomEmail(){
        return getRandomString(6, 12) + "@" + getRandomString(2, 4) + ".com";
    }
}