package gusev.max.readytostudy.presentation.main;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;

import gusev.max.readytostudy.R;
import gusev.max.readytostudy.presentation.base.BaseActivityFragmentContainer;
import gusev.max.readytostudy.presentation.base.BaseModel;
import gusev.max.readytostudy.presentation.main.disciplines.DisciplinesFragment;
import gusev.max.readytostudy.presentation.main.themes.ThemesFragment;

import static gusev.max.readytostudy.domain.model.ThemeModel.THEME_MODEL;
import static gusev.max.readytostudy.utils.Constants.CHOOSE_NOTIFICATIONS_INTERVAL_FRAGMENT;
import static gusev.max.readytostudy.utils.Constants.DISCIPLINES_LIST_FRAGMENT;
import static gusev.max.readytostudy.utils.Constants.NOTIFICATIONS_INTERVAL_CHOSEN;
import static gusev.max.readytostudy.utils.Constants.THEMES_LIST_FRAGMENT;

/**
 * Created by v on 27/01/2018.
 */

public class MainActivity extends BaseActivityFragmentContainer implements MainActivityCallback {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        if (!PreferenceManager.getDefaultSharedPreferences(this)
                .getBoolean(NOTIFICATIONS_INTERVAL_CHOSEN, false)) {
            navigateToChooseNotificationsIntervalFragment();
        } else {
            if (savedInstanceState == null) {
                navigateToDisciplines();
            }
        }
    }

    @Override
    public void setContainerId() {
        setContainerId(R.id.fragment_container);
    }

    @Override
    protected Fragment createFragment(String tag, Bundle args) {
        switch (tag) {
            case DISCIPLINES_LIST_FRAGMENT:
                return new DisciplinesFragment();
            case THEMES_LIST_FRAGMENT:
                return ThemesFragment.newInstance(args);
            case CHOOSE_NOTIFICATIONS_INTERVAL_FRAGMENT:
                return new ChooseNotificationsIntervalFragment();
            default:
                Log.i("createFragment: ", "you must add your fragment");
        }
        return null;
    }

    @Override
    public void navigateToDisciplines() {
        navigateToFragment(DISCIPLINES_LIST_FRAGMENT, null, false);
        setCurrentTag(DISCIPLINES_LIST_FRAGMENT);
    }

    @Override
    public void navigateToThemes(BaseModel model) {
        Bundle args = new Bundle();
        args.putSerializable(THEME_MODEL, model);
        navigateToFragment(THEMES_LIST_FRAGMENT, args, true);
        setCurrentTag(THEMES_LIST_FRAGMENT);
    }

    @Override
    public void navigateToChooseNotificationsIntervalFragment() {
        navigateToFragment(CHOOSE_NOTIFICATIONS_INTERVAL_FRAGMENT, null, false);
        setCurrentTag(CHOOSE_NOTIFICATIONS_INTERVAL_FRAGMENT);
    }

    @Override
    public void onBackPressedInFragment() {
        super.onBackPressed();
    }
}
