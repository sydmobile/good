package com.syd.good.feature.jetpack;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * <pre>
 *     @author Mobile
 *     e-mail : mobilesun516@gmail.com
 *     time   : 2021/8/30 13:14
 *     desc   :
 *     version: 1.0
 * </pre>
 */
@Entity
public class User {
    @PrimaryKey(autoGenerate = true)
    private long id;
    private String firstName;
    private String lastName;
    private int age;
    private String school;


    public User() {

    }

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }
}
