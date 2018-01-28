package gusev.max.readytostudy.data.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import gusev.max.readytostudy.data.entity.DisciplineEntity;
import gusev.max.readytostudy.data.entity.GroupEntity;
import gusev.max.readytostudy.data.entity.UserEntity;

/**
 * Created by v on 13/01/2018.
 */

public class AuthResponsePojo {

    @SerializedName("error")
    @Expose
    private String error;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("user")
    @Expose
    private UserEntity user;
    @SerializedName("group")
    @Expose
    private GroupEntity group;
    @SerializedName("disciplines")
    @Expose
    private List<DisciplineEntity> disciplines = null;
    @SerializedName("expires_in")
    @Expose
    private Integer expiresIn;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public GroupEntity getGroup() {
        return group;
    }

    public void setGroup(GroupEntity group) {
        this.group = group;
    }

    public List<DisciplineEntity> getDisciplines() {
        return disciplines;
    }

    public void setDisciplines(List<DisciplineEntity> disciplines) {
        this.disciplines = disciplines;
    }

    public Integer getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Integer expiresIn) {
        this.expiresIn = expiresIn;
    }
}
