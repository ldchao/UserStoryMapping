package cn.edu.nju.user_story_mapping.dao;

import cn.edu.nju.user_story_mapping.entity.ActivityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

/**
 * Created by ldchao on 2019/1/13.
 */
public interface ActivityDao extends JpaRepository<ActivityEntity,Serializable> {
}