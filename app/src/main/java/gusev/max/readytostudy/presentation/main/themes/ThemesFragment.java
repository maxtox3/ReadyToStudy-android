package gusev.max.readytostudy.presentation.main.themes;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.hannesdorfmann.mosby3.mvi.MviFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import gusev.max.readytostudy.App;
import gusev.max.readytostudy.R;
import gusev.max.readytostudy.domain.model.TestModel;
import gusev.max.readytostudy.domain.model.ThemeModel;
import gusev.max.readytostudy.presentation.base.BaseViewState;
import gusev.max.readytostudy.presentation.main.MainActivityCallback;
import gusev.max.readytostudy.presentation.main.MainAdapter;
import gusev.max.readytostudy.presentation.test.TestActivity;
import gusev.max.readytostudy.presentation.viewholder.MainViewHolder;
import io.reactivex.Observable;

import static gusev.max.readytostudy.domain.model.ThemeModel.THEME_MODEL;

/**
 * Created by v on 01/02/2018.
 */
public class ThemesFragment extends MviFragment<ThemesListView, ThemesListPresenter> implements ThemesListView, MainViewHolder.MainClickListener<TestModel> {

    public static final String TAG = ThemesFragment.class.getName();
    @BindView(R.id.main_recycler)
    RecyclerView testsRecycler;
    @BindView(R.id.error_view)
    TextView errorView;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.collapsing)
    CollapsingToolbarLayout collapsing;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private Long selectedThemeId = -1L;
    private ThemeModel theme;
    private Unbinder unbinder;
    private MainAdapter<TestModel> testsAdapter;
    private MainActivityCallback activityCallback;

    public static Fragment newInstance(Bundle args) {
        Fragment fragment = new ThemesFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        if (getArguments() != null) {
            theme = (ThemeModel) getArguments().getSerializable(THEME_MODEL);
        }
        try {
            activityCallback = (MainActivityCallback) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement activityCallback");
        }
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(
        @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_themes, container, false);
        unbinder = ButterKnife.bind(this, view);
        setupWidgets();

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }

    private void setupWidgets() {
        //todo грузануть картинку темы и добавить ее
        toolbar.setNavigationOnClickListener(v -> {
            getActivity().onBackPressed();
        });
        collapsing.setTitle(theme.getName());

        testsAdapter = new MainAdapter<>(LayoutInflater.from(getActivity()), this);
        testsRecycler.setAdapter(testsAdapter);
        testsRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @NonNull
    @Override
    public ThemesListPresenter createPresenter() {
        return App.getDependencyInjection(getActivity()).newThemesListPresenter();
    }

    @Override
    public Observable<Long> getData() {
        return Observable.just(theme.getId());
    }

    @Override
    public void render(BaseViewState state) {
        if (state instanceof ThemesViewState.LoadingState) {
            renderLoading();
        } else if (state instanceof ThemesViewState.DataState) {
            renderData(((BaseViewState.DataState) state).getViewObject());
        } else if (state instanceof ThemesViewState.ErrorState) {
            renderError(((ThemesViewState.ErrorState) state).getError());
        }
    }

    private void renderData(Object viewObject) {
        if (viewObject instanceof List) {
            testsAdapter.setData((List<TestModel>) viewObject);
        }

        progressBar.setVisibility(View.GONE);
        errorView.setVisibility(View.GONE);
    }

    private void renderLoading() {
        errorView.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }

    private void renderError(Throwable error) {
        errorView.setVisibility(View.VISIBLE);
        errorView.setText(error.getMessage());
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onMainModelClick(TestModel model) {
        Intent intent = new Intent(getContext(), TestActivity.class);
        intent.putExtra(TestModel.TEST_MODEL, model);
        startActivity(intent);
    }
}
