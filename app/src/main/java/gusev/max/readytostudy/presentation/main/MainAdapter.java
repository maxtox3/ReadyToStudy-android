package gusev.max.readytostudy.presentation.main;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import gusev.max.readytostudy.presentation.base.BaseModel;
import gusev.max.readytostudy.presentation.viewholder.MainViewHolder;

/**
 * Created by v on 28/01/2018.
 */

public class MainAdapter<M extends BaseModel> extends RecyclerView.Adapter<MainViewHolder<M>> {

    private final LayoutInflater inflater;
    private final MainViewHolder.MainClickListener<M> clickListener;

    private List<M> models;

    public MainAdapter(
        LayoutInflater inflater, MainViewHolder.MainClickListener<M> clickListener) {
        this.inflater = inflater;
        this.clickListener = clickListener;
    }

    @Override public MainViewHolder<M> onCreateViewHolder(ViewGroup parent, int viewType) {
        return MainViewHolder.create(inflater, clickListener);
    }

    @Override
    public void onBindViewHolder(MainViewHolder<M> holder, int position) {
        holder.bind(models.get(position));
    }

    @Override
    public int getItemCount() {
        return models == null ? 0 : models.size();
    }

    public final void setData(@NonNull List<M> values) {
        this.models = values;
        notifyDataSetChanged();
    }

}