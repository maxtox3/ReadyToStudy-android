package gusev.max.readytostudy.presentation.test.task;

import gusev.max.readytostudy.domain.model.TasksModel;
import gusev.max.readytostudy.presentation.base.BaseViewState;

/**
 * Created by v on 04/02/2018.
 */

public interface TasksViewState extends BaseViewState {


    final class DialogState implements TasksViewState {

        private final TasksModel viewObject;

        public DialogState(TasksModel viewObject) {
            this.viewObject = viewObject;
        }

        TasksModel getViewObject() {
            return viewObject;
        }
    }

    final class FinishState implements TasksViewState {
    }
}
