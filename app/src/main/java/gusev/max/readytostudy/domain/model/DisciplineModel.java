package gusev.max.readytostudy.domain.model;

import gusev.max.readytostudy.presentation.base.BaseModel;

/**
 * Created by v on 28/01/2018.
 */

public class DisciplineModel extends BaseModel {
    public static final String DISCIPLINE_MODEL = "DISCIPLINE_MODEL";
    private final Long id;
    private final Long teacherId;
    private final String name;

    public DisciplineModel(Long id, Long teacherId, String name) {
        this.id = id;
        this.teacherId = teacherId;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public String getName() {
        return name;
    }
}
