package app.rxdemo.presenter;

import java.util.ArrayList;
import java.util.List;

import app.rxdemo.model.Product;
import app.rxdemo.model.service.OntarioService;
import app.rxdemo.model.service.ServiceFactory;
import app.rxdemo.presenter.subscribers.ProductSubscriber;
import app.rxdemo.ui.adapter.ProductAdapter;
import app.rxdemo.ui.fragments.ProductView;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by atempa on 26/07/16.
 */
public class ProductPresenterImpl  implements ProductPresenter {
    private ProductView view = null;
    private ProductAdapter adapter = null;
    private OntarioService service = null;
    private Subscription subscription = null;
    private Observable<List<Product>> observable = null;

    public ProductPresenterImpl(ProductView view) {
        this.view = view;
        bind();
    }

    @Override
    public void bind() {
        view.setPresenter(this);
        view.showProgressBar();
        adapter = new ProductAdapter(new ArrayList<Product>());
        view.getRecyclerView().setAdapter(adapter);
        service = ServiceFactory.createRetrofitService(OntarioService.class, OntarioService.SERVICE_ENDPOINT);
    }

    @Override
    public ProductView geView() {
        return view;
    }

    @Override
    public ProductAdapter getAdapter() {
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
            observable = getAllSortedProducts();
        else
            observable = getAllProducts();
        //Setup subscription
        subscription = observable
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new ProductSubscriber(this));
    }

    private Observable<List<Product>> getAllProducts() {
        return service.getProducts().cache();
    }

    private Observable<List<Product>> getAllSortedProducts() {
        return service.getProducts()
                .cache()
                .flatMap(new Func1<List<Product>, Observable<Product>>() {
                    @Override
                    public Observable<Product> call(List<Product> products) {
                        return Observable.from(products);
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
