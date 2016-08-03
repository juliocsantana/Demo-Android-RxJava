package app.rxdemo.ui.fragments;

import android.support.v7.widget.RecyclerView;

import app.rxdemo.presenter.BeerPresenter;

/**
 * Created by atempa on 26/07/16.
 */
public interface BeerView {
    void setPresenter(BeerPresenter presenter);
    void showProgressBar();
    void hideProgressBar();
    void showSnackBar();
    RecyclerView getRecyclerView();
}