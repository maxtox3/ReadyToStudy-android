package gusev.max.readytostudy.domain.model;

/**
 * Created by v on 28/01/2018.
 */

public class ThemeModel {
    public static final String THEME_MODEL = "THEME_MODEL";
    private final Long id;
    private final String name;
    private final Long disciplineId;

    public ThemeModel(Long id, String name, Long disciplineId) {
        this.id = id;
        this.name = name;
        this.disciplineId = disciplineId;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getDisciplineId() {
        return disciplineId;
    }
}
