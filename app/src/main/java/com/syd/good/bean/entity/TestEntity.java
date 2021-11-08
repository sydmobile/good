package com.syd.good.bean.entity;

/**
 * <pre>
 *     @author Mobile
 *     e-mail : mobilesun516@gmail.com
 *     time   : 2021/11/2 11:13
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class TestEntity {
    private int age;
    private String name;
    private String grade;
    private String like;
    private String height;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getLike() {
        return like;
    }

    public void setLike(String like) {
        this.like = like;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "TestEntity{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", grade='" + grade + '\'' +
                ", like='" + like + '\'' +
                ", height='" + height + '\'' +
                '}';
    }
}
