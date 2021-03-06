package io.fikrims.cataloguemovie.feature.main.UpcomingMovie;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.fikrims.cataloguemovie.R;
import io.fikrims.cataloguemovie.data.model.response.MovieResult;
import io.fikrims.cataloguemovie.data.remote.BaseApiService;
import io.fikrims.cataloguemovie.data.remote.UtilsApi;

/**
 * A simple {@link Fragment} subclass.
 */
public class UpcomingMovieFragment extends Fragment implements UpcomingMoviePresenter.UpcomingMovieListener {

    @BindView(R.id.swipeToRefresh)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.list_movie)
    RecyclerView listMovie;

    private UpcomingMovieAdapter upcomingMovieAdapter;
    private BaseApiService mApiService;
    private UpcomingMoviePresenter upcomingMoviePresenter;
    private Context context;
    private int columns;

    public UpcomingMovieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_upcoming_movie, container, false);
        ButterKnife.bind(this, view);
        context = getContext();
        mApiService = UtilsApi.getAPIService(context);
        upcomingMoviePresenter = new UpcomingMoviePresenter(context, mApiService, this);
        upcomingMoviePresenter.doMovieUpcoming();
        initView();

        return view;
    }

    void initView(){
        columns = getResources().getInteger(R.integer.collumn_count);
        upcomingMovieAdapter = new UpcomingMovieAdapter(getContext());
        listMovie.setLayoutManager(new GridLayoutManager(getActivity(), columns));
        listMovie.setHasFixedSize(true);
        listMovie.setItemAnimator(new DefaultItemAnimator());

        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setRefreshing(true);

        // show data in recyclerview

        swipeRefreshLayout.setOnRefreshListener(() -> {

            upcomingMovieAdapter.notifyDataSetChanged();
            upcomingMoviePresenter.doMovieUpcoming();

        });
    }

    @Override
    public void getMovie(List<MovieResult> list) {
        upcomingMovieAdapter.setMovieResult(list);
        listMovie.setAdapter(upcomingMovieAdapter);
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void error(Throwable throwable) {

    }
}
