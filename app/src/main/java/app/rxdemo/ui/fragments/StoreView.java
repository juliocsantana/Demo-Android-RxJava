package app.rxdemo.ui.fragments;

import android.support.v7.widget.RecyclerView;

import app.rxdemo.presenter.StorePresenter;

/**
 * Created by atempa on 31/07/16.
 */
public interface StoreView {
    void setPresenter(StorePresenter presenter);
    void showProgressBar();
    void hideProgressBar();
    void showSnackBar();
    RecyclerView getRecyclerView();
}
