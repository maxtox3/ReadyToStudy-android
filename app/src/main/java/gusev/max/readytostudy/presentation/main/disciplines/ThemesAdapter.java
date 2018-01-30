package gusev.max.readytostudy.presentation.main.disciplines;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import gusev.max.readytostudy.domain.model.ThemeModel;
import gusev.max.readytostudy.presentation.viewholder.ThemeViewHolder;

/**
 * Created by v on 28/01/2018.
 */

public class ThemesAdapter extends RecyclerView.Adapter<ThemeViewHolder> {

    private final LayoutInflater inflater;
    private final ThemeViewHolder.ThemeClickListener themeClickListener;

    private List<ThemeModel> themeModelList;

    ThemesAdapter(LayoutInflater inflater, ThemeViewHolder.ThemeClickListener
        clickListener) {
        this.inflater = inflater;
        this.themeClickListener = clickListener;
    }

    @Override public ThemeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ThemeViewHolder.create(inflater, themeClickListener);
    }

    @Override
    public void onBindViewHolder(ThemeViewHolder holder, int position) {
        holder.bind(themeModelList.get(position));
    }

    @Override
    public int getItemCount() {
        return themeModelList == null ? 0 : themeModelList.size();
    }

    final void setData(@NonNull List<ThemeModel> values) {
        this.themeModelList= values;
        notifyDataSetChanged();
    }

}