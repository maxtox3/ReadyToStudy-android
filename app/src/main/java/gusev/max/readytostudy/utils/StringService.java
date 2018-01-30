package gusev.max.readytostudy.utils;

import gusev.max.readytostudy.App;

/**
 * Created by v on 27/01/2018.
 */

public class StringService {

    public static String getById(int id) {
        if (App.getContext() != null) {
            return App.getContext().getResources().getString(id);
        } else {
            throw new NullPointerException();
        }
    }

    public static String buildTokenString(String token) {
        return "Bearer " + token;
    }
}
