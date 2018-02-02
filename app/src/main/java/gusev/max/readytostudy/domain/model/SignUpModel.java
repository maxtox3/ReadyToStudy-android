package gusev.max.readytostudy.domain.model;

import gusev.max.readytostudy.presentation.base.BaseModel;

/**
 * Created by v on 13/01/2018.
 */

public class SignUpModel extends BaseModel {

    public static final String SIGN_UP_MODEL = "SIGN_UP_MODEL";

    private final String email;
    private final String password;
    private final Long groupId;

    public SignUpModel(String name, String email, String password, Long groupId) {
        super(null, name);
        this.email = email;
        this.password = password;
        this.groupId = groupId;
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
