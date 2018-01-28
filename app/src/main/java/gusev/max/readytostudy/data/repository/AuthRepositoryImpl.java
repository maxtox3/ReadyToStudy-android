package gusev.max.readytostudy.data.repository;

import java.util.List;

import gusev.max.readytostudy.data.api.AuthApi;
import gusev.max.readytostudy.data.entity.GroupEntity;
import gusev.max.readytostudy.data.entity.UserEntity;
import gusev.max.readytostudy.data.pojo.GroupsPojo;
import io.reactivex.Observable;

/**
 * Created by v on 13/01/2018.
 */

public class AuthRepositoryImpl implements AuthRepository {

    private AuthApi api;

    public AuthRepositoryImpl(AuthApi api) {
        this.api = api;
    }

    @Override
    public Observable<UserEntity> login(String email, String password) {
        return api.auth(email, password).toObservable().flatMap(authResponsePojo -> {
            if (authResponsePojo.getError() != null) {
                return Observable.error(new Throwable(authResponsePojo.getError()));
            }
            UserEntity user = authResponsePojo.getUser();
            user.setToken(authResponsePojo.getToken());
            return Observable.just(authResponsePojo.getUser());
        });
    }

    @Override
    public Observable<UserEntity> signup(String name, String email, String password, Long groupId) {
        return api
            .register(name, email, password, groupId)
            .toObservable()
            .flatMap(authResponsePojo -> {
                if (authResponsePojo.getError() != null) {
                    return Observable.error(new Throwable(authResponsePojo.getError()));
                }
                return login(email, password);
            });
    }

    @Override
    public Observable<List<GroupEntity>> getGroups() {
        return api.getGroupsList().map(GroupsPojo::getGroups).toObservable();
    }

    @Override
    public Observable<UserEntity> reauth(String token) {
        return api.refresh(buildToken(token)).toObservable().flatMap(authResponsePojo -> {
            if (authResponsePojo.getError() != null) {
                return Observable.error(new Throwable(authResponsePojo.getError()));
            }
            return me(authResponsePojo.getToken());
        });
    }

    @Override
    public Observable<UserEntity> me(String token) {
        return api.me(buildToken(token))
                  .toObservable()
                  .flatMap(authResponsePojo -> {
                      if (authResponsePojo.getError() != null) {
                          return Observable.error(new Throwable(authResponsePojo.getError()));
                      }
                      UserEntity user = authResponsePojo.getUser();
                      user.setToken(token);
                      return Observable.just(authResponsePojo.getUser());
                  });
    }

    private String buildToken(String token){
        StringBuilder sb = new StringBuilder();
        sb.append("Bearer ");
        sb.append(token);
        return sb.toString();
    }
}
