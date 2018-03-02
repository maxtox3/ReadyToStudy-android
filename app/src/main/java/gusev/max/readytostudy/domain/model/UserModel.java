package gusev.max.readytostudy.domain.model;

import gusev.max.readytostudy.presentation.base.BaseModel;

/**
 * Created by v on 13/01/2018.
 */

public class UserModel extends BaseModel {

    public static final String USER_MODEL = UserModel.class.getName();

    private final String token;
    private final String email;
    private final Long isTeacher;
    private final Long groupId;

    public UserModel(
        String token, Long id, String name, String email, Long isTeacher, Long groupId) {
        super(id, name);
        this.token = token;
        this.email = email;
        this.isTeacher = isTeacher;
        this.groupId = groupId;
    }

    public String getToken() {
        return token;
    }

    public String getEmail() {
        return email;
    }

    public Long getIsTeacher() {
        return isTeacher;
    }

    public Long getGroupId() {
        return groupId;
    }
}
