package gusev.max.readytostudy.data.api;

import gusev.max.readytostudy.data.pojo.DisciplinesResponsePojo;
import gusev.max.readytostudy.data.pojo.ThemesResponsePojo;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

/**
 * Created by v on 28/01/2018.
 */

public interface DisciplinesApi {

    @GET("disciplines")
    Single<DisciplinesResponsePojo> getDisciplines(@Header("Authorization") String token);

    @GET("disciplines/{id}")
    Single<ThemesResponsePojo> getThemesByDisciplineId(
        @Header("Authorization") String token, @Path("id") Long disciplineId);
}
