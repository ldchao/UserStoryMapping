package cn.edu.nju.user_story_mapping.dao;

import cn.edu.nju.user_story_mapping.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

/**
 * Created by ldchao on 2018/12/31.
 */
public interface UserDao extends JpaRepository<UserEntity,Serializable> {

}
