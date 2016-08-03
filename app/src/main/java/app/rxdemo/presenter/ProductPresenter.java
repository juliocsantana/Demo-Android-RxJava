package app.rxdemo.presenter;

import app.rxdemo.ui.adapter.ProductAdapter;
import app.rxdemo.ui.fragments.ProductView;

/**
 * Created by atempa on 26/07/16.
 */
public interface ProductPresenter {
    void clear();
    void onDestroy();
    void bind();
    void fetch(boolean sort);
    ProductView geView();
    ProductAdapter getAdapter();
}
