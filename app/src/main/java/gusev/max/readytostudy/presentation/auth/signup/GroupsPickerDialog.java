package gusev.max.readytostudy.presentation.auth.signup;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;

import java.io.Serializable;
import java.util.List;

import gusev.max.readytostudy.R;
import gusev.max.readytostudy.domain.model.GroupModel;

/**
 * Created by v on 21/01/2018.
 */

public class GroupsPickerDialog extends android.support.v4.app.DialogFragment {

    public static final String TAG = GroupsPickerDialog.class.getName();
    private static final String GROUPS = "GROUPS";
    private List<GroupModel> groups;
    private GroupsPickerDialogListener listener;

    public static GroupsPickerDialog newInstance(List<GroupModel> groups) {
        GroupsPickerDialog f = new GroupsPickerDialog();
        Bundle args = new Bundle();
        args.putSerializable(GROUPS, (Serializable) groups);
        f.setArguments(args);

        return f;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        try {
            listener = (GroupsPickerDialogListener) getTargetFragment();
        } catch (ClassCastException e) {
            throw new ClassCastException(getTargetFragment().toString() + " must implement GroupsPickerDialogListener");
        }
        super.onCreate(savedInstanceState);
    }

    public interface GroupsPickerDialogListener {
        void onGroupClick(GroupModel group);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        groups = (List<GroupModel>) getArguments().getSerializable(GROUPS);

        String[] groupsNames = new String[groups.size()];
        for (int i = 0; i < groupsNames.length; i++) {
            groupsNames[i] = groups.get(i).getName();
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(getTargetFragment().getActivity());
        builder.setTitle(R.string.pick_group).setItems(groupsNames, (dialog, which) -> {
            listener.onGroupClick(groups.get(which));
            dialog.dismiss();
        });
        return builder.create();
    }
}
