package cn.edu.nju.user_story_mapping.dao;

import cn.edu.nju.user_story_mapping.entity.ReleaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

/**
 * Created by ldchao on 2019/1/13.
 */
public interface ReleaseDao extends JpaRepository<ReleaseEntity,Serializable> {
}
