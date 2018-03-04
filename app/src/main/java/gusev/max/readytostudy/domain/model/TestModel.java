package gusev.max.readytostudy.domain.model;

import gusev.max.readytostudy.presentation.base.BaseModel;

/**
 * Created by v on 01/02/2018.
 */

public class TestModel extends BaseModel {

    public static final String TEST_MODEL = TestModel.class.getName();

    private final Long themeId;
    private final String description;
    private final int tasksCount;

    public TestModel(Long id, String name, Long themeId, String description, int tasksCount) {
        super(id, name);
        this.themeId = themeId;
        this.description = description;
        this.tasksCount = tasksCount;
    }

    public Long getThemeId() {
        return themeId;
    }

    public String getDescription() {
        return description;
    }

    public int getTasksCount() {
        return tasksCount;
    }
}
