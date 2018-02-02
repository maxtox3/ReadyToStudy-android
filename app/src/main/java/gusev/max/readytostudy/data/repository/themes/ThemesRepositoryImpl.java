package gusev.max.readytostudy.data.repository.themes;

import android.util.Pair;

import java.util.List;

import gusev.max.readytostudy.data.api.ThemesApi;
import gusev.max.readytostudy.data.entity.TestEntity;
import gusev.max.readytostudy.data.entity.ThemeEntity;
import gusev.max.readytostudy.utils.StringService;
import io.reactivex.Observable;

/**
 * Created by v on 02/02/2018.
 */

public class ThemesRepositoryImpl implements ThemesRepository {

    private ThemesApi api;

    public ThemesRepositoryImpl(ThemesApi api) {
        this.api = api;
    }

    public Observable<Pair<List<ThemeEntity>, List<TestEntity>>> getThemesAndTests(String token) {
        return api
            .getThemes(StringService.buildTokenString(token))
            .toObservable()
            .flatMap(themesResponsePojo -> {
                if (themesResponsePojo.getError() != null) {
                    return Observable.error(new Throwable(themesResponsePojo.getError()));
                }
                return Observable.just(new Pair<>(themesResponsePojo.getThemes(),
                    themesResponsePojo.getTests()));
            });
    }

    public Observable<List<TestEntity>> getTestsByThemeId(String token, Long themeId) {
        return api.getTestsByThemeId(StringService.buildTokenString(token), themeId)
                  .toObservable()
                  .flatMap(themesResponsePojo -> {
                      if (themesResponsePojo.getError() != null) {
                          return Observable.error(new Throwable(themesResponsePojo.getError()));
                      }
                      return Observable.just(themesResponsePojo.getTests());
                  });
    }
}
