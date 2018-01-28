package gusev.max.readytostudy.presentation.auth.signup;

import com.hannesdorfmann.mosby3.mvp.MvpView;

import gusev.max.readytostudy.domain.model.SignUpModel;
import gusev.max.readytostudy.presentation.base.BaseViewState;
import io.reactivex.Observable;

/**
 * Created by v on 14/01/2018.
 */

public interface SignUpView extends MvpView {

    Observable<Boolean> getData();

    Observable<SignUpModel> signUp();

    void render(BaseViewState state);

}
