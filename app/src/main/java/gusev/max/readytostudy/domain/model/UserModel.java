package gusev.max.readytostudy.domain.model;

import gusev.max.readytostudy.presentation.base.BaseModel;

/**
 * Created by v on 13/01/2018.
 */

public class UserModel extends BaseModel {
    public static final String USER_MODEL = "USER_MODEL";
    private final String token;
    private final Long id;
    private final String name;
    private final String email;
    private final Long isTeacher;
    private final Long groupId;

    public UserModel(
        String token, Long id, String name, String email, Long isTeacher, Long groupId) {
        this.token = token;
        this.id = id;
        this.name = name;
        this.email = email;
        this.isTeacher = isTeacher;
        this.groupId = groupId;
    }

    public String getToken() {
        return token;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
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
