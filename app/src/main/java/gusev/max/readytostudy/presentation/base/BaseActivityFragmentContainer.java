package gusev.max.readytostudy.presentation.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by v on 27/01/2018.
 */

public abstract class BaseActivityFragmentContainer extends AppCompatActivity implements
        FragmentContainer {

    public final static String SAVED_FRAGMENT_TAG = "SAVED_FRAGMENT_TAG";
    private CompositeDisposable disposable;
    private String currentTag;
    private int containerId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContainerId();
        if (savedInstanceState != null) {
            currentTag = savedInstanceState.getString(SAVED_FRAGMENT_TAG);
            restoreFragment(currentTag);
        }
        disposable = new CompositeDisposable();
    }

    @Override
    protected void onDestroy() {
        if (disposable != null) {
            disposable.dispose();
        }
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStackImmediate();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (currentTag != null) {
            outState.putString(SAVED_FRAGMENT_TAG, currentTag);
        }
        super.onSaveInstanceState(outState);
    }

    protected void navigateToFragment(String tag, Bundle args, boolean addToBackStack) {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if (addToBackStack) {
            transaction.setTransition(android.app.FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            transaction.addToBackStack(null);
        }

        if (getSupportFragmentManager().findFragmentByTag(tag) != null) {
            replace(transaction, getSupportFragmentManager().findFragmentByTag(tag), tag);
        } else {
            Fragment fragment = createFragment(tag, args);
            add(transaction, fragment, tag);
        }
    }

    protected void addDisposable(Disposable disposable) {
        this.disposable.add(disposable);
    }

    protected void setContainerId(int containerId) {
        this.containerId = containerId;
    }

    protected abstract Fragment createFragment(String tag, Bundle args);

    private void restoreFragment(String tag) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (getSupportFragmentManager().findFragmentByTag(tag) != null) {
            replace(transaction, getSupportFragmentManager().findFragmentByTag(tag), tag);
        }
    }

    protected void setCurrentTag(String tag) {
        currentTag = tag;
    }

    private void replace(FragmentTransaction transaction, Fragment fragment, String tag) {
        transaction.replace(containerId, fragment, tag).commit();
    }

    private void add(FragmentTransaction transaction, Fragment fragment, String tag) {
        transaction.add(containerId, fragment, tag).commit();
    }
}


