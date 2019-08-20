package user.userclass;

public class UserInfo {
    private int uid;
    private String name;
    private String gender;
    private int age;
    private String birthplace;
    private String qq_number;
    private String email;

    public UserInfo(){
    }

    public UserInfo( int uid, String name, String gender, int age, String brthplace, String qq_number, String email ){
        this.uid = uid;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.birthplace = brthplace;
        this.qq_number = qq_number;
        this.email = email;
    }

    public String getUid(){
        return String.valueOf(uid);
    }

    public void setUid( String uid ){
        this.uid = Integer.valueOf(uid);
    }

    public String getName(){
        return name;
    }

    public void setName( String name ){
        this.name = name;
    }

    public String getGender(){
        return gender;
    }

    public void setGender( String gender ){
        this.gender = gender;
    }

    public String getAge(){
        return String.valueOf(age);
    }

    public void setAge( String age ){
        this.age = Integer.valueOf(age);
    }

    public String getBirthplace(){
        return birthplace;
    }

    public void setBirthplace( String birthplace ){
        this.birthplace = birthplace;
    }

    public String getQq_number(){
        return qq_number;
    }

    public void setQq_number( String qq_number ){
        this.qq_number = qq_number;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail( String email ){
        this.email = email;
    }

    @Override
    public String toString(){
        return "UserInfo{" +
                "uid=" + uid +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", brthplace='" + birthplace + '\'' +
                ", qq_number='" + qq_number + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
