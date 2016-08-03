package app.rxdemo.presenter.subscribers;

import android.util.Log;

import java.util.List;

import app.rxdemo.model.Store;
import app.rxdemo.presenter.StorePresenter;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

/**
 * Created by atempa on 31/07/16.
 */
public class StoreSubscriber extends Subscriber<List<Store>> {
    private StorePresenter presenter;

    public StoreSubscriber(StorePresenter presenter) {
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
        Log.e("Stores tab ", e.getMessage());
    }

    @Override
    public final void onNext(List<Store> stores) {
        Observable.from(stores)
                .forEach(new Action1<Store>() {
                    @Override
                    public void call(Store store) {
                        presenter.getAdapter().addStore(store);
                    }
                });
    }
}
