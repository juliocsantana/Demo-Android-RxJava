package app.rxdemo.presenter.subscribers;

import android.util.Log;

import java.util.List;

import app.rxdemo.model.Product;
import app.rxdemo.presenter.ProductPresenter;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

/**
 * Created by atempa on 26/07/16.
 */
public class ProductSubscriber extends Subscriber<List<Product>> {
    private ProductPresenter presenter;

    public ProductSubscriber(ProductPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public final void onCompleted() {
        if(presenter != null) {
            presenter.geView().hideProgressBar();
            presenter.geView().showSnackBar();
        }
    }

    @Override
    public final void onError(Throwable e) {
        Log.e("Products tab ", e.getMessage());
    }

    @Override
    public final void onNext(List<Product> products) {
        Observable.from(products)
                .forEach(new Action1<Product>() {
                    @Override
                    public void call(Product product) {
                        presenter.getAdapter().addProduct(product);
                    }
                });
    }
}
