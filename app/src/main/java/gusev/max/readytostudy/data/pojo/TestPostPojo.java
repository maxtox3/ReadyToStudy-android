package gusev.max.readytostudy.data.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by v on 06/03/2018.
 */

public class TestPostPojo {

    @SerializedName("test_id")
    @Expose
    private Long testId;
    @SerializedName("right_answers_count")
    @Expose
    private Integer rightAnswersCount;
    @SerializedName("bad_answers_count")
    @Expose
    private Integer badAnswersCount;
    @SerializedName("time_spent")
    @Expose
    private Long timeSpent;

    public TestPostPojo(
            Long id,
            Integer rightAnswersCount,
            Integer badAnswersCount,
            Long timeSpent
    ) {
        this.testId = id;
        this.rightAnswersCount = rightAnswersCount;
        this.badAnswersCount = badAnswersCount;
        this.timeSpent = timeSpent;
    }

    public Long getId() {
        return testId;
    }

    public void setId(Long id) {
        this.testId = id;
    }

    public Integer getRightAnswersCount() {
        return rightAnswersCount;
    }

    public void setRightAnswersCount(Integer rightAnswersCount) {
        this.rightAnswersCount = rightAnswersCount;
    }

    public Integer getBadAnswersCount() {
        return badAnswersCount;
    }

    public void setBadAnswersCount(Integer badAnswersCount) {
        this.badAnswersCount = badAnswersCount;
    }

    public Long getTimeSpent() {
        return timeSpent;
    }

    public void setTimeSpent(Long timeSpent) {
        this.timeSpent = timeSpent;
    }
}
