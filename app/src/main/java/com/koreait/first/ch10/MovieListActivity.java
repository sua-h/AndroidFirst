package com.koreait.first.ch10;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Movie;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.koreait.first.R;
import com.koreait.first.ch10.searchmoviemodel.MovieVO;

import java.util.ArrayList;
import java.util.List;

public class MovieListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);
    }
}

class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MyViewHolder> {
    private List<MovieVO> list = new ArrayList<>();

    public void addItem(MovieVO vo) {
        list.add(vo);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.item_movie, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        MovieVO vo = list.get(position);
        holder.setItem(vo);
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvMovieNm;
        private TextView tvMovieNmEn;
        private TextView tvRepNationNm;
        private TextView tvRepGenreNm;

        public MyViewHolder(View v) {
            super(v);

            tvMovieNm = v.findViewById(R.id.tvMovieNm);
            tvMovieNmEn = v.findViewById(R.id.tvMovieNmEn);
            tvRepNationNm = v.findViewById(R.id.tvRepNationNm);
            tvRepGenreNm = v.findViewById(R.id.tvRepGenreNm);
        }

        public void setItem(MovieVO vo) {
            tvMovieNm.setText(vo.getMovieNm());
            tvMovieNmEn.setText(vo.getMovieNmEn());
            tvRepNationNm.setText(vo.getRepNationNm());
            tvRepGenreNm.setText(vo.getRepGenreNm());
        }
    }
}