package gusev.max.readytostudy.domain.model;

import gusev.max.readytostudy.presentation.base.BaseModel;

/**
 * Created by v on 28/01/2018.
 */

public class DisciplineModel extends BaseModel {

    public static final String DISCIPLINE_MODEL = "DISCIPLINE_MODEL";

    private final Long teacherId;

    public DisciplineModel(Long id, Long teacherId, String name) {
        super(id, name);
        this.teacherId = teacherId;
    }

    public Long getTeacherId() {
        return teacherId;
    }
}
