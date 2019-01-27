package cn.edu.nju.user_story_mapping.dao;

import cn.edu.nju.user_story_mapping.entity.MapEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ldchao on 2019/1/13.
 */
public interface MapDao extends JpaRepository<MapEntity,Serializable> {

    List<MapEntity> findByTitleAndDescription(String title,String desc);
}
