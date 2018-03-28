package gusev.max.readytostudy.data.api;

import gusev.max.readytostudy.data.pojo.BasePojo;
import gusev.max.readytostudy.data.pojo.TasksResponsePojo;
import gusev.max.readytostudy.data.pojo.TestPostPojo;
import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by v on 27/02/2018.
 */

public interface TestApi {

    @GET("tests/{id}")
    Single<TasksResponsePojo> getTasksByTestId(
        @Header("Authorization") String token, @Path("id") Long testId);

    @POST("test/finish")
    Single<BasePojo> finishTest(
            @Header("Authorization") String token, @Body TestPostPojo testPostPojo);
}
