package gusev.max.readytostudy.presentation.auth;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.Toast;

import gusev.max.readytostudy.App;
import gusev.max.readytostudy.BuildConfig;
import gusev.max.readytostudy.R;
import gusev.max.readytostudy.domain.interactor.AuthInteractor;
import gusev.max.readytostudy.domain.model.UserModel;
import gusev.max.readytostudy.presentation.auth.login.AuthFragment;
import gusev.max.readytostudy.presentation.auth.signup.SignUpFragment;
import gusev.max.readytostudy.presentation.base.BaseActivityFragmentContainer;
import gusev.max.readytostudy.presentation.main.MainActivity;
import gusev.max.readytostudy.utils.NetworkConnectionCheckUtil;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static gusev.max.readytostudy.domain.model.UserModel.USER_MODEL;
import static gusev.max.readytostudy.utils.Constants.AUTH_FRAGMENT;
import static gusev.max.readytostudy.utils.Constants.SIGN_UP_FRAGMENT;

/**
 * Created by v on 13/01/2018.
 */

public class AuthActivity extends BaseActivityFragmentContainer implements AuthActivityCallback {

    private AuthInteractor interactor;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.auth_activity);
        App.getDependencyInjection(this).getNotificationService().createNextNotification();
        interactor = App.getDependencyInjection(this).getAuthInteractor();
        if (savedInstanceState == null) {
            checkAuth();
        }
    }

    private void checkAuth() {
        if (NetworkConnectionCheckUtil.isThereInternetConnection()) {
            Disposable disposable = interactor
                    .reauth()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this::loggedIn, throwable -> {
                        if(BuildConfig.DEBUG){
                            throwable.printStackTrace();
                        }
                        navigateToAuth();
                    });
            addDisposable(disposable);
        } else {
            Toast.makeText(this, "Проверьте ваше интернет соединение", Toast.LENGTH_SHORT).show();
            navigateToAuth();
        }
    }

    @Override
    public void loggedIn(UserModel viewObject) {
        Intent intent = new Intent(this, MainActivity.class);
        Bundle args = new Bundle();
        args.putSerializable(USER_MODEL, viewObject);
        startActivity(intent);
    }

    @Override
    public void navigateToRegister() {
        navigateToFragment(SIGN_UP_FRAGMENT, null, false);
        setCurrentTag(SIGN_UP_FRAGMENT);
    }

    @Override
    public void navigateToAuth() {
        navigateToFragment(AUTH_FRAGMENT, null, false);
        setCurrentTag(AUTH_FRAGMENT);
    }

    @Override
    protected Fragment createFragment(String tag, Bundle args) {
        switch (tag) {
            case AUTH_FRAGMENT:
                return new AuthFragment();
            case SIGN_UP_FRAGMENT:
                return new SignUpFragment();
            default:
                Log.i("createFragment: ", "you must add your fragment");
        }
        return null;
    }

    @Override
    public void setContainerId() {
        setContainerId(R.id.fragment_container);
    }
}
