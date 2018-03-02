package gusev.max.readytostudy.data.repository.tests;

import gusev.max.readytostudy.data.pojo.TasksResponsePojo;
import io.reactivex.Observable;

/**
 * Created by v on 27/02/2018.
 */

public interface TestRepository {
    Observable<TasksResponsePojo> getTasks(String token, Long id);
}
