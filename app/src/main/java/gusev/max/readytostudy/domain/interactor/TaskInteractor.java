package gusev.max.readytostudy.domain.interactor;

import gusev.max.readytostudy.data.pojo.TestPostPojo;
import gusev.max.readytostudy.data.repository.auth.AuthRepository;
import gusev.max.readytostudy.data.repository.tests.TestRepository;
import gusev.max.readytostudy.domain.mapper.MainMapper;
import gusev.max.readytostudy.domain.model.TasksModel;
import gusev.max.readytostudy.domain.model.TestModel;
import gusev.max.readytostudy.presentation.base.BaseViewState;
import gusev.max.readytostudy.presentation.test.task.TasksViewState;
import gusev.max.readytostudy.utils.SharedPrefManager;
import io.reactivex.Observable;

/**
 * Created by v on 04/02/2018.
 */

public class TaskInteractor {

    private final TestRepository repository;
    private final MainMapper mapper;
    private final SharedPrefManager helper;
    private final AuthRepository authRepository;

    public TaskInteractor(
            TestRepository repository,
            MainMapper mapper,
            SharedPrefManager helper,
            AuthRepository authRepository
    ) {
        this.repository = repository;
        this.mapper = mapper;
        this.helper = helper;
        this.authRepository = authRepository;
    }

    public Observable<BaseViewState> getData(TestModel testModel) {
        return repository
                .getTasks(helper.getToken(), testModel.getId())
                .map(mapper::transformTest)
                .map(TasksViewState.DataState::new)
                .cast(BaseViewState.class)
                .startWith(new TasksViewState.LoadingState())
                .onErrorReturn(TasksViewState.ErrorState::new);
    }

    public Observable<BaseViewState> addAnswer(TasksModel tasksModel) {
        return Observable.just(new TasksViewState.DialogState(tasksModel));
    }

    public Observable<BaseViewState> getNextTask(TasksModel tasksModel) {
        return Observable
                .just(new TasksViewState.DataState<>(TasksModel.onNextTask(tasksModel)))
                .flatMap(state -> {
                    if (state.getViewObject() == null) {
                        return finishTask(tasksModel);
                    }
                    return Observable.just(state);
                });
    }

    public Observable<BaseViewState> finishTask(TasksModel tasksModel) {
        TestPostPojo pojo = mapper.revertTransformTest(tasksModel);
        return repository.finishTest(helper.getToken(), pojo)
                .map(basePojo -> new TasksViewState.FinishState(tasksModel))
                .cast(BaseViewState.class)
                .startWith(new TasksViewState.LoadingState())
                .onErrorReturn(TasksViewState.ErrorState::new);
    }
}
