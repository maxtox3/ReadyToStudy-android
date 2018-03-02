package gusev.max.readytostudy.presentation.base;

import java.io.Serializable;

/**
 * Created by v on 27/01/2018.
 */

public class BaseModel implements Serializable {

    public static String BASE_MODEL = BaseModel.class.getName();

    private final Long id;
    private final String name;

    public BaseModel(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
