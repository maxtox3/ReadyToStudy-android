package gusev.max.readytostudy.presentation.main.disciplines;

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
import gusev.max.readytostudy.presentation.viewholder.ThemeViewHolder;
import io.reactivex.Observable;

/**
 * Created by v on 28/01/2018.
 */

public class DisciplinesFragment extends MviFragment<DisciplinesListView, DisciplinesListPresenter> implements DisciplinesListView, ThemeViewHolder.ThemeClickListener {

    public static final String TAG = DisciplinesFragment.class.getName();
    @BindView(R.id.disciplines_recycler)
    RecyclerView disciplinesRecycler;
    @BindView(R.id.themes_recycler)
    RecyclerView themesRecycler;
    @BindView(R.id.error_view)
    TextView errorView;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    private Unbinder unbinder;
    private DisciplinesAdapter disciplinesAdapter;
    private ThemesAdapter themesAdapter;

    @Nullable
    @Override
    public View onCreateView(
        @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
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
        disciplinesAdapter = new DisciplinesAdapter(LayoutInflater.from(getActivity()));
        disciplinesRecycler.setAdapter(disciplinesAdapter);
        disciplinesRecycler.setLayoutManager(new LinearLayoutManager(getActivity(),
            LinearLayoutManager.HORIZONTAL,
            false));

        themesAdapter = new ThemesAdapter(LayoutInflater.from(getActivity()), this);
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
//        return 0L;
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
            disciplinesAdapter.setData(((Pair<List<DisciplineModel>, List<ThemeModel>>) viewObject).first);
            themesAdapter.setData(((Pair<List<DisciplineModel>, List<ThemeModel>>) viewObject).second);
        } else if (viewObject instanceof List) {
            themesAdapter.setData((List<ThemeModel>) viewObject);
        }

        progressBar.setVisibility(View.GONE);
        errorView.setVisibility(View.GONE);
    }

    private void renderLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    private void renderError(Throwable error) {
        errorView.setVisibility(View.VISIBLE);
        errorView.setText(error.getMessage());
        progressBar.setVisibility(View.GONE);
//        Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onThemeClick(ThemeModel model) {

    }
}
