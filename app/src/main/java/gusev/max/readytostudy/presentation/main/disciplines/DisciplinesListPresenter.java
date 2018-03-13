package gusev.max.readytostudy.presentation.main.disciplines;

import com.hannesdorfmann.mosby3.mvi.MviBasePresenter;

import gusev.max.readytostudy.domain.interactor.DisciplineInteractor;
import gusev.max.readytostudy.presentation.base.BaseViewState;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by v on 28/01/2018.
 */

public class DisciplinesListPresenter extends MviBasePresenter<DisciplinesListView,
        BaseViewState> {

    private DisciplineInteractor interactor;

    public DisciplinesListPresenter(DisciplineInteractor interactor) {
        this.interactor = interactor;
    }

    @Override
    protected void bindIntents() {
        Observable<BaseViewState> getData = intent(DisciplinesListView::getData)
                .flatMap(ignored -> interactor.getDisciplines().subscribeOn(Schedulers.io()));

        Observable<BaseViewState> selectDiscipline =
                intent(DisciplinesListView::selectDisciplineIntent)
                        .flatMap(disciplineId -> interactor.selectDiscipline(disciplineId)
                                .subscribeOn(Schedulers.io()));

        Observable<BaseViewState> allIntentsObservable = Observable.merge(
                getData,
                selectDiscipline
        ).observeOn(AndroidSchedulers.mainThread());

        subscribeViewState(allIntentsObservable, DisciplinesListView::render);
    }
}
