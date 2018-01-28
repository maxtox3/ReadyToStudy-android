package gusev.max.readytostudy.data.entity;

/**
 * Created by v on 13/01/2018.
 */

public class DisciplineEntity {

    private Long id;
    private Long teacherId;
    private String name;
    private String createdAt;
    private String updatedAt;

    public DisciplineEntity(
        Long id, Long teacherId, String name, String createdAt, String updatedAt) {
        this.id = id;
        this.teacherId = teacherId;
        this.name = name;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
