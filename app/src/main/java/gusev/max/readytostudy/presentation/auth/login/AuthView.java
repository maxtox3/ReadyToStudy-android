package gusev.max.readytostudy.presentation.auth.login;

import com.hannesdorfmann.mosby3.mvp.MvpView;

import gusev.max.readytostudy.domain.model.SignUpModel;
import gusev.max.readytostudy.presentation.base.BaseViewState;
import io.reactivex.Observable;

/**
 * Created by v on 13/01/2018.
 */

public interface AuthView extends MvpView {

    Observable<SignUpModel> login();

    void render(BaseViewState state);
}
