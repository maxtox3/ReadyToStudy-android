package gusev.max.readytostudy.di;

import java.util.concurrent.TimeUnit;

import gusev.max.readytostudy.BuildConfig;
import gusev.max.readytostudy.data.api.AuthApi;
import gusev.max.readytostudy.data.api.DisciplinesApi;
import gusev.max.readytostudy.data.api.LoggingInterceptor;
import gusev.max.readytostudy.data.api.TestApi;
import gusev.max.readytostudy.data.api.ThemesApi;
import gusev.max.readytostudy.data.repository.auth.AuthRepository;
import gusev.max.readytostudy.data.repository.auth.AuthRepositoryImpl;
import gusev.max.readytostudy.data.repository.disciplines.DisciplinesRepository;
import gusev.max.readytostudy.data.repository.disciplines.DisciplinesRepositoryImpl;
import gusev.max.readytostudy.data.repository.tests.TestRepository;
import gusev.max.readytostudy.data.repository.tests.TestRepositoryImpl;
import gusev.max.readytostudy.data.repository.themes.ThemesRepository;
import gusev.max.readytostudy.data.repository.themes.ThemesRepositoryImpl;
import gusev.max.readytostudy.domain.interactor.AuthInteractor;
import gusev.max.readytostudy.domain.interactor.DisciplineInteractor;
import gusev.max.readytostudy.domain.interactor.TaskInteractor;
import gusev.max.readytostudy.domain.interactor.ThemeInteractor;
import gusev.max.readytostudy.domain.mapper.AuthMapper;
import gusev.max.readytostudy.domain.mapper.DisciplineEntityToModelMapper;
import gusev.max.readytostudy.domain.mapper.GroupEntityToModelMapper;
import gusev.max.readytostudy.domain.mapper.MainMapper;
import gusev.max.readytostudy.domain.mapper.TaskEntityToModelMapper;
import gusev.max.readytostudy.domain.mapper.TestEntityToModelMapper;
import gusev.max.readytostudy.domain.mapper.ThemeEntityToModelMapper;
import gusev.max.readytostudy.domain.mapper.UserEntityToModelMapper;
import gusev.max.readytostudy.presentation.auth.login.AuthPresenter;
import gusev.max.readytostudy.presentation.auth.signup.SignUpPresenter;
import gusev.max.readytostudy.presentation.main.disciplines.DisciplinesListPresenter;
import gusev.max.readytostudy.presentation.main.themes.ThemesListPresenter;
import gusev.max.readytostudy.presentation.test.task.TasksPresenter;
import gusev.max.readytostudy.utils.SharedPrefManager;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by v on 13/01/2018.
 */

public class DependencyInjection {

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


    //<----------------------api---------------------->
    private final AuthApi authApi = retrofit.create(AuthApi.class);
    private final DisciplinesApi disciplinesApi = retrofit.create(DisciplinesApi.class);
    private final ThemesApi themesApi = retrofit.create(ThemesApi.class);
    private final TestApi testApi = retrofit.create(TestApi.class);


    //<----------------------repository---------------------->
    private final AuthRepository authRepository = new AuthRepositoryImpl(authApi);
    private final DisciplinesRepository disciplinesRepository = new DisciplinesRepositoryImpl(
        disciplinesApi);
    private final ThemesRepository themesRepository = new ThemesRepositoryImpl(themesApi);
    private final TestRepository testRepository = new TestRepositoryImpl(testApi);

    private AuthRepository getAuthRepository() {
        return authRepository;
    }

    private DisciplinesRepository getDisciplinesRepository() {
        return disciplinesRepository;
    }

    private ThemesRepository getThemesRepository() {
        return themesRepository;
    }

    private TestRepository getTestRepository() {
        return testRepository;
    }


    //<----------------------mappers---------------------->
    private final UserEntityToModelMapper userEntityToModelMapper = new UserEntityToModelMapper();
    private final GroupEntityToModelMapper groupEntityToModelMapper = new GroupEntityToModelMapper();
    private final AuthMapper authMapper = new AuthMapper(userEntityToModelMapper,
        groupEntityToModelMapper);

    private AuthMapper getAuthMapper() {
        return authMapper;
    }

    private final DisciplineEntityToModelMapper disciplineEntityToModelMapper = new DisciplineEntityToModelMapper();
    private ThemeEntityToModelMapper themeEntityToModelMapper = new ThemeEntityToModelMapper();
    private TestEntityToModelMapper testEntityToModelMapper = new TestEntityToModelMapper();
    private TaskEntityToModelMapper taskEntityToModelMapper = new TaskEntityToModelMapper();
    private MainMapper mainMapper = new MainMapper(disciplineEntityToModelMapper,
        themeEntityToModelMapper,
        testEntityToModelMapper, taskEntityToModelMapper);

    private MainMapper getMainMapper() {
        return mainMapper;
    }


    //<----------------------utils---------------------->
    public SharedPrefManager getPrefManager() {
        return SharedPrefManager.getInstance();
    }


    //<----------------------interactor---------------------->
    private AuthInteractor authInteractor = new AuthInteractor(getAuthRepository(),
        getAuthMapper(),
        getPrefManager());

    private DisciplineInteractor disciplineInteractor = new DisciplineInteractor(getMainMapper(),
        getDisciplinesRepository(),
        getAuthRepository(),
        getPrefManager());

    private ThemeInteractor themesInteractor = new ThemeInteractor(getThemesRepository(),
        getMainMapper(),
        getPrefManager(),
        getAuthRepository());

    private TaskInteractor taskInteractor = new TaskInteractor(getTestRepository(),
        getMainMapper(),
        getPrefManager(),
        getAuthRepository());

    public AuthInteractor getAuthInteractor() {
        return authInteractor;
    }

    public DisciplineInteractor getDisciplinesInteractor() {
        return disciplineInteractor;
    }

    public ThemeInteractor getThemesInteractor() {
        return themesInteractor;
    }

    public TaskInteractor getTaskInteractor() {
        return taskInteractor;
    }


    //<----------------------presenter---------------------->
    public AuthPresenter newAuthPresenter() {
        return new AuthPresenter(getAuthInteractor());
    }

    public SignUpPresenter newSignUpPresenter() {
        return new SignUpPresenter(getAuthInteractor());
    }

    public DisciplinesListPresenter newDispciplinesListPresenter() {
        return new DisciplinesListPresenter(getDisciplinesInteractor());
    }

    public ThemesListPresenter newThemesListPresenter() {
        return new ThemesListPresenter(getThemesInteractor());
    }

    public TasksPresenter newTaskPresenter() {
        return new TasksPresenter(getTaskInteractor());
    }
}
