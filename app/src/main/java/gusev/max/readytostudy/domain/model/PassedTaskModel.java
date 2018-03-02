package gusev.max.readytostudy.domain.model;

import java.io.Serializable;

/**
 * Created by v on 02/03/2018.
 */

public class PassedTaskModel implements Serializable {

    public static final String TAG = PassedTaskModel.class.getName();

    private final String question;
    private final String givenAnswer;
    private final String rightAnswer;

    public PassedTaskModel(String question, String givenAnswer, String rightAnswer) {
        this.question = question;
        this.givenAnswer = givenAnswer;
        this.rightAnswer = rightAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public String getGivenAnswer() {
        return givenAnswer;
    }

    public String getRightAnswer() {
        return rightAnswer;
    }
}
