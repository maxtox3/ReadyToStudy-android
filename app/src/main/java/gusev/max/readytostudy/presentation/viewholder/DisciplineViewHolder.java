package gusev.max.readytostudy.presentation.viewholder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import gusev.max.readytostudy.R;
import gusev.max.readytostudy.domain.model.DisciplineModel;

/**
 * Created by v on 28/01/2018.
 */

public class DisciplineViewHolder extends RecyclerView.ViewHolder {

    public interface DisciplineClickListener {
        void onDisciplineClick(DisciplineModel discipline);
    }

    public static DisciplineViewHolder create(LayoutInflater inflater, DisciplineClickListener listener) {
        return new DisciplineViewHolder(inflater.inflate(R.layout.item_discipline, null, false),
            listener);
    }

    @BindView(R.id.discipline_name)
    TextView name;

    private DisciplineModel disciplineModel;

    private DisciplineViewHolder(View itemView, DisciplineClickListener clickListener) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(v -> clickListener.onDisciplineClick(disciplineModel));
    }

    public void bind(@NonNull DisciplineModel model) {
        this.disciplineModel = model;
        name.setText(disciplineModel.getName());
    }
}
