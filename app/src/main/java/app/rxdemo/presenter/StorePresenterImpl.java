package app.rxdemo.presenter;

import java.util.ArrayList;
import java.util.List;

import app.rxdemo.model.Store;
import app.rxdemo.model.service.OntarioService;
import app.rxdemo.model.service.ServiceFactory;
import app.rxdemo.presenter.subscribers.ProductSubscriber;
import app.rxdemo.presenter.subscribers.StoreSubscriber;
import app.rxdemo.ui.adapter.StoreAdapter;
import app.rxdemo.ui.fragments.StoreView;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by atempa on 31/07/16.
 */
public class StorePresenterImpl implements StorePresenter {
    private StoreView view = null;
    private StoreAdapter adapter = null;
    private OntarioService service = null;
    private Subscription subscription = null;

    public StorePresenterImpl(StoreView view) {
        this.view = view;
        bind();
    }

    @Override
    public void bind() {
        view.setPresenter(this);
        view.showProgressBar();
        adapter = new StoreAdapter(new ArrayList<Store>());
        view.getRecyclerView().setAdapter(adapter);
        service = ServiceFactory.createRetrofitService(OntarioService.class);
    }

    @Override
    public StoreView geView() {
        return view;
    }

    @Override
    public StoreAdapter getAdapter() {
        return adapter;
    }

    @Override
    public void clear() {
        adapter.clear();
    }

    @Override
    public void fetch() {
        //Setup subscription
        subscription = getAllStores()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new StoreSubscriber(this));
    }

    @Override
    public void fetchSort() {
        getAllSortedStores()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new StoreSubscriber(this));
    }

    private Observable<List<Store>> getAllStores() {
        return service.getStores().cache();
    }

    private Observable<List<Store>> getAllSortedStores() {
        return getAllStores()
                .flatMap(new Func1<List<Store>, Observable<Store>>() {
                    @Override
                    public Observable<Store> call(List<Store> stores) {
                        return Observable.from(stores);
                    }
                })
                .toSortedList();
    }

    @Override
    public void unsubscribe() {
        if (subscription != null && !subscription.isUnsubscribed())
            subscription.unsubscribe();
    }
}
