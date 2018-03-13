package gusev.max.readytostudy.data.repository.tests;

import gusev.max.readytostudy.data.pojo.BasePojo;
import gusev.max.readytostudy.data.pojo.TasksResponsePojo;
import gusev.max.readytostudy.data.pojo.TestPostPojo;
import io.reactivex.Observable;

/**
 * Created by v on 27/02/2018.
 */

public interface TestRepository {
    Observable<TasksResponsePojo> getTasks(String token, Long id);

    Observable<BasePojo> finishTest(String token, TestPostPojo testPostPojo);
}
