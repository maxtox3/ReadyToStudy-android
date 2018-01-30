package gusev.max.readytostudy.domain.interactor;

import gusev.max.readytostudy.data.repository.auth.AuthRepository;
import gusev.max.readytostudy.data.repository.disciplines.DisciplinesRepository;
import gusev.max.readytostudy.domain.mapper.MainMapper;
import gusev.max.readytostudy.presentation.base.BaseViewState;
import gusev.max.readytostudy.presentation.main.disciplines.DisciplinesViewState;
import gusev.max.readytostudy.utils.SharedPrefManager;
import io.reactivex.Observable;

/**
 * Created by v on 28/01/2018.
 */

public class DisciplineInteractor {

    private MainMapper mapper;
    private DisciplinesRepository repository;
    private AuthRepository authRepository;
    private SharedPrefManager helper;

    public DisciplineInteractor(
        MainMapper mapper,
        DisciplinesRepository repository,
        AuthRepository authRepository,
        SharedPrefManager helper) {
        this.mapper = mapper;
        this.repository = repository;
        this.authRepository = authRepository;
        this.helper = helper;
    }

    public Observable<BaseViewState> getDisciplines() {
        return repository
            .getDisciplines(helper.getToken())
            .map(entity -> mapper.transformDisciplinesAndThemesPair(entity))
            .map(DisciplinesViewState.DataState::new)
            .cast(BaseViewState.class)
            .startWith(new DisciplinesViewState.LoadingState())
            .onErrorReturn(DisciplinesViewState.ErrorState::new);
    }

    public Observable<BaseViewState> selectDiscipline(Long disciplineId) {
        return repository
            .getThemesByDisciplineId(helper.getToken(), disciplineId)
            .map(list -> mapper.transformThemes(list))
            .map(DisciplinesViewState.DataState::new)
            .cast(BaseViewState.class)
            .startWith(new DisciplinesViewState.LoadingState())
            .onErrorReturn(DisciplinesViewState.ErrorState::new);
    }
}
