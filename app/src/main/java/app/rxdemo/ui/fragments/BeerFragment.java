package app.rxdemo.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
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
import app.rxdemo.presenter.BeerPresenter;
import app.rxdemo.presenter.BeerPresenterImpl;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BeerFragment extends Fragment implements BeerView {
    @BindView(R.id.container)
    RelativeLayout container;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    private View view;
    private BeerPresenter presenter;
    private String msj = null;
    private boolean sortData = false;
    public static final String STATE_SORTDATA = "beersSorted";

    public BeerFragment() {
        // Required empty public constructor
    }

    public static BeerFragment newInstance() {
        return new BeerFragment();
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
        presenter = new BeerPresenterImpl(this);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(savedInstanceState != null)
            sortData = savedInstanceState.getBoolean(STATE_SORTDATA);

        presenter.fetch(sortData);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(STATE_SORTDATA, sortData);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(savedInstanceState != null)
            sortData = savedInstanceState.getBoolean(STATE_SORTDATA);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        presenter.fetch(sortData);
    }

    @Override
    public void onDestroy() {
        presenter.onDestroy();
        presenter = null;
        super.onDestroy();
    }

    @Override
    public void setPresenter(BeerPresenter presenter) {
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
