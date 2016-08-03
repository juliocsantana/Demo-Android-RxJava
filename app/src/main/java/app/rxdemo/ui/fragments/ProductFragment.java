package app.rxdemo.ui.fragments;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import app.rxdemo.R;
import app.rxdemo.presenter.ProductPresenter;
import app.rxdemo.presenter.ProductPresenterImpl;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductFragment extends Fragment implements ProductView {
    @BindView(R.id.container)
    RelativeLayout container;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    private View view;
    private ProductPresenter presenter;
    private String msj = null;
    private boolean sortData;
    static final String STATE_SORTDATA = "productsSorted";

    public ProductFragment() {
        // Required empty public constructor
    }

    public static ProductFragment newInstance() {
        return new ProductFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if(savedInstanceState != null)
            sortData = savedInstanceState.getBoolean(STATE_SORTDATA);
        else
            sortData = false;

        view = inflater.inflate(R.layout.fragment_beer, container, false);
        setHasOptionsMenu(true);
        ButterKnife.bind(this, view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        presenter = new ProductPresenterImpl(this);
        presenter.fetch(sortData);

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putBoolean(STATE_SORTDATA, sortData);
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.clear();
        presenter.fetch(sortData);
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.clear();
        presenter.fetch(sortData);
    }

    @Override
    public void onDestroy() {
        presenter.onDestroy();
        presenter = null;
        super.onDestroy();
    }

    @Override
    public void setPresenter(ProductPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showProgressBar() {
        if(presenter != null) {
            progressBar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void hideProgressBar() {
        if(presenter != null) {
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void showSnackBar() {
        if(presenter != null && sortData) {
            msj = "Data has been sorted successfully !";
            Snackbar.make(container, msj, Snackbar.LENGTH_SHORT).show();
        }
    }

    @Override
    public RecyclerView getRecyclerView() {
        if(presenter != null) {
            return mRecyclerView;
        }

        return null;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_sort) {
            sortData = true;
            presenter.clear();
            presenter.fetch(sortData);
        }

        return super.onOptionsItemSelected(item);
    }
}
