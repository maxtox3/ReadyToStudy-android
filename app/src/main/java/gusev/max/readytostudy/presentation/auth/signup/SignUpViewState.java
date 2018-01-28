package gusev.max.readytostudy.presentation.auth.signup;

import java.util.List;

import gusev.max.readytostudy.domain.model.GroupModel;
import gusev.max.readytostudy.presentation.base.BaseViewState;

/**
 * Created by v on 14/01/2018.
 */

public interface SignUpViewState extends BaseViewState {

    final class GroupsDataState implements SignUpViewState {

        private final List<GroupModel> groupModels;

        public GroupsDataState(List<GroupModel> groupModels) {
            this.groupModels = groupModels;
        }

        List<GroupModel> getGroupModels() {
            return groupModels;
        }
    }
}
