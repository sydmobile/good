package com.syd.good.feature.jetpack;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

/**
 * <pre>
 *     @author Mobile
 *     e-mail : mobilesun516@gmail.com
 *     time   : 2021/8/30 16:22
 *     desc   :
 *     version: 1.0
 * </pre>
 */
@Dao
public interface UserDao {

    /**
     * 插入数据
     * @param user 用户
     * @return id 主键 id
     */
    @Insert
    long insertUser(User user);

    /**
     * 更新
     * @param user 用户
     */
    @Update
    void updateUser(User user);

    /**
     * 查询
     * @return 列表
     */
    @Query("select * from User")
    List<User> loadUser();


    /**
     * 查询年龄大于
     * @param age 年龄
     * @return 符合条件
     */
    @Query("select * from User where age > :age")
    List<User> loadUserOlderThan(int age);

    /**
     * 删除
     * @param user 删除
     */
    @Delete
    void deleteUser(User user);

    /**
     * 删除 通过lastName
     * @param lastName l
     * @return 数量
     */
    @Query("delete from User where lastName = :lastName")
    int deleteUserByLastName(String lastName);
}
