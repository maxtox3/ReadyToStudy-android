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

public class TopViewHolder<M extends BaseModel> extends RecyclerView.ViewHolder {

    public interface TopClickListener<M extends BaseModel> {
        void onTopClick(M discipline);
    }

    public static TopViewHolder create(LayoutInflater inflater, TopClickListener listener) {
        return new TopViewHolder(inflater.inflate(R.layout.item_discipline, null, false),
            listener);
    }

    @BindView(R.id.top_name)
    TextView name;

    private M model;

    private TopViewHolder(View itemView, TopClickListener<M> clickListener) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(v -> clickListener.onTopClick(model));
    }

    public void bind(@NonNull M model) {
        this.model = model;
        name.setText(this.model.getName());
    }
}
