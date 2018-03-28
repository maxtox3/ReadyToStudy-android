package gusev.max.readytostudy.presentation.main;

import android.content.Context;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import gusev.max.readytostudy.App;
import gusev.max.readytostudy.R;

import static gusev.max.readytostudy.utils.Constants.FOUR_PER_DAY;
import static gusev.max.readytostudy.utils.Constants.NOTIFICATIONS_INTERVAL_CHOSEN;
import static gusev.max.readytostudy.utils.Constants.NOTIFICATIONS_PER_DAY;
import static gusev.max.readytostudy.utils.Constants.ONE_PER_DAY;
import static gusev.max.readytostudy.utils.Constants.THREE_PER_DAY;
import static gusev.max.readytostudy.utils.Constants.TWO_PER_DAY;

/**
 * Created by v on 12/03/2018.
 */

public class ChooseNotificationsIntervalFragment extends Fragment {

    MainActivityCallback activityCallback;
    Unbinder unbinder;

    @OnClick(R.id.four_per_day_btn)
    void fourPerDayCLicked() {
        saveInterval(FOUR_PER_DAY);
    }

    @OnClick(R.id.three_per_day_btn)
    void threePerDayCLicked() {
        saveInterval(THREE_PER_DAY);
    }

    @OnClick(R.id.two_per_day_btn)
    void twoPerDayCLicked() {
        saveInterval(TWO_PER_DAY);
    }

    @OnClick(R.id.one_per_day_btn)
    void onePerDayCLicked() {
        saveInterval(ONE_PER_DAY);
    }

    @Override
    public void onAttach(Context context) {
        try {
            activityCallback = (MainActivityCallback) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement activityCallback");
        }
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState
    ) {
        View view = inflater.inflate(R.layout.fragment_choose_interval, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroy() {
        if (unbinder != null) {
            unbinder.unbind();
        }
        super.onDestroy();
    }

    private void saveInterval(int interval) {
        PreferenceManager.getDefaultSharedPreferences(App.getContext()).edit()
                .putBoolean(NOTIFICATIONS_INTERVAL_CHOSEN, true).commit();
        PreferenceManager.getDefaultSharedPreferences(App.getContext()).edit()
                .putInt(NOTIFICATIONS_PER_DAY, interval).commit();

        App.getDependencyInjection(getActivity()).getNotificationService().createNextNotification();

        activityCallback.navigateToDisciplines();
    }


}
