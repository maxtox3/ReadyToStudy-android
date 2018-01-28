package gusev.max.readytostudy.domain.model;

import gusev.max.readytostudy.presentation.base.BaseModel;

/**
 * Created by v on 13/01/2018.
 */

public class SignUpModel extends BaseModel{
    private final String name;
    private final String email;
    private final String password;
    private final Long groupId;

    public SignUpModel(String name, String email, String password, Long groupId) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.groupId = groupId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Long getGroupId() {
        return groupId;
    }
}
