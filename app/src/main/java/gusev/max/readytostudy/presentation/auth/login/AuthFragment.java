package gusev.max.readytostudy.presentation.auth.login;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.hannesdorfmann.mosby3.mvi.MviFragment;
import com.jakewharton.rxbinding2.view.RxView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import gusev.max.readytostudy.App;
import gusev.max.readytostudy.R;
import gusev.max.readytostudy.domain.model.SignUpModel;
import gusev.max.readytostudy.domain.model.UserModel;
import gusev.max.readytostudy.presentation.auth.AuthActivityCallback;
import gusev.max.readytostudy.presentation.base.BaseModel;
import gusev.max.readytostudy.presentation.base.BaseViewState;
import io.reactivex.Observable;

import static gusev.max.readytostudy.utils.Constants.TYPE_ERROR_BOTH;
import static gusev.max.readytostudy.utils.Constants.TYPE_ERROR_EMAIL;
import static gusev.max.readytostudy.utils.Constants.TYPE_ERROR_PASSWORD;

/**
 * Created by v on 13/01/2018.
 */

public class AuthFragment extends MviFragment<AuthView, AuthPresenter> implements AuthView {

    @BindView(R.id.edit_email)
    EditText login;

    @BindView(R.id.edit_password)
    EditText password;

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    @BindView(R.id.btn_login)
    Button btnLogin;

    @BindView(R.id.input_layout_email)
    TextInputLayout inputLayoutEmail;

    @BindView(R.id.input_layout_password)
    TextInputLayout inputLayoutPassword;

    public static final String TAG = AuthFragment.class.getName();
    private Unbinder unbinder;
    private Observable<SignUpModel> loginButtonObservable;
    private AuthActivityCallback activityCallback;

    @OnClick(R.id.btn_register)
    void onRegister() {
        activityCallback.navigateToRegister();
    }

    @Override
    public void onAttach(Context context) {
        try {
            activityCallback = (AuthActivityCallback) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement activityCallback");
        }
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(
        @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_auth, null);
        unbinder = ButterKnife.bind(this, view);
        loginButtonObservable = RxView.clicks(btnLogin).share().map(o -> getModel());
        initEditTexts();
        return view;
    }

    @Override
    public void onDestroy() {
        if (unbinder != null) {
            unbinder.unbind();
        }
        super.onDestroy();
    }

    @Override
    public Observable<SignUpModel> login() {
        return loginButtonObservable;
    }

    @NonNull
    @Override
    public AuthPresenter createPresenter() {
        return App.getDependencyInjection(getActivity()).newAuthPresenter();
    }

    @Override
    public void render(BaseViewState state) {
        if (state instanceof AuthViewState.LoadingState) {
            renderLoading();
        } else if (state instanceof BaseViewState.DataStateBase) {
            renderData(((BaseViewState.DataStateBase) state).getViewObject());
        } else if (state instanceof AuthViewState.ErrorState) {
            renderError(((BaseViewState.ErrorState) state).getError());
        } else if (state instanceof AuthViewState.FieldErrorState) {
            renderFieldError(((AuthViewState.FieldErrorState) state).getFieldType());
        }
    }

    private void renderData(BaseModel viewObject) {
        if (viewObject instanceof UserModel) {
            progressBar.setVisibility(View.GONE);
            activityCallback.loggedIn((UserModel) viewObject);
        }
    }

    private void renderLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    private void renderFieldError(String fieldType) {
        progressBar.setVisibility(View.GONE);
        switch (fieldType) {
            case TYPE_ERROR_BOTH:
                setPasswordError();
                setEmailError();
                break;
            case TYPE_ERROR_EMAIL:
                setEmailError();
                break;
            case TYPE_ERROR_PASSWORD:
                setPasswordError();
        }
    }

    private void renderError(Throwable error) {
        progressBar.setVisibility(View.GONE);
        Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
    }

    private void setPasswordError() {
        inputLayoutPassword.setErrorEnabled(true);
        inputLayoutPassword.setError("Пароль должен быть более 4 символов");
    }

    private void setEmailError() {
        inputLayoutEmail.setErrorEnabled(true);
        inputLayoutEmail.setError("Email должен быть более 4 символов");
    }

    private void initEditTexts() {

        FrameLayout frameLayout = (FrameLayout) login.getParent();
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) frameLayout.getLayoutParams();
        params.setMargins(0, 0, 0, 1);
        frameLayout.setLayoutParams(params);
        frameLayout = (FrameLayout) password.getParent();
        frameLayout.setLayoutParams(params);

        login.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                inputLayoutEmail.setError(null);
                inputLayoutEmail.setErrorEnabled(false);
            }
        });

        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                inputLayoutPassword.setError(null);
                inputLayoutPassword.setErrorEnabled(false);
            }
        });

        password.setOnClickListener(view -> {
            password.setSelection(password.getText().length());
        });

        login.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_NEXT) {
                password.requestFocus();
                return true;
            }
            return false;
        });
        password.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_UNSPECIFIED) {
                btnLogin.performClick();
                return true;
            }
            return false;
        });
    }

    private SignUpModel getModel() {
        return new SignUpModel(null,
            login.getText().toString(),
            password.getText().toString(),
            null);
    }
}
