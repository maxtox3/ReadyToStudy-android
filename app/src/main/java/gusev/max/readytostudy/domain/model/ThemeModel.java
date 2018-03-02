package gusev.max.readytostudy.domain.model;

import gusev.max.readytostudy.presentation.base.BaseModel;

/**
 * Created by v on 28/01/2018.
 */

public class ThemeModel extends BaseModel {

    public static final String THEME_MODEL = ThemeModel.class.getName();

    private final Long disciplineId;

    public ThemeModel(Long id, String name, Long disciplineId) {
        super(id, name);
        this.disciplineId = disciplineId;
    }

    public Long getDisciplineId() {
        return disciplineId;
    }
}
