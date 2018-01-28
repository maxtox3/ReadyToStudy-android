package gusev.max.readytostudy.data.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import gusev.max.readytostudy.data.entity.GroupEntity;

/**
 * Created by v on 13/01/2018.
 */

public class GroupsPojo {
    @SerializedName("groups")
    @Expose
    private ArrayList<GroupEntity> groups;

    @SerializedName("error")
    @Expose
    private String error;

    public List<GroupEntity> getGroups() {
        return groups;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
