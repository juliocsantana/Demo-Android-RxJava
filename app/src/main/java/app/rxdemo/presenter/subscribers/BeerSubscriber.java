package app.rxdemo.presenter.subscribers;

import android.util.Log;

import java.util.List;

import app.rxdemo.model.Beer;
import app.rxdemo.presenter.BeerPresenter;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

/**
 * Created by atempa on 24/07/16.
 */
public class BeerSubscriber extends Subscriber<List<Beer>> {
    private BeerPresenter presenter;

    public BeerSubscriber(BeerPresenter presenter) {
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
        Log.e("Beers tab ", e.getMessage());
    }

    @Override
    public final void onNext(List<Beer> beers) {
        Observable.from(beers)
                .forEach(new Action1<Beer>() {
                    @Override
                    public void call(Beer beer) {
                        presenter.getAdapter().addBeer(beer);
                    }
                });
    }
}
