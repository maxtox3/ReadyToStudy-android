package gusev.max.readytostudy.data.api;

import gusev.max.readytostudy.data.pojo.MainResponsePojo;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

/**
 * Created by v on 28/01/2018.
 */

public interface DisciplinesApi {

    @GET("disciplines")
    Single<MainResponsePojo> getDisciplines(@Header("Authorization") String token);

    @GET("discipline/{id}")
    Single<MainResponsePojo> getThemesByDisciplineId(
        @Header("Authorization") String token, @Path("id") Long disciplineId);
}
