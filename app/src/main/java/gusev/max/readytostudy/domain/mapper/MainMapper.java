package gusev.max.readytostudy.domain.mapper;

import android.util.Pair;

import java.util.List;

import gusev.max.readytostudy.data.entity.DisciplineEntity;
import gusev.max.readytostudy.data.entity.TestEntity;
import gusev.max.readytostudy.data.entity.ThemeEntity;
import gusev.max.readytostudy.domain.model.DisciplineModel;
import gusev.max.readytostudy.domain.model.TestModel;
import gusev.max.readytostudy.domain.model.ThemeModel;
import io.reactivex.Observable;

/**
 * Created by v on 28/01/2018.
 */

public class MainMapper {

    private DisciplineEntityToModelMapper disciplineMapper;
    private ThemeEntityToModelMapper themeMapper;
    private TestEntityToModelMapper testMapper;

    public MainMapper(
        DisciplineEntityToModelMapper disciplineMapper,
        ThemeEntityToModelMapper themeMapper,
        TestEntityToModelMapper testMapper) {
        this.disciplineMapper = disciplineMapper;
        this.themeMapper = themeMapper;
        this.testMapper = testMapper;
    }

    public Pair<List<DisciplineModel>, List<ThemeModel>> transformDisciplinesAndThemesPair(
        Pair<List<DisciplineEntity>, List<ThemeEntity>> pair) {
        List<ThemeModel> themeModels = transformThemes(pair.second);
        List<DisciplineModel> disciplineModels = transformDisciplines(pair.first);
        return new Pair<>(disciplineModels, themeModels);
    }

    public Pair<List<ThemeModel>, List<TestModel>> transformThemesAndTestsPair(
        Pair<List<ThemeEntity>, List<TestEntity>> pair) {
        List<ThemeModel> themeModels = transformThemes(pair.first);
        List<TestModel> testModel = transformTests(pair.second);
        return new Pair<>(themeModels, testModel);
    }

    public List<DisciplineModel> transformDisciplines(
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

    public List<TestModel> transformTests(List<TestEntity> entities) {
        return Observable
            .fromIterable(entities)
            .map(testEntity -> testMapper.apply(testEntity))
            .toList()
            .blockingGet();
    }
}
