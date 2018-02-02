package gusev.max.readytostudy.presentation.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;

import gusev.max.readytostudy.R;
import gusev.max.readytostudy.presentation.base.BaseActivityFragmentContainer;
import gusev.max.readytostudy.presentation.base.BaseModel;
import gusev.max.readytostudy.presentation.main.disciplines.DisciplinesFragment;
import gusev.max.readytostudy.presentation.main.themes.ThemesFragment;

import static gusev.max.readytostudy.domain.model.ThemeModel.THEME_MODEL;
import static gusev.max.readytostudy.utils.Constants.DISCIPLINES_LIST_FRAGMENT;
import static gusev.max.readytostudy.utils.Constants.THEMES_LIST_FRAGMENT;

/**
 * Created by v on 27/01/2018.
 */

public class MainActivity extends BaseActivityFragmentContainer implements MainActivityCallback {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        if (savedInstanceState == null) {
            navigateToDisciplines();
        }
    }

    @Override
    protected void restoreFragment(String tag) {
        createFragment(tag, null);
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
                if(getSupportFragmentManager().findFragmentByTag(ThemesFragment.TAG) != null){
                    return getSupportFragmentManager().findFragmentByTag(ThemesFragment.TAG);
                } else {
                    return ThemesFragment.newInstance(args);
                }
            default:
                Log.i("createFragment: ", "you must add your fragment");
        }
        return null;
    }

    @Override
    public void navigateToDisciplines() {
        navigateToFragment(
            createFragment(DISCIPLINES_LIST_FRAGMENT, null),
            DisciplinesFragment.TAG);
    }

    @Override
    public void navigateToThemes(BaseModel model) {
        Bundle args = new Bundle();
        args.putSerializable(THEME_MODEL, model);
        navigateToFragmentWithAddToBackStack(
            createFragment(THEMES_LIST_FRAGMENT, args), ThemesFragment.TAG);

    }
}
