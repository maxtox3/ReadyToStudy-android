package gusev.max.readytostudy.presentation.base;

/**
 * Created by v on 27/01/2018.
 */

public interface BaseViewState {

    final class FieldErrorState implements BaseViewState {

        private final String typeOfError;

        public FieldErrorState(String typeOfError) {
            this.typeOfError = typeOfError;
        }

        public String getFieldType() {
            return typeOfError;
        }
    }

    final class LoadingState implements BaseViewState {
    }

    final class ErrorState implements BaseViewState {

        private final Throwable error;

        public ErrorState(Throwable error) {
            this.error = error;
        }

        public Throwable getError() {
            return error;
        }
    }

    final class DataStateBase<M extends BaseModel> implements BaseViewState {

        private final M viewObject;

        public DataStateBase(M viewObject) {
            this.viewObject = viewObject;
        }

        public M getViewObject() {
            return viewObject;
        }
    }

    final class DataState<T> implements BaseViewState{
        private final T viewObject;

        public DataState(T viewObject) {
            this.viewObject = viewObject;
        }

        public T getViewObject() {
            return viewObject;
        }
    }
}
