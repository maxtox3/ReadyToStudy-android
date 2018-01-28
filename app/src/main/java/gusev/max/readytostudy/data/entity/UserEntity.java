package gusev.max.readytostudy.data.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by v on 13/01/2018.
 */

public class UserEntity {

    private String token;
    private Long id;
    private String name;
    private String email;
    @SerializedName("is_teacher")
    @Expose
    private Long isTeacher;
    @SerializedName("group_id")
    @Expose
    private Long groupId;
    private String createdAt;
    private String updatedAt;

    public UserEntity(
        String token,
        Long id,
        String name,
        String email,
        Long isTeacher,
        Long groupId,
        String createdAt,
        String updatedAt) {
        this.token = token;
        this.id = id;
        this.name = name;
        this.email = email;
        this.isTeacher = isTeacher;
        this.groupId = groupId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getIsTeacher() {
        return isTeacher;
    }

    public void setIsTeacher(Long isTeacher) {
        this.isTeacher = isTeacher;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
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
