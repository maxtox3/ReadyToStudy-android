package gusev.max.readytostudy.data.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import gusev.max.readytostudy.data.entity.DisciplineEntity;
import gusev.max.readytostudy.data.entity.TestEntity;
import gusev.max.readytostudy.data.entity.ThemeEntity;

/**
 * Created by v on 01/02/2018.
 */

public class ThemesResponsePojo {

    @SerializedName("error")
    @Expose
    private String error;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("disciplines")
    @Expose
    private DisciplineEntity discipline = null;
    @SerializedName("themes")
    @Expose
    private List<ThemeEntity> themes = null;
    @SerializedName("tests")
    @Expose
    private List<TestEntity> tests = null;

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

    public DisciplineEntity getDisciplines() {
        return discipline;
    }

    public void setDisciplines(DisciplineEntity disciplines) {
        this.discipline = disciplines;
    }

    public List<ThemeEntity> getThemes() {
        return themes;
    }

    public void setThemes(List<ThemeEntity> themes) {
        this.themes = themes;
    }

    public List<TestEntity> getTests() {
        return tests;
    }

    public void setTests(List<TestEntity> tests) {
        this.tests = tests;
    }
}
