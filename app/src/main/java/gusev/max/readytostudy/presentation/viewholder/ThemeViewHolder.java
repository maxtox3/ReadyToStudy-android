package gusev.max.readytostudy.presentation.viewholder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import gusev.max.readytostudy.R;
import gusev.max.readytostudy.domain.model.ThemeModel;

/**
 * Created by v on 28/01/2018.
 */

public class ThemeViewHolder extends RecyclerView.ViewHolder {

    public interface ThemeClickListener {
        void onThemeClick(ThemeModel model);
    }

    public static ThemeViewHolder create(LayoutInflater inflater, ThemeClickListener listener) {
        return new ThemeViewHolder(inflater.inflate(R.layout.item_theme, null, false), listener);
    }

    @BindView(R.id.theme_name)
    TextView name;

    private ThemeModel themeModel;

    private ThemeViewHolder(View itemView, ThemeClickListener clickListener) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(v -> clickListener.onThemeClick(themeModel));
    }

    public void bind(@NonNull ThemeModel model) {
        this.themeModel = model;
        name.setText(themeModel.getName());
    }
}
