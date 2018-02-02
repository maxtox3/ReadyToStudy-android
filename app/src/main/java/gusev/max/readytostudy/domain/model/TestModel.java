package gusev.max.readytostudy.domain.model;

import gusev.max.readytostudy.presentation.base.BaseModel;

/**
 * Created by v on 01/02/2018.
 */

public class TestModel extends BaseModel {

    private final Long themeId;

    public TestModel(Long id, String name, Long themeId) {
        super(id, name);
        this.themeId = themeId;
    }
}
