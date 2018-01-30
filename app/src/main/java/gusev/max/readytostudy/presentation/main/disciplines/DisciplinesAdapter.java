package gusev.max.readytostudy.presentation.main.disciplines;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import gusev.max.readytostudy.domain.model.DisciplineModel;
import gusev.max.readytostudy.presentation.viewholder.DisciplineViewHolder;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by v on 28/01/2018.
 */

public class DisciplinesAdapter extends RecyclerView.Adapter<DisciplineViewHolder> implements DisciplineViewHolder.DisciplineClickListener {

    private final String YELLOW = "#EC407A";
    private final String WHITE = "#FFFFFF";

    private final LayoutInflater layoutInflater;
    private List<DisciplineModel> models;
    private int positionSelected = 0;
    private PublishSubject<Long> disciplineClickSubject = PublishSubject.create();

    DisciplinesAdapter(LayoutInflater layoutInflater){
        this.layoutInflater = layoutInflater;
    }

    @Override public DisciplineViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return DisciplineViewHolder.create(layoutInflater, this);
    }

    @Override
    public void onBindViewHolder(DisciplineViewHolder holder, int position) {

        if (position == positionSelected) {
            holder.itemView.setBackgroundColor(Color.parseColor(YELLOW));
        } else {
            holder.itemView.setBackgroundColor(Color.parseColor(WHITE));
        }

        holder.bind(models.get(position));
    }

    @Override public int getItemCount() {
        return models == null ? 0 : models.size();
    }

    public void setData(@NonNull List<DisciplineModel> values) {
        this.models = values;
        notifyDataSetChanged();
    }

    Observable<Long> getSelectedItemObservable() {
        return disciplineClickSubject;
    }

    void setSelectedTopic(Long idOfSelectedTopic) {
        for(int i = 0; i < models.size(); i++){
            if(models.get(i).getId().equals(idOfSelectedTopic))
                positionSelected = i;
        }
        notifyDataSetChanged();
    }

    @Override
    public void onDisciplineClick(DisciplineModel discipline) {
        disciplineClickSubject.onNext(discipline.getId());
    }
}
