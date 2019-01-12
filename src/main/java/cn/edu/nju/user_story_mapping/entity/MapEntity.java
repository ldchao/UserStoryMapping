package cn.edu.nju.user_story_mapping.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by ldchao on 2019/1/12.
 */
@Entity
@Table(name = "map", schema = "user_story_mapping")
public class MapEntity {
    private int mid;
    private String title;
    private String description;

    @Id
    @Column(name = "mid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MapEntity mapEntity = (MapEntity) o;

        if (mid != mapEntity.mid) return false;
        if (title != null ? !title.equals(mapEntity.title) : mapEntity.title != null) return false;
        if (description != null ? !description.equals(mapEntity.description) : mapEntity.description != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = mid;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
