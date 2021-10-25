package com.koreait.first.ch10;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Movie;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.koreait.first.R;
import com.koreait.first.ch10.searchmoviemodel.MovieListResultBodyVO;
import com.koreait.first.ch10.searchmoviemodel.MovieVO;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieListActivity extends AppCompatActivity {
    private KobisService service;
    private final String KEY = "1a0a7ecf96ad3364d8de70e91560767a";

    private final String ITEM_PER_PAGE = "20";
    private int curPage = 1;

    private RecyclerView rvList;
    private MovieListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);

        Retrofit rf = new Retrofit.Builder()
                .baseUrl("https://www.kobis.or.kr/kobisopenapi/webservice/rest/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = rf.create(KobisService.class);
        rvList = findViewById(R.id.rvList);

        rvList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                Log.i("myLog", "rv : " + recyclerView.canScrollVertically(1) + "");
                Log.i("myLog",  "IDLE : " + (newState == RecyclerView.SCROLL_STATE_IDLE));

                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    Log.i("myLog", "스크롤 끝");
                    getList();
                }

            }

//            @Override
//            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//            }
        });

        adapter = new MovieListAdapter();
        rvList.setAdapter(adapter);

        getList();
    }

    private void getList() {
        Call<MovieListResultBodyVO> call = service.searchMovieList(KEY, ITEM_PER_PAGE, curPage++);
        call.enqueue(new Callback<MovieListResultBodyVO>() {
            @Override
            public void onResponse(Call<MovieListResultBodyVO> call, Response<MovieListResultBodyVO> response) {
                if(response.isSuccessful()) {
                    MovieListResultBodyVO result = response.body();

                    List<MovieVO> list = result.getMovieListResult().getMovieList();

                    for (MovieVO vo : list) {
                        adapter.addItem(vo);
                    }
                    adapter.notifyDataSetChanged();
                } else {
                    // 통신은 됐지만 오류가 발생했을 때 처리
                    Log.e("myLog", "오류 발생");
                }
            }

            @Override
            public void onFailure(Call<MovieListResultBodyVO> call, Throwable t) {
                // 아예 응답이 없을 때 처리
                Log.e("myLog", "통신 실패");
            }
        });
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
        final MovieVO obj = list.get(position);
        holder.setItem(obj);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String movieCd = obj.getMovieCd();
                Log.i("myLog", movieCd);
                Intent intent = new Intent(v.getContext(), MovieDetailActivity.class);
                intent.putExtra("movieCd", movieCd);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
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