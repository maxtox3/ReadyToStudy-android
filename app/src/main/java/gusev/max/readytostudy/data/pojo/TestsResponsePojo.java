package gusev.max.readytostudy.data.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import gusev.max.readytostudy.data.entity.TestEntity;

/**
 * Created by v on 02/02/2018.
 */

public class TestsResponsePojo {

    @SerializedName("error")
    @Expose
    private String error;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("tests")
    @Expose
    private List<TestEntity> tests;

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

    public List<TestEntity> getTests() {
        return tests;
    }

    public void setTests(List<TestEntity> tests) {
        this.tests = tests;
    }
}
