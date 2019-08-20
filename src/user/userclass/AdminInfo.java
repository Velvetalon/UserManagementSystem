package user.userclass;

public class AdminInfo {
    private int aid;
    private String username;

    private String password;

    public AdminInfo(){
    }

    public int getAid(){
        return aid;
    }

    public void setAid( int aid ){
        this.aid = aid;
    }

    public String getUsername(){
        return username;
    }

    public void setUsername( String username ){
        this.username = username;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword( String password ){
        this.password = password;
    }

    @Override
    public String toString(){
        return "AdminInfo{" +
                "aid=" + aid +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
