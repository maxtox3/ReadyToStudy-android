package gusev.max.readytostudy.presentation.main.themes;

import com.hannesdorfmann.mosby3.mvp.MvpView;

import gusev.max.readytostudy.presentation.base.BaseViewState;
import io.reactivex.Observable;

/**
 * Created by v on 01/02/2018.
 */

public interface ThemesListView extends MvpView {

    Observable<Long> getData();

//    Observable<Long> selectThemeIntent();

    void render(BaseViewState viewState);
}
