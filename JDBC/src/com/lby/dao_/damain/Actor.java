package com.lby.dao_.damain;

import java.util.Date;

/**
 * @author Humble
 * @version 1.0
 * Actor对象和 actor表的记录对应
 */
public class Actor {

    private Integer id;
    private String name;
    private String sex;
    private Date borndate;
    private String phone;

    public Actor(){}//一定要给一个无参构造器【反射需要】

    public Actor(Integer id, String name, String sex, Date borndate, String phone) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.borndate = borndate;
        this.phone = phone;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSex() {
        return sex;
    }

    public Date getBorndate() {
        return borndate;
    }

    public String getPhone() {
        return phone;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setBorndate(Date borndate) {
        this.borndate = borndate;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString(){
        return "\nActor{"+
                "id="+id+
                ",name='"+name+'\''+
                ",sex='"+sex+'\''+
                ",borndate="+borndate+
                ",phone='"+phone+'\''+
                '}';
    }
}

