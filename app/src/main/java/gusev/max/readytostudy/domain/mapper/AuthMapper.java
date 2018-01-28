package gusev.max.readytostudy.domain.mapper;

import java.util.List;

import gusev.max.readytostudy.data.entity.GroupEntity;
import gusev.max.readytostudy.data.entity.UserEntity;
import gusev.max.readytostudy.domain.model.GroupModel;
import gusev.max.readytostudy.domain.model.UserModel;
import io.reactivex.Observable;

/**
 * Created by v on 13/01/2018.
 */

public class AuthMapper {

    private UserEntityToModelMapper userMapper;
    private GroupEntityToModelMapper groupsMapper;

    public AuthMapper(
        UserEntityToModelMapper usermapper, GroupEntityToModelMapper groupsMapper) {
        this.userMapper = usermapper;
        this.groupsMapper = groupsMapper;
    }

    public List<GroupModel> transformGroups(List<GroupEntity> entities) {
        return Observable
            .fromIterable(entities)
            .map(groupEntity -> groupsMapper.apply(groupEntity))
            .toList()
            .blockingGet();
    }

    public UserModel transformUser(UserEntity entity) {
        return userMapper.apply(entity);
    }
}
