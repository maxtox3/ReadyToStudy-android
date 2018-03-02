package gusev.max.readytostudy.domain.model;

import gusev.max.readytostudy.presentation.base.BaseModel;

/**
 * Created by v on 13/01/2018.
 */

public class GroupModel extends BaseModel {

    public final static String GROUP_MODEL = GroupModel.class.getName();
    public GroupModel(Long id, String name) {
        super(id, name);
    }
}
