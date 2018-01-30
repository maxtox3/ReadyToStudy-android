package gusev.max.readytostudy.data.repository.auth;

import java.util.List;

import gusev.max.readytostudy.data.entity.GroupEntity;
import gusev.max.readytostudy.data.entity.UserEntity;
import io.reactivex.Observable;

/**
 * Created by v on 13/01/2018.
 */

public interface AuthRepository {

    Observable<UserEntity> login(String email, String password);

    Observable<UserEntity> signup(
        String name,
        String email,
        String password,
        Long groupId);

    Observable<List<GroupEntity>> getGroups();

    Observable<UserEntity> reauth(String token);

    Observable<UserEntity> me(String token);
}
