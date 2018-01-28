package gusev.max.readytostudy.domain.mapper;

import gusev.max.readytostudy.data.entity.GroupEntity;
import gusev.max.readytostudy.domain.model.GroupModel;
import io.reactivex.functions.Function;

/**
 * Created by v on 13/01/2018.
 */

public class GroupEntityToModelMapper implements Function<GroupEntity, GroupModel> {
    @Override
    public GroupModel apply(GroupEntity groupEntity) {
        return new GroupModel(groupEntity.getId(), groupEntity.getName());
    }
}
