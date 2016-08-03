package app.rxdemo.model.service;

import java.util.List;

import app.rxdemo.model.Beer;
import app.rxdemo.model.Product;
import app.rxdemo.model.Store;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by atempa on 31/07/16.
 */
public interface OntarioService {
    String SERVICE_ENDPOINT = "http://ontariobeerapi.ca/";

    @GET("beers/")
    Observable<List<Beer>> getBeers();

    @GET("products/")
    Observable<List<Product>> getProducts();

    @GET("stores/")
    Observable<List<Store>> getStores();
}