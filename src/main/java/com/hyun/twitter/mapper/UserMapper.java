package com.hyun.twitter.mapper;

import com.hyun.twitter.entity.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    @Insert("INSERT INTO Users (username, bio, email, password, created_at, role) " +
            "VALUES (#{username}, #{bio}, #{email}, #{password}, NOW(), #{role})")
    @Options(useGeneratedKeys = true, keyProperty = "userId")
    void insertUser(User user);

    @Select("SELECT * FROM Users WHERE user_id = #{userId}")
    User findById(Long userId);

    @Update("UPDATE Users SET bio = #{bio}, updated_at = NOW() WHERE user_id = #{userId}")
    void updateBio(@Param("userId") Long userId, @Param("bio") String bio);

    @Update("UPDATE Users SET password = #{password}, updated_at = NOW() WHERE user_id = #{userId}")
    void updatePassword(@Param("userId") Long userId, @Param("password") String password);

    @Update("UPDATE Users SET deleted_at = NOW() WHERE user_id = #{userId}")
    void softDelete(Long userId);
}