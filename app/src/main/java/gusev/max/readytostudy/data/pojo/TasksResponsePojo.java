package gusev.max.readytostudy.data.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import gusev.max.readytostudy.data.entity.TaskEntity;
import gusev.max.readytostudy.data.entity.TestEntity;

/**
 * Created by v on 27/02/2018.
 */

public class TasksResponsePojo {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("test")
    @Expose
    private TestEntity test;
    @SerializedName("tasks")
    @Expose
    private List<TaskEntity> tasks = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public TestEntity getTest() {
        return test;
    }

    public void setTest(TestEntity test) {
        this.test = test;
    }

    public List<TaskEntity> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskEntity> tasks) {
        this.tasks = tasks;
    }
}
