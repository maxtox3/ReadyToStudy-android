package gusev.max.readytostudy.presentation.main;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import gusev.max.readytostudy.R;
import gusev.max.readytostudy.presentation.base.BaseModel;
import gusev.max.readytostudy.presentation.viewholder.TopViewHolder;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by v on 28/01/2018.
 */

public class TopAdapter<M extends BaseModel> extends RecyclerView.Adapter<TopViewHolder<M>> implements TopViewHolder.TopClickListener<M> {

    private final String LIGHT_BLUE = "#1E88E5";
    private final String WHITE = "#808080";

    private final LayoutInflater layoutInflater;
    private List<M> models;
    private int positionSelected = 0;
    private PublishSubject<Long> clickSubject = PublishSubject.create();

    public TopAdapter(LayoutInflater layoutInflater) {
        this.layoutInflater = layoutInflater;
    }

    @Override
    public TopViewHolder<M> onCreateViewHolder(ViewGroup parent, int viewType) {
        return TopViewHolder.create(layoutInflater, this);
    }

    @Override
    public void onBindViewHolder(TopViewHolder<M> holder, int position) {
        TextView btn = holder.itemView.findViewById(R.id.top_name);
        if (position == positionSelected) {
            btn.setTextColor(Color.parseColor(LIGHT_BLUE));
        } else {
            btn.setTextColor(Color.parseColor(WHITE));
        }

        holder.bind(models.get(position));
    }

    @Override
    public int getItemCount() {
        return models == null ? 0 : models.size();
    }

    public void setData(@NonNull List<M> values) {
        this.models = values;
        notifyDataSetChanged();
    }

    public Observable<Long> getSelectedItemObservable() {
        return clickSubject;
    }

    public void setSelectedModel(Long idOfSelectedModel) {
        for (int i = 0; i < models.size(); i++) {
            if (models.get(i).getId().equals(idOfSelectedModel)) {
                positionSelected = i;
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public void onTopClick(M model) {
        clickSubject.onNext(model.getId());
    }
}
