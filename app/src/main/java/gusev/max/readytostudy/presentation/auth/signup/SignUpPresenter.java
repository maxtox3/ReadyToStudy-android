package gusev.max.readytostudy.presentation.auth.signup;

import com.hannesdorfmann.mosby3.mvi.MviBasePresenter;

import gusev.max.readytostudy.domain.interactor.AuthInteractor;
import gusev.max.readytostudy.presentation.base.BaseViewState;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by v on 14/01/2018.
 */

public class SignUpPresenter extends MviBasePresenter<SignUpView, BaseViewState> {

    private AuthInteractor interactor;

    public SignUpPresenter(AuthInteractor interactor) {
        this.interactor = interactor;
    }

    @Override
    protected void bindIntents() {
        Observable<BaseViewState> getDataIntent =
                intent(SignUpView::getData).flatMap(ignored -> interactor
                        .getGroups()
                        .subscribeOn(Schedulers.io()));

        Observable<BaseViewState> signUp =
                intent(SignUpView::signUp).flatMap(signUpModel -> interactor
                        .signUp(signUpModel)
                        .subscribeOn(Schedulers.io()));

        Observable<BaseViewState> allIntentsObservable = Observable
                .merge(getDataIntent, signUp)
                .observeOn(AndroidSchedulers.mainThread());

        subscribeViewState(allIntentsObservable, SignUpView::render);
    }
}
