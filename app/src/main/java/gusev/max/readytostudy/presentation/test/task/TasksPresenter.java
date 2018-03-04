package gusev.max.readytostudy.presentation.test.task;

import com.hannesdorfmann.mosby3.mvi.MviBasePresenter;

import gusev.max.readytostudy.domain.interactor.TaskInteractor;
import gusev.max.readytostudy.presentation.base.BaseViewState;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by v on 04/02/2018.
 */

public class TasksPresenter extends MviBasePresenter<TasksView, BaseViewState> {

    private TaskInteractor interactor;

    public TasksPresenter(TaskInteractor interactor) {
        this.interactor = interactor;
    }

    @Override
    protected void bindIntents() {
        Observable<BaseViewState> getData =
                intent(TasksView::getData).flatMap(testModel -> interactor
                        .getData(testModel)
                        .subscribeOn(Schedulers.io()));

        Observable<BaseViewState> addAnswer =
                intent(TasksView::onAnswerClicked).flatMap(task -> interactor
                        .addAnswer(task)
                        .subscribeOn(Schedulers.io()));

        Observable<BaseViewState> getNextTask =
                intent(TasksView::getNextTask).flatMap(tasks -> interactor
                        .getNextTask(tasks)
                        .subscribeOn(Schedulers.io()));

        Observable<BaseViewState> allIntentsObservable = Observable
                .merge(getData, addAnswer, getNextTask)
                .observeOn(AndroidSchedulers.mainThread());

        subscribeViewState(allIntentsObservable, TasksView::render);
    }
}
