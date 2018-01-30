package gusev.max.readytostudy.data.repository.disciplines;

import android.util.Pair;

import java.util.List;

import gusev.max.readytostudy.data.entity.DisciplineEntity;
import gusev.max.readytostudy.data.entity.ThemeEntity;
import io.reactivex.Observable;

/**
 * Created by v on 28/01/2018.
 */

public interface DisciplinesRepository {

    Observable<Pair<List<DisciplineEntity>, List<ThemeEntity>>> getDisciplines(String token);

    Observable<List<ThemeEntity>> getThemesByDisciplineId(String token, Long disciplineId);
}
