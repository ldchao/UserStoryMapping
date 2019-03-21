package cn.edu.nju.user_story_mapping.dao;

import cn.edu.nju.user_story_mapping.entity.EditLogEntity;
import cn.edu.nju.user_story_mapping.entity.UserMapEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ldchao on 2019/1/13.
 */
public interface EditLogDao extends JpaRepository<UserMapEntity, Serializable> {

    List<EditLogEntity> findByUid(int uid);

    List<EditLogEntity> findByMid(int mid);

}
