package gusev.max.readytostudy.domain.mapper;

import io.reactivex.functions.Function;

import gusev.max.readytostudy.data.entity.ThemeEntity;
import gusev.max.readytostudy.domain.model.ThemeModel;

/**
 * Created by v on 28/01/2018.
 */

public class ThemeEntityToModelMapper implements Function<ThemeEntity, ThemeModel> {

    @Override
    public ThemeModel apply(ThemeEntity themeEntity) {
        return new ThemeModel(
            themeEntity.getId(),
            themeEntity.getName(),
            themeEntity.getDisciplineId());
    }
}
