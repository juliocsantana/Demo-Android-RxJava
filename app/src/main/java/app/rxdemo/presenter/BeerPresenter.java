package app.rxdemo.presenter;

import app.rxdemo.ui.adapter.BeerAdapter;
import app.rxdemo.ui.fragments.BeerView;

/**
 * Created by atempa on 26/07/16.
 */
public interface BeerPresenter {
    void clear();
    void unsubscribe();
    void bind();
    void fetch();
    void fetchSort();
    BeerView geView();
    BeerAdapter getAdapter();
}

