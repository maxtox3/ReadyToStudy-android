package gusev.max.readytostudy.data.api;

import gusev.max.readytostudy.data.pojo.TasksResponsePojo;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

/**
 * Created by v on 27/02/2018.
 */

public interface TestApi {

    @GET("tests/{id}")
    Single<TasksResponsePojo> getTasksByTestId(
        @Header("Authorization") String token, @Path("id") Long testId);
}
