package com.dawnrecord.domain;

/**
 * @author Li-Xiaoxu
 * @version 1.0
 * @date 2020/4/22 11:02
 */
public class LxxUser {
    //id,username,pwd,sex
    private Integer Id;
    private String username;
    private String pwd;
    private String sex;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "LxxUser{" +
                "Id=" + Id +
                ", username='" + username + '\'' +
                ", pwd='" + pwd + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }
}
