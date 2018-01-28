package gusev.max.readytostudy.presentation.auth;

import gusev.max.readytostudy.domain.model.UserModel;

/**
 * Created by v on 21/01/2018.
 */

public interface AuthActivityCallback {

    void loggedIn(UserModel viewObject);

    void navigateToRegister();

    void navigateToAuth();
}
