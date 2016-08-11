package app.rxdemo.presenter;

import java.util.ArrayList;
import java.util.List;

import app.rxdemo.model.Beer;
import app.rxdemo.model.service.OntarioService;
import app.rxdemo.model.service.ServiceFactory;
import app.rxdemo.presenter.subscribers.BeerSubscriber;
import app.rxdemo.ui.adapter.BeerAdapter;
import app.rxdemo.ui.fragments.BeerView;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by atempa on 26/07/16.
 */
public class BeerPresenterImpl implements BeerPresenter {
    private BeerView view = null;
    private BeerAdapter adapter = null;
    private OntarioService service = null;
    private Subscription subscription = null;
    private Observable<List<Beer>> observable = null;

    public BeerPresenterImpl(BeerView view) {
        this.view = view;
        bind();
    }

    @Override
    public void bind() {
        view.setPresenter(this);
        view.showProgressBar();
        adapter = new BeerAdapter(new ArrayList<Beer>());
        view.getRecyclerView().setAdapter(adapter);
        service = ServiceFactory.createRetrofitService(OntarioService.class, OntarioService.SERVICE_ENDPOINT);
    }

    @Override
    public BeerView geView() {
        return view;
    }

    @Override
    public BeerAdapter getAdapter() {
        return adapter;
    }

    @Override
    public void clear() {
        adapter.clear();
    }

    @Override
    public void fetch(boolean sort) {
        //Setup observable
        if(sort)
            observable = getAllSortedBeers();
        else
            observable = getAllBeers();
        //Setup subscription
        subscription = observable
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new BeerSubscriber(this));
    }

    private Observable<List<Beer>> getAllBeers() {
        return service.getBeers();
    }

    private Observable<List<Beer>> getAllSortedBeers() {
        return service.getBeers()
                .flatMap(new Func1<List<Beer>, Observable<Beer>>() {
                    @Override
                    public Observable<Beer> call(List<Beer> beers) {
                        return Observable.from(beers);
                    }
                })
                .toSortedList();
    }

    @Override
    public void onDestroy() {
        if (subscription != null && !subscription.isUnsubscribed())
            subscription.unsubscribe();
    }
}