package top.qwebnm.entity;

public class Users {
    private Integer uid;
    private String uname;

    public Users() {
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public Users(Integer uid, String uname) {
        this.uid = uid;
        this.uname = uname;
    }
}
