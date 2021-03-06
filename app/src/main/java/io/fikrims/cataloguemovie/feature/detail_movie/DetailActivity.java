package io.fikrims.cataloguemovie.feature.detail_movie;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.fikrims.cataloguemovie.R;
import io.fikrims.cataloguemovie.data.model.response.MovieResult;
import io.fikrims.cataloguemovie.utils.Constant;
import io.fikrims.cataloguemovie.utils.DateFormator;

public class DetailActivity extends AppCompatActivity {

    @BindView(R.id.detail_overview_tv)
    TextView tvOverview;
    @BindView(R.id.image_detail)
    ImageView backDrop;
    @BindView(R.id.item_date_detail)
    TextView tvDate;
    @BindView(R.id.item_title_detail)
    TextView tvTitle;
    @BindView(R.id.movie_poster_detail)
    ImageView poster;
    @BindView(R.id.item_rating_detial)
    TextView tvRating;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ButterKnife.bind(this);

        MovieResult movie = getIntent().getParcelableExtra(Constant.Utils.MOVIE_DETAIL);
        updateUI(movie);
    }

    void updateUI(MovieResult movie){
//        getSupportActionBar().setTitle(movie.getTitle());
        Picasso.with(this)
                .load(Constant.Utils.BASE_POSTER_URL+movie.getPosterPath())
                .placeholder(R.mipmap.play)
                .error(R.drawable.ic_refresh)
                .into(poster);

        Picasso.with(this)
                .load(Constant.Utils.BASE_BACKDROP_URL+movie.getBackdropPath())
                .placeholder(R.mipmap.play)
                .error(R.drawable.ic_refresh)
                .into(backDrop);
        tvTitle.setText(movie.getTitle());
        tvOverview.setText(movie.getOverview());
        tvDate.setText("Release Date : "+DateFormator.getDateDay(movie.getReleaseDate()));
        tvRating.setText("Rating : "+movie.getVoteAverage().toString());
    }
}
