package gusev.max.readytostudy.presentation.viewholder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import gusev.max.readytostudy.R;
import gusev.max.readytostudy.presentation.base.BaseModel;

/**
 * Created by v on 28/01/2018.
 */

public class MainViewHolder<M extends BaseModel> extends RecyclerView.ViewHolder {

    public interface MainClickListener<M extends BaseModel> {
        void onMainModelClick(M model);
    }

    public static MainViewHolder create(LayoutInflater inflater, MainClickListener listener) {
        return new MainViewHolder(inflater.inflate(R.layout.item_theme, null, false), listener);
    }

    @BindView(R.id.theme_name)
    TextView name;

    private M model;

    private MainViewHolder(View itemView, MainClickListener<M> clickListener) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(v -> clickListener.onMainModelClick(model));
    }

    public void bind(@NonNull M model) {
        this.model = model;
        name.setText(this.model.getName());
    }
}
