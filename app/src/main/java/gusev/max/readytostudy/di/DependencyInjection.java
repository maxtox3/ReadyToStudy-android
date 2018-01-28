package gusev.max.readytostudy.di;

import java.util.concurrent.TimeUnit;

import gusev.max.readytostudy.BuildConfig;
import gusev.max.readytostudy.data.api.AuthApi;
import gusev.max.readytostudy.data.api.LoggingInterceptor;
import gusev.max.readytostudy.data.repository.AuthRepository;
import gusev.max.readytostudy.data.repository.AuthRepositoryImpl;
import gusev.max.readytostudy.domain.interactor.AuthInteractor;
import gusev.max.readytostudy.domain.mapper.AuthMapper;
import gusev.max.readytostudy.domain.mapper.GroupEntityToModelMapper;
import gusev.max.readytostudy.domain.mapper.UserEntityToModelMapper;
import gusev.max.readytostudy.presentation.auth.login.AuthPresenter;
import gusev.max.readytostudy.presentation.auth.signup.SignUpPresenter;
import gusev.max.readytostudy.utils.SharedPrefManager;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by v on 13/01/2018.
 */

public class DependencyInjection {

    private final String SHARED_PATH = "RTS";

    private final LoggingInterceptor httpLogger = new LoggingInterceptor();

    private final Retrofit retrofit = new Retrofit.Builder()
        .baseUrl(BuildConfig.API_ENDPOINT)
        .client(new OkHttpClient.Builder()
            .addInterceptor(httpLogger)
            .connectTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .build())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build();

    //api interfaces
    private final AuthApi authApi = retrofit.create(AuthApi.class);

    //repositories
    private final AuthRepository authRepository = new AuthRepositoryImpl(authApi);

    private AuthRepository getAuthRepository() {
        return authRepository;
    }

    //mappers
    private final UserEntityToModelMapper userEntityToModelMapper = new UserEntityToModelMapper();
    private final GroupEntityToModelMapper groupEntityToModelMapper = new GroupEntityToModelMapper();
    private final AuthMapper authMapper = new AuthMapper(userEntityToModelMapper,
        groupEntityToModelMapper);

    private AuthMapper getAuthMapper() {
        return authMapper;
    }

    //utils
    public SharedPrefManager getPrefManager(){
        return SharedPrefManager.getInstance();
    }

    //interactors
    private AuthInteractor authInteractor = new AuthInteractor(getAuthRepository(),
        getAuthMapper(), getPrefManager());

    public AuthInteractor getAuthInteractor() {
        return authInteractor;
    }

    //presenters
    public AuthPresenter newAuthPresenter() {
        return new AuthPresenter(getAuthInteractor());
    }

    public SignUpPresenter newSignUpPresenter() {
        return new SignUpPresenter(getAuthInteractor());
    }
}
