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
import app.rxdemo.presenter.StorePresenter;
import app.rxdemo.presenter.StorePresenterImpl;
import butterknife.BindView;
import butterknife.ButterKnife;

public class StoreFragment extends Fragment implements StoreView {
    @BindView(R.id.container)
    RelativeLayout container;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    private View view;
    private StorePresenter presenter;
    private String msj = null;
    private boolean sortData = false;
    public static final String STATE_SORT = "storesSorted";

    public StoreFragment() {
        // Required empty public constructor
    }

    public static StoreFragment newInstance() {
        return new StoreFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_beer, container, false);
        setHasOptionsMenu(true);
        setRetainInstance(true);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(savedInstanceState != null)
            sortData = savedInstanceState.getBoolean(STATE_SORT);

        ButterKnife.bind(this, view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        presenter = new StorePresenterImpl(this);
        presenter.fetch();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(STATE_SORT, sortData);
    }

    @Override
    public void onDestroy() {
        if(presenter != null) {
            presenter.unsubscribe();
            presenter = null;
        }
        super.onDestroy();
    }

    @Override
    public void setPresenter(StorePresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showProgressBar() {
        if(presenter != null)
            progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        if(presenter != null)
            progressBar.setVisibility(View.GONE);
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
        return mRecyclerView;
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
            presenter.unsubscribe();
            presenter.clear();
            presenter.fetchSort();
        }

        return super.onOptionsItemSelected(item);
    }
}
