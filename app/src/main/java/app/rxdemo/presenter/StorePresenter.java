package app.rxdemo.presenter;

import app.rxdemo.ui.adapter.StoreAdapter;
import app.rxdemo.ui.fragments.StoreView;

/**
 * Created by atempa on 31/07/16.
 */
public interface StorePresenter {
    void clear();
    void unsubscribe();
    void bind();
    void fetch();
    void fetchSort();
    StoreView geView();
    StoreAdapter getAdapter();
}
