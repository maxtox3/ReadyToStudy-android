package gusev.max.readytostudy.presentation.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import gusev.max.readytostudy.R;
import gusev.max.readytostudy.presentation.base.BaseActivityFragmentContainer;

/**
 * Created by v on 27/01/2018.
 */

public class MainActivity extends BaseActivityFragmentContainer {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
    }

    @Override
    protected Fragment createFragment(String tag, Bundle args) {
        return null;
    }

    @Override
    protected void restoreFragment(String tag) {

    }

    @Override
    public void setContainerId() {

    }
}
