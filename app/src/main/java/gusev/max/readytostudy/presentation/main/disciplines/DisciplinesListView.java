package gusev.max.readytostudy.presentation.main.disciplines;

import com.hannesdorfmann.mosby3.mvp.MvpView;

import gusev.max.readytostudy.presentation.base.BaseViewState;
import io.reactivex.Observable;

/**
 * Created by v on 28/01/2018.
 */

public interface DisciplinesListView extends MvpView {

    Observable<Boolean> getData();

    Observable<Long> selectDisciplineIntent();

    void render(BaseViewState viewState);
}
