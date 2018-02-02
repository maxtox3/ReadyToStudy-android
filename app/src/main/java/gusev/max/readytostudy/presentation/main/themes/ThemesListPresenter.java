package gusev.max.readytostudy.presentation.main.themes;

import com.hannesdorfmann.mosby3.mvi.MviBasePresenter;

import gusev.max.readytostudy.domain.interactor.ThemeInteractor;
import gusev.max.readytostudy.presentation.base.BaseViewState;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by v on 01/02/2018.
 */

public class ThemesListPresenter extends MviBasePresenter<ThemesListView, BaseViewState> {

    private ThemeInteractor interactor;

    public ThemesListPresenter(ThemeInteractor interactor){
        this.interactor = interactor;
    }
    @Override
    protected void bindIntents() {
        Observable<BaseViewState> getData = intent(ThemesListView::getData)
            .flatMap(themeId -> interactor.getTestsByThemeId(themeId).subscribeOn(Schedulers.io()));

//        Observable<BaseViewState> selectDiscipline = intent(ThemesListView::selectThemeIntent)
//            .flatMap(themeId -> interactor.getTestsByThemeId(themeId).subscribeOn
//                (Schedulers.io()));

        Observable<BaseViewState> allIntentsObservable = getData.observeOn(AndroidSchedulers.mainThread());//Observable.merge(getData)


        subscribeViewState(allIntentsObservable, ThemesListView::render);
    }
}
