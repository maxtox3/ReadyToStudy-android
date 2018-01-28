package gusev.max.readytostudy.presentation.auth;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;

import gusev.max.readytostudy.App;
import gusev.max.readytostudy.R;
import gusev.max.readytostudy.domain.interactor.AuthInteractor;
import gusev.max.readytostudy.domain.model.UserModel;
import gusev.max.readytostudy.presentation.auth.login.AuthFragment;
import gusev.max.readytostudy.presentation.auth.signup.SignUpFragment;
import gusev.max.readytostudy.presentation.base.BaseActivityFragmentContainer;
import gusev.max.readytostudy.presentation.main.MainActivity;
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
        interactor = App.getDependencyInjection(this).getAuthInteractor();
        if (savedInstanceState == null) {
            checkAuth();
        }
    }

    private void checkAuth() {
        Disposable disposable = interactor
            .reauth()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(userModel -> {
                loggedIn(userModel);
            }, throwable -> {
                navigateToFragment(createFragment(AUTH_FRAGMENT, null), AuthFragment.TAG);
            });
        addDisposable(disposable);
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
        navigateToFragment(createFragment(SIGN_UP_FRAGMENT, null), SignUpFragment.TAG);
    }

    @Override
    public void navigateToAuth() {
        navigateToFragment(createFragment(AUTH_FRAGMENT, null), AuthFragment.TAG);
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
    protected void restoreFragment(String tag) {
        createFragment(tag, null);
    }

    @Override
    public void setContainerId() {
        setContainerId(R.id.fragment_container);
    }
}
