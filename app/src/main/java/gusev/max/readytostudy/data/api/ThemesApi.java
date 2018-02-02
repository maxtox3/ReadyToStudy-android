package gusev.max.readytostudy.data.api;

import gusev.max.readytostudy.data.pojo.ThemesResponsePojo;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

/**
 * Created by v on 02/02/2018.
 */

public interface ThemesApi {

    @GET("themes")
    Single<ThemesResponsePojo> getThemes(@Header("Authorization") String token);

    @GET("themes/{id}")
    Single<ThemesResponsePojo> getTestsByThemeId(
        @Header("Authorization") String token, @Path("id") Long themeId);

}
