package gusev.max.readytostudy.domain.mapper;

import gusev.max.readytostudy.data.entity.TestEntity;
import gusev.max.readytostudy.domain.model.TestModel;
import io.reactivex.functions.Function;

/**
 * Created by v on 02/02/2018.
 */

public class TestEntityToModelMapper implements Function<TestEntity, TestModel> {

    @Override
    public TestModel apply(TestEntity testEntity) {
        return new TestModel(
                testEntity.getId(),
                testEntity.getName(),
                testEntity.getThemeId(),
                testEntity.getDescription(),
                testEntity.getTasksCount()
        );
    }
}