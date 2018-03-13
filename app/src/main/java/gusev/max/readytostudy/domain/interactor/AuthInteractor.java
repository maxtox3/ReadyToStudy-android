package gusev.max.readytostudy.domain.interactor;

import gusev.max.readytostudy.data.repository.auth.AuthRepository;
import gusev.max.readytostudy.domain.mapper.AuthMapper;
import gusev.max.readytostudy.domain.model.SignUpModel;
import gusev.max.readytostudy.domain.model.UserModel;
import gusev.max.readytostudy.presentation.auth.login.AuthViewState;
import gusev.max.readytostudy.presentation.auth.signup.SignUpViewState;
import gusev.max.readytostudy.presentation.base.BaseViewState;
import gusev.max.readytostudy.utils.SharedPrefManager;
import io.reactivex.Observable;

import static gusev.max.readytostudy.utils.Constants.TYPE_ERROR_ALL;
import static gusev.max.readytostudy.utils.Constants.TYPE_ERROR_BOTH;
import static gusev.max.readytostudy.utils.Constants.TYPE_ERROR_EMAIL;
import static gusev.max.readytostudy.utils.Constants.TYPE_ERROR_NAME;
import static gusev.max.readytostudy.utils.Constants.TYPE_ERROR_PASSWORD;

/**
 * Created by v on 13/01/2018.
 */

public class AuthInteractor {

    private static final int UNAUTHORIZED = 401;

    private AuthRepository repository;
    private AuthMapper mapper;
    private SharedPrefManager helper;

    public AuthInteractor(
            AuthRepository repository, AuthMapper mapper, SharedPrefManager helper
    ) {
        this.repository = repository;
        this.mapper = mapper;
        this.helper = helper;
    }

    public Observable<BaseViewState> login(String email, String password) {
        if (email.length() < 4 && password.length() < 4) {
            return Observable.just(new AuthViewState.FieldErrorState(TYPE_ERROR_BOTH));
        } else if (email.length() < 4) {
            return Observable.just(new AuthViewState.FieldErrorState(TYPE_ERROR_EMAIL));
        } else if (password.length() < 4) {
            return Observable.just(new AuthViewState.FieldErrorState(TYPE_ERROR_PASSWORD));
        } else {
            return repository
                    .login(email, password)
                    .map(entity -> mapper.transformUser(entity))
                    .doOnNext(userModel -> helper.saveToken(userModel.getToken()))
                    .map(AuthViewState.DataStateBase::new)
                    .cast(BaseViewState.class)
                    .startWith(new AuthViewState.LoadingState())
                    .onErrorReturn(AuthViewState.ErrorState::new);
        }
    }

    public Observable<BaseViewState> getGroups() {
        return repository
                .getGroups()
                .map(entities -> mapper.transformGroups(entities))
                .map(SignUpViewState.GroupsDataState::new)
                .cast(BaseViewState.class)
                .startWith(new SignUpViewState.LoadingState())
                .onErrorReturn(SignUpViewState.ErrorState::new);
    }

    public Observable<BaseViewState> signUp(SignUpModel model) {
        if (model.getEmail().length() < 4 && model.getPassword().length() < 4 && model
                .getName()
                .length() < 4 && model.getGroupId() == null) {
            return Observable.just(new AuthViewState.FieldErrorState(TYPE_ERROR_ALL));
        } else if (model.getEmail().length() < 4) {
            return Observable.just(new AuthViewState.FieldErrorState(TYPE_ERROR_EMAIL));
        } else if (model.getPassword().length() < 4) {
            return Observable.just(new AuthViewState.FieldErrorState(TYPE_ERROR_PASSWORD));
        } else if (model.getName().length() < 4) {
            return Observable.just(new AuthViewState.FieldErrorState(TYPE_ERROR_NAME));
        } else if (model.getGroupId() == null) {
            return getGroups();
        } else {
            return repository
                    .signup(
                            model.getName(),
                            model.getEmail(),
                            model.getPassword(),
                            model.getGroupId()
                    )
                    .map(entity -> mapper.transformUser(entity))
                    .doOnNext(userModel -> helper.saveToken(userModel.getToken()))
                    .map(SignUpViewState.DataStateBase::new)
                    .cast(BaseViewState.class)
                    .startWith(new SignUpViewState.LoadingState())
                    .onErrorReturn(SignUpViewState.ErrorState::new);
        }
    }

    public Observable<UserModel> reauth() {
        if (!helper.getToken().equals("")) {
            return repository
                    .reauth(helper.getToken())
                    .map(entity -> mapper.transformUser(entity))
                    .flatMap(userModel -> {
                        helper.saveToken(userModel.getToken());
                        return Observable.just(userModel);
                    });
        } else {
            return Observable.error(new Throwable());
        }
    }
}
