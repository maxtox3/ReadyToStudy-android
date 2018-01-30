package gusev.max.readytostudy.presentation.main.disciplines;

import gusev.max.readytostudy.presentation.base.BaseViewState;

/**
 * Created by v on 28/01/2018.
 */

public interface DisciplinesViewState extends BaseViewState {

    final class DataState<T> implements DisciplinesViewState{
        private final T viewObject;

        public DataState(T viewObject) {
            this.viewObject = viewObject;
        }

        public T getViewObject() {
            return viewObject;
        }
    }
}
