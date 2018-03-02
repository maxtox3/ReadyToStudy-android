package gusev.max.readytostudy.presentation.test.task;

import com.hannesdorfmann.mosby3.mvp.MvpView;

import gusev.max.readytostudy.domain.model.TasksModel;
import gusev.max.readytostudy.domain.model.TestModel;
import gusev.max.readytostudy.presentation.base.BaseViewState;
import io.reactivex.Observable;

/**
 * Created by v on 04/02/2018.
 */

public interface TasksView extends MvpView {

    Observable<TestModel> getData();

    Observable<TasksModel> onAnswerClicked();

    Observable<TasksModel> getNextTask();

    void render(BaseViewState state);
}
