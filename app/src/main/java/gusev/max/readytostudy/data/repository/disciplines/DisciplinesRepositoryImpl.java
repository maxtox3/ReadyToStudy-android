package gusev.max.readytostudy.data.repository.disciplines;

import android.util.Pair;

import java.util.List;

import gusev.max.readytostudy.data.api.DisciplinesApi;
import gusev.max.readytostudy.data.entity.DisciplineEntity;
import gusev.max.readytostudy.data.entity.ThemeEntity;
import gusev.max.readytostudy.utils.StringService;
import io.reactivex.Observable;

/**
 * Created by v on 28/01/2018.
 */

public class DisciplinesRepositoryImpl implements DisciplinesRepository {

    private DisciplinesApi api;

    public DisciplinesRepositoryImpl(DisciplinesApi api) {
        this.api = api;
    }

    @Override
    public Observable<Pair<List<DisciplineEntity>, List<ThemeEntity>>> getDisciplines(
        String token) {
        return api
            .getDisciplines(StringService.buildTokenString(token))
            .toObservable()
            .flatMap(mainResponsePojo -> {
                if (mainResponsePojo.getError() != null) {
                    return Observable.error(new Throwable(mainResponsePojo.getError()));
                }
                return Observable.just(new Pair<>(mainResponsePojo.getDisciplines(),
                    mainResponsePojo.getThemes()));
            });
    }

    @Override
    public Observable<List<ThemeEntity>> getThemesByDisciplineId(String token, Long disciplineId) {
        return api.getThemesByDisciplineId(StringService.buildTokenString(token), disciplineId)
                  .toObservable()
                  .flatMap(mainResponsePojo -> {
                      if (mainResponsePojo.getError() != null) {
                          return Observable.error(new Throwable(mainResponsePojo.getError()));
                      }
                      return Observable.just(mainResponsePojo.getThemes());
                  });
    }
}
