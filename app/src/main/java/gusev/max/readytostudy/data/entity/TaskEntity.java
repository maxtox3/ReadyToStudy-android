package gusev.max.readytostudy.data.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by v on 27/02/2018.
 */

public class TaskEntity {

    @SerializedName("id")
    @Expose
    private Long id;
    @SerializedName("test_id")
    @Expose
    private Long testId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("first_var")
    @Expose
    private String firstVar;
    @SerializedName("second_var")
    @Expose
    private String secondVar;
    @SerializedName("third_var")
    @Expose
    private String thirdVar;
    @SerializedName("fourth_var")
    @Expose
    private String fourthVar;
    @SerializedName("right_var")
    @Expose
    private Integer rightVar;
    @SerializedName("created_at")
    @Expose
    private Long createdAt;
    @SerializedName("updated_at")
    @Expose
    private Long updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTestId() {
        return testId;
    }

    public void setTestId(Long testId) {
        this.testId = testId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstVar() {
        return firstVar;
    }

    public void setFirstVar(String firstVar) {
        this.firstVar = firstVar;
    }

    public String getSecondVar() {
        return secondVar;
    }

    public void setSecondVar(String secondVar) {
        this.secondVar = secondVar;
    }

    public String getThirdVar() {
        return thirdVar;
    }

    public void setThirdVar(String thirdVar) {
        this.thirdVar = thirdVar;
    }

    public String getFourthVar() {
        return fourthVar;
    }

    public void setFourthVar(String fourthVar) {
        this.fourthVar = fourthVar;
    }

    public Integer getRightVar() {
        return rightVar;
    }

    public void setRightVar(Integer rightVar) {
        this.rightVar = rightVar;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public Long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Long updatedAt) {
        this.updatedAt = updatedAt;
    }
}
