package gusev.max.readytostudy.domain.mapper;

import gusev.max.readytostudy.data.entity.UserEntity;
import gusev.max.readytostudy.domain.model.UserModel;
import io.reactivex.functions.Function;

/**
 * Created by v on 13/01/2018.
 */

public class UserEntityToModelMapper implements Function<UserEntity, UserModel> {

    @Override
    public UserModel apply(UserEntity userEntity) {
        return new UserModel(
            userEntity.getToken(),
            userEntity.getId(),
            userEntity.getName(),
            userEntity.getEmail(),
            userEntity.getIsTeacher(),
            userEntity.getGroupId()
        );
    }
}
