package gusev.max.readytostudy.data.api;

import gusev.max.readytostudy.data.pojo.AuthResponsePojo;
import gusev.max.readytostudy.data.pojo.GroupsPojo;
import io.reactivex.Single;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by v on 13/01/2018.
 */

public interface AuthApi {

    @FormUrlEncoded
    @POST("auth/login")
    Single<AuthResponsePojo> auth(
        @Field("email") String email, @Field("password") String password);

    @GET("auth/groups")
    Single<GroupsPojo> getGroupsList();

    @FormUrlEncoded
    @POST("auth/signup")
    Single<AuthResponsePojo> register(
        @Field("name") String name,
        @Field("email") String email,
        @Field("password") String password,
        @Field("group_id") Long groupId);

    @POST("auth/refresh")
    Single<AuthResponsePojo> refresh(@Header("Authorization") String token);

    @GET("auth/me")
    Single<AuthResponsePojo> me(@Header("Authorization") String token);
}
