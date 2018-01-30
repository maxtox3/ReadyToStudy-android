package gusev.max.readytostudy.data.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import gusev.max.readytostudy.data.entity.DisciplineEntity;
import gusev.max.readytostudy.data.entity.ThemeEntity;

/**
 * Created by v on 28/01/2018.
 */

public class MainResponsePojo {

    @SerializedName("error")
    @Expose
    private String error;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("disciplines")
    @Expose
    private List<DisciplineEntity> disciplines = null;
    @SerializedName("themes")
    @Expose
    private List<ThemeEntity> themes = null;

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

    public List<DisciplineEntity> getDisciplines() {
        return disciplines;
    }

    public void setDisciplines(List<DisciplineEntity> disciplines) {
        this.disciplines = disciplines;
    }

    public List<ThemeEntity> getThemes() {
        return themes;
    }

    public void setThemes(List<ThemeEntity> themes) {
        this.themes = themes;
    }
}
