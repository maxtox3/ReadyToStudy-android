package gusev.max.readytostudy.domain.mapper;

import android.util.Pair;

import java.util.List;

import gusev.max.readytostudy.data.entity.DisciplineEntity;
import gusev.max.readytostudy.data.entity.ThemeEntity;
import gusev.max.readytostudy.domain.model.DisciplineModel;
import gusev.max.readytostudy.domain.model.ThemeModel;
import io.reactivex.Observable;

/**
 * Created by v on 28/01/2018.
 */

public class MainMapper {

    private DisciplineEntityToModelMapper disciplineMapper;
    private ThemeEntityToModelMapper themeMapper;

    public MainMapper(
        DisciplineEntityToModelMapper disciplineMapper, ThemeEntityToModelMapper themeMapper) {
        this.disciplineMapper = disciplineMapper;
        this.themeMapper = themeMapper;
    }

    public Pair<List<DisciplineModel>, List<ThemeModel>> transformDisciplinesAndThemesPair(
        Pair<List<DisciplineEntity>, List<ThemeEntity>> pair) {
        List<ThemeModel> themeModels = transformThemes(pair.second);
        List<DisciplineModel> disciplineModels = transformDisciplines(pair.first);
        return new Pair<>(disciplineModels, themeModels);
    }

    private List<DisciplineModel> transformDisciplines(
        List<DisciplineEntity> entities) {
        return Observable
            .fromIterable(entities)
            .map(disciplineEntity -> disciplineMapper.apply(disciplineEntity))
            .toList()
            .blockingGet();
    }

    public List<ThemeModel> transformThemes(List<ThemeEntity> entities) {
        return Observable
            .fromIterable(entities)
            .map(themeEntity -> themeMapper.apply(themeEntity))
            .toList()
            .blockingGet();
    }
}
