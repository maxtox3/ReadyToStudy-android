package gusev.max.readytostudy.presentation.auth.login;

import com.hannesdorfmann.mosby3.mvi.MviBasePresenter;

import gusev.max.readytostudy.domain.interactor.AuthInteractor;
import gusev.max.readytostudy.presentation.base.BaseViewState;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by v on 13/01/2018.
 */

public class AuthPresenter extends MviBasePresenter<AuthView, BaseViewState> {

    private AuthInteractor interactor;

    public AuthPresenter(AuthInteractor interactor) {
        this.interactor = interactor;
    }

    @Override
    protected void bindIntents() {

        Observable<BaseViewState> login = intent(AuthView::login).flatMap(signUpModel -> interactor
            .login(signUpModel.getEmail(), signUpModel.getPassword())
            .subscribeOn(Schedulers.io()));

        Observable<BaseViewState> allIntentsObservable = login.observeOn(AndroidSchedulers.mainThread());

        subscribeViewState(allIntentsObservable, AuthView::render);
    }
}
