package gusev.max.readytostudy.domain.interactor;

import gusev.max.readytostudy.data.repository.auth.AuthRepository;
import gusev.max.readytostudy.data.repository.themes.ThemesRepository;
import gusev.max.readytostudy.domain.mapper.MainMapper;
import gusev.max.readytostudy.presentation.base.BaseViewState;
import gusev.max.readytostudy.presentation.main.themes.ThemesViewState;
import gusev.max.readytostudy.utils.SharedPrefManager;
import io.reactivex.Observable;

/**
 * Created by v on 02/02/2018.
 */

public class ThemeInteractor {

    private final ThemesRepository repository;
    private final MainMapper mapper;
    private final SharedPrefManager helper;
    private final AuthRepository authRepository;

    public ThemeInteractor(
        ThemesRepository repository,
        MainMapper mapper,
        SharedPrefManager helper,
        AuthRepository authRepository) {
        this.repository = repository;
        this.mapper = mapper;
        this.helper = helper;
        this.authRepository = authRepository;
    }

//    public Observable<BaseViewState> getThemes() {
//        return repository
//            .getThemesAndTests(helper.getToken())
//            .map(mapper::transformThemesAndTestsPair)
//            .map(ThemesViewState.DataState::new)
//            .cast(BaseViewState.class)
//            .startWith(new ThemesViewState.LoadingState())
//            .onErrorReturn(ThemesViewState.ErrorState::new);
//    }

    public Observable<BaseViewState> getTestsByThemeId(Long themeId) {
        return repository
            .getTestsByThemeId(helper.getToken(), themeId)
            .map(mapper::transformTests)
            .map(ThemesViewState.DataState::new)
            .cast(BaseViewState.class)
            .startWith(new ThemesViewState.LoadingState())
            .onErrorReturn(ThemesViewState.ErrorState::new);
    }
}
