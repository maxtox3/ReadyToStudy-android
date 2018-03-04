package gusev.max.readytostudy.presentation.auth.signup;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.hannesdorfmann.mosby3.mvi.MviFragment;
import com.jakewharton.rxbinding2.view.RxView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import gusev.max.readytostudy.App;
import gusev.max.readytostudy.R;
import gusev.max.readytostudy.domain.model.GroupModel;
import gusev.max.readytostudy.domain.model.SignUpModel;
import gusev.max.readytostudy.domain.model.UserModel;
import gusev.max.readytostudy.presentation.auth.AuthActivityCallback;
import gusev.max.readytostudy.presentation.base.BaseModel;
import gusev.max.readytostudy.presentation.base.BaseViewState;
import io.reactivex.Observable;

import static gusev.max.readytostudy.utils.Constants.DIALOG_REQUEST_CODE;
import static gusev.max.readytostudy.utils.Constants.TYPE_ERROR_BOTH;
import static gusev.max.readytostudy.utils.Constants.TYPE_ERROR_EMAIL;
import static gusev.max.readytostudy.utils.Constants.TYPE_ERROR_NAME;
import static gusev.max.readytostudy.utils.Constants.TYPE_ERROR_PASSWORD;

/**
 * Created by v on 14/01/2018.
 */

public class SignUpFragment extends MviFragment<SignUpView, SignUpPresenter> implements SignUpView,
        GroupsPickerDialog.GroupsPickerDialogListener {

    @BindView(R.id.edit_email)
    EditText email;
    @BindView(R.id.edit_password)
    EditText password;
    @BindView(R.id.edit_name)
    EditText name;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.btn_register)
    Button btnRegister;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.group_name)
    TextView groupName;
    @BindView(R.id.btn_groups)
    ImageView groupsBtn;
    @BindView(R.id.input_layout_email)
    TextInputLayout inputLayoutEmail;
    @BindView(R.id.input_layout_name)
    TextInputLayout inputLayoutName;
    @BindView(R.id.input_layout_password)
    TextInputLayout inputLayoutPassword;

    public static final String TAG = SignUpFragment.class.getName();
    private Unbinder unbinder;
    private Observable<SignUpModel> signUpButtonObservable;
    private AuthActivityCallback activityCallback;
    private List<GroupModel> models;

    @OnClick(R.id.btn_login)
    void onLogin() {
        activityCallback.navigateToAuth();
    }

    @OnClick(R.id.btn_groups)
    void onGroups() {
        showDialog();
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
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState
    ) {
        View view = inflater.inflate(R.layout.fragment_signup, null);
        unbinder = ButterKnife.bind(this, view);
        signUpButtonObservable = RxView.clicks(btnRegister).share().map(o -> getModel());
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

    @NonNull
    @Override
    public SignUpPresenter createPresenter() {
        return App.getDependencyInjection(getActivity()).newSignUpPresenter();
    }

    @Override
    public Observable<Boolean> getData() {
        return Observable.just(true);
    }

    @Override
    public Observable<SignUpModel> signUp() {
        return signUpButtonObservable;
    }

    @Override
    public void render(BaseViewState state) {
        if (state instanceof SignUpViewState.LoadingState) {
            renderLoading();
        } else if (state instanceof SignUpViewState.GroupsDataState) {
            renderGroups(((SignUpViewState.GroupsDataState) state).getGroupModels());
        } else if (state instanceof SignUpViewState.ErrorState) {
            renderError(((SignUpViewState.ErrorState) state).getError());
        } else if (state instanceof SignUpViewState.FieldErrorState) {
            renderFieldError(((SignUpViewState.FieldErrorState) state).getFieldType());
        } else if (state instanceof BaseViewState.DataStateBase) {
            renderSignUpOk(((BaseViewState.DataStateBase) state).getViewObject());
        }
    }

    @Override
    public void onGroupClick(GroupModel group) {
        groupName.setText(group.getName());
    }

    private void renderSignUpOk(BaseModel model) {
        if (model instanceof UserModel) {
            progressBar.setVisibility(View.GONE);
            activityCallback.loggedIn((UserModel) model);
        }
    }

    private void showDialog() {
        DialogFragment dialog = GroupsPickerDialog.newInstance(models);
        dialog.setTargetFragment(this, DIALOG_REQUEST_CODE);
        dialog.show(getActivity().getSupportFragmentManager(), GroupsPickerDialog.TAG);
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
                setNameError();
                break;
            case TYPE_ERROR_EMAIL:
                setEmailError();
                break;
            case TYPE_ERROR_PASSWORD:
                setPasswordError();
                break;
            case TYPE_ERROR_NAME:
                setNameError();
                break;
        }
    }

    private void renderError(Throwable error) {
        progressBar.setVisibility(View.GONE);
        Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
        Log.i(TAG, error.getLocalizedMessage());
    }

    private void renderGroups(List<GroupModel> groupModels) {
        progressBar.setVisibility(View.GONE);
        btnRegister.setEnabled(true);
        groupName.setEnabled(true);
        this.models = groupModels;
        groupName.setText(groupModels.get(0).getName());
    }

    private void initEditTexts() {
        FrameLayout frameLayout = (FrameLayout) email.getParent();
        LinearLayout.LayoutParams params =
                (LinearLayout.LayoutParams) frameLayout.getLayoutParams();
        params.setMargins(0, 0, 0, 1);
        frameLayout.setLayoutParams(params);
        frameLayout = (FrameLayout) password.getParent();
        frameLayout.setLayoutParams(params);
        frameLayout = (FrameLayout) name.getParent();
        frameLayout.setLayoutParams(params);

        name.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_UNSPECIFIED) {
                btnRegister.performClick();
                return true;
            }
            return false;
        });
        fixErrorUpdate();
    }

    private void fixErrorUpdate() {
        email.addTextChangedListener(new TextWatcher() {
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

        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                inputLayoutName.setError(null);
                inputLayoutName.setErrorEnabled(false);
            }
        });
    }

    private SignUpModel getModel() {
        return new SignUpModel(
                name.getText().toString(),
                email.getText().toString(),
                password.getText().toString(),
                getSelectedGroupId()
        );
    }

    private Long getSelectedGroupId() {
        String name = groupName.getText().toString();
        for (GroupModel model : models) {
            if (name.equals(model.getName())) {
                return model.getId();
            }
        }
        return null;
    }

    private void setPasswordError() {
        inputLayoutPassword.setErrorEnabled(true);
        inputLayoutPassword.setError("Пароль должен быть более 4 символов");
    }

    private void setEmailError() {
        inputLayoutEmail.setErrorEnabled(true);
        inputLayoutEmail.setError("Email должен быть более 4 символов");
    }

    private void setNameError() {
        inputLayoutName.setErrorEnabled(true);
        inputLayoutName.setError("Слишком короткое имя");
    }
}
