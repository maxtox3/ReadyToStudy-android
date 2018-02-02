package gusev.max.readytostudy.data.repository.themes;

import android.util.Pair;

import java.util.List;

import gusev.max.readytostudy.data.entity.TestEntity;
import gusev.max.readytostudy.data.entity.ThemeEntity;
import io.reactivex.Observable;

/**
 * Created by v on 02/02/2018.
 */

public interface ThemesRepository {

    Observable<Pair<List<ThemeEntity>, List<TestEntity>>> getThemesAndTests(String token);

    Observable<List<TestEntity>> getTestsByThemeId(String token, Long themeId);
}
