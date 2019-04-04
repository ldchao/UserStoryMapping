package cn.edu.nju.user_story_mapping.dao;

import cn.edu.nju.user_story_mapping.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.io.Serializable;
import java.util.List;


public interface UserDao extends JpaRepository<UserEntity,Serializable> {

    UserEntity findFirstByUsername(String username);

    List<UserEntity> findByUsernameLike(String name);

    @Query(value = "select u.username from UserEntity u")
    List<String> findAllUsername();
}
