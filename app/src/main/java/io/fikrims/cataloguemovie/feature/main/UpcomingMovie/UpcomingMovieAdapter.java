package io.fikrims.cataloguemovie.feature.main.UpcomingMovie;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.fikrims.cataloguemovie.R;
import io.fikrims.cataloguemovie.data.model.response.MovieResult;
import io.fikrims.cataloguemovie.utils.Constant;

public class UpcomingMovieAdapter extends RecyclerView.Adapter<UpcomingMovieAdapter.ViewHolder> {

    private Context context;
    private List<MovieResult> listMovie;

    UpcomingMovieAdapter(Context context){
        this.listMovie = new ArrayList<>();
        this.context = context;
    }

    public void setMovieResult(List<MovieResult> listMovie){
        this.listMovie = listMovie;
    }

    @NonNull
    @Override
    public UpcomingMovieAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        @SuppressLint("InflateParams") View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_movie, null);
        // create ViewHolder
        return new ViewHolder(itemLayoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull UpcomingMovieAdapter.ViewHolder holder, int position) {
        holder.bind(listMovie.get(position));
    }

    @Override
    public int getItemCount() {
        return listMovie.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image_movie)
        ImageView imageMovie;
        @BindView(R.id.text_title)
        TextView textTitle;
        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(MovieResult movieResult){
            Picasso.with(itemView.getContext())
                    .load(Constant.Utils.BASE_POSTER_URL+movieResult.getPosterPath())
                    .placeholder(R.mipmap.play)
                    .error(R.drawable.ic_refresh)
                    .into(imageMovie);
            imageMovie.setPadding(0,0,0,0);

            textTitle.setText(movieResult.getTitle());
        }
    }
}
