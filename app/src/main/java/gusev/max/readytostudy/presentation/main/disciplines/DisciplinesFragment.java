package gusev.max.readytostudy.presentation.main.disciplines;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
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
import gusev.max.readytostudy.domain.model.DisciplineModel;
import gusev.max.readytostudy.domain.model.ThemeModel;
import gusev.max.readytostudy.presentation.base.BaseViewState;
import gusev.max.readytostudy.presentation.main.MainActivityCallback;
import gusev.max.readytostudy.presentation.main.MainAdapter;
import gusev.max.readytostudy.presentation.main.TopAdapter;
import gusev.max.readytostudy.presentation.viewholder.MainViewHolder;
import io.reactivex.Observable;

/**
 * Created by v on 28/01/2018.
 */

public class DisciplinesFragment extends MviFragment<DisciplinesListView,
        DisciplinesListPresenter> implements DisciplinesListView, MainViewHolder
        .MainClickListener<ThemeModel> {

    public static final String TAG = DisciplinesFragment.class.getName();
    @BindView(R.id.top_recycler)
    RecyclerView disciplinesRecycler;
    @BindView(R.id.main_recycler)
    RecyclerView themesRecycler;
    @BindView(R.id.error_view)
    TextView errorView;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    private Long selectedDisciplineId = -1L;
    private Unbinder unbinder;
    private TopAdapter<DisciplineModel> disciplinesAdapter;
    private MainAdapter<ThemeModel> themesAdapter;
    private MainActivityCallback activityCallback;

    @Override
    public void onAttach(Context context) {
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
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState
    ) {
        View view = inflater.inflate(R.layout.fragment_disciplines, container, false);
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
        disciplinesAdapter = new TopAdapter<>(LayoutInflater.from(getActivity()));
        disciplinesRecycler.setAdapter(disciplinesAdapter);
        disciplinesRecycler.setLayoutManager(new LinearLayoutManager(
                getActivity(),
                LinearLayoutManager.HORIZONTAL,
                false
        ));

        themesAdapter = new MainAdapter<>(LayoutInflater.from(getActivity()), this);
        themesRecycler.setAdapter(themesAdapter);
        themesRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @NonNull
    @Override
    public DisciplinesListPresenter createPresenter() {
        return App.getDependencyInjection(getActivity()).newDispciplinesListPresenter();
    }

    @Override
    public Observable<Boolean> getData() {
        return Observable.just(true);
    }

    @Override
    public Observable<Long> selectDisciplineIntent() {
        return disciplinesAdapter.getSelectedItemObservable().doOnNext(this::setSelectedDiscipline);
    }

    private void setSelectedDiscipline(Long disciplineId) {
        this.selectedDisciplineId = disciplineId;
        disciplinesAdapter.setSelectedModel(disciplineId);
    }

    @Override
    public void render(BaseViewState state) {
        if (state instanceof DisciplinesViewState.LoadingState) {
            renderLoading();
        } else if (state instanceof DisciplinesViewState.DataState) {
            renderData(((DisciplinesViewState.DataState) state).getViewObject());
        } else if (state instanceof DisciplinesViewState.ErrorState) {
            renderError(((BaseViewState.ErrorState) state).getError());
        }
    }

    private void renderData(Object viewObject) {
        if (viewObject instanceof Pair) {
            disciplinesAdapter
                    .setData(((Pair<List<DisciplineModel>, List<ThemeModel>>) viewObject).first);
            themesAdapter
                    .setData(((Pair<List<DisciplineModel>, List<ThemeModel>>) viewObject).second);
        } else if (viewObject instanceof List) {
            themesAdapter.setData((List<ThemeModel>) viewObject);
        }
        disciplinesRecycler.setVisibility(View.VISIBLE);
        themesRecycler.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
        errorView.setVisibility(View.GONE);
    }

    private void renderLoading() {
        disciplinesRecycler.setVisibility(View.GONE);
        themesRecycler.setVisibility(View.GONE);
        errorView.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }

    private void renderError(Throwable error) {
        progressBar.setVisibility(View.GONE);
        disciplinesRecycler.setVisibility(View.GONE);
        themesRecycler.setVisibility(View.GONE);
        errorView.setVisibility(View.VISIBLE);
        errorView.setText(error.getMessage());
    }

    @Override
    public void onMainModelClick(ThemeModel model) {
        activityCallback.navigateToThemes(model);
    }
}
