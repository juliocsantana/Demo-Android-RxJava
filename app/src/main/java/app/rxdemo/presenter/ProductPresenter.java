package app.rxdemo.presenter;

import app.rxdemo.ui.adapter.ProductAdapter;
import app.rxdemo.ui.fragments.ProductView;

/**
 * Created by atempa on 26/07/16.
 */
public interface ProductPresenter {
    void clear();
    void unsubscribe();
    void bind();
    void fetch();
    void fetchSort();
    ProductView geView();
    ProductAdapter getAdapter();
}
