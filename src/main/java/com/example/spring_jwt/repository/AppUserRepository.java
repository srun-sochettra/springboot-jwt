package com.example.spring_jwt.repository;

import com.example.spring_jwt.model.AppUser;
import org.apache.ibatis.annotations.*;

@Mapper
public interface AppUserRepository {
    @Select("""
           SELECT r.role_name FROM roles r INNER JOIN user_role ur
           ON r.id = ur.role_id WHERE user_id = #{userId}
           """)
    String getRoleByUserId(Integer userId);
    @Select("""
           SELECT * FROM users WHERE email = #{email}
           """)
    @Results(id = "userMap", value = {
            @Result(property = "roles", column = "id",
                    many = @Many(select = "getRoleByUserId")
            ),
            @Result(property = "id", column = "id"),
            @Result(property = "fullName", column = "full_name")
    })
    AppUser findByEmail(String email);

    @Insert("""
           INSERT INTO users (full_name, email, password)
           VALUES (#{fullName}, #{email}, #{password})
           """)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertUser(AppUser user);

    @Insert("""
           INSERT INTO user_role (user_id, role_id)
           VALUES (#{userId}, #{roleId})
           """)
    void insertUserRole(@Param("userId") Integer userId, @Param("roleId") Integer roleId);

    @Select("""
           SELECT id FROM roles WHERE role_name = #{roleName}
           """)
    Integer findRoleIdByName(@Param("roleName") String roleName);
}