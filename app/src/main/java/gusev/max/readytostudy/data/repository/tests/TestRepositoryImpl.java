package gusev.max.readytostudy.data.repository.tests;

import gusev.max.readytostudy.data.api.TestApi;
import gusev.max.readytostudy.data.pojo.BasePojo;
import gusev.max.readytostudy.data.pojo.TasksResponsePojo;
import gusev.max.readytostudy.data.pojo.TestPostPojo;
import gusev.max.readytostudy.utils.StringService;
import io.reactivex.Observable;

/**
 * Created by v on 27/02/2018.
 */

public class TestRepositoryImpl implements TestRepository {

    private TestApi api;

    public TestRepositoryImpl(TestApi api) {
        this.api = api;
    }

    @Override
    public Observable<TasksResponsePojo> getTasks(String token, Long id) {
        return api.getTasksByTestId(StringService.buildTokenString(token), id).toObservable();
    }

    @Override
    public Observable<BasePojo> finishTest(String token, TestPostPojo testPostPojo) {
        return api.finishTest(StringService.buildTokenString(token), testPostPojo).toObservable();
    }
}
