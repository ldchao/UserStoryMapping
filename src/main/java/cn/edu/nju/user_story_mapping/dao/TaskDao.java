package cn.edu.nju.user_story_mapping.dao;

import cn.edu.nju.user_story_mapping.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ldchao on 2019/1/13.
 */
public interface TaskDao extends JpaRepository<TaskEntity, Serializable> {

    TaskEntity findFirstByAidAndTitleAndDescription(String aid, String title, String desc);

    List<TaskEntity> findByAid(String aid);

}
