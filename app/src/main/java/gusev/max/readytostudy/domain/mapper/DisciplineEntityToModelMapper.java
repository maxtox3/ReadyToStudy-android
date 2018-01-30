package gusev.max.readytostudy.domain.mapper;

import gusev.max.readytostudy.data.entity.DisciplineEntity;
import gusev.max.readytostudy.domain.model.DisciplineModel;
import io.reactivex.functions.Function;

/**
 * Created by v on 28/01/2018.
 */

public class DisciplineEntityToModelMapper implements Function<DisciplineEntity, DisciplineModel> {

    @Override
    public DisciplineModel apply(DisciplineEntity disciplineEntity) throws Exception {
        return new DisciplineModel(disciplineEntity.getId(),
            disciplineEntity.getTeacherId(),
            disciplineEntity.getName());
    }
}
