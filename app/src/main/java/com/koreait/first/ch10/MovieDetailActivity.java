package com.koreait.first.ch10;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.koreait.first.R;
import com.koreait.first.ch10.searchmoviemodel.ActorVO;
import com.koreait.first.ch10.searchmoviemodel.MovieInfoResultBodyVO;
import com.koreait.first.ch10.searchmoviemodel.MovieInfoVO;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieDetailActivity extends AppCompatActivity {
    private TextView tvMovieNm;
    private TextView tvMovieNmEn;
    private TextView tvShowTm;
    private RecyclerView rvActorList;

    private ActorAdaptor adaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        Intent intent = getIntent();
        String movieCd = intent.getStringExtra("movieCd");
        Log.i("myLog", "detail - movieCd : " + movieCd);
        getData(movieCd);

        tvMovieNm = findViewById(R.id.tvMovieNm);
        tvMovieNmEn = findViewById(R.id.tvMovieNmEn);
        tvShowTm = findViewById(R.id.tvShowTm);
        rvActorList = findViewById(R.id.rvActorList);

        adaptor = new ActorAdaptor();
        rvActorList.setAdapter(adaptor);
    }

    private void getData(String movieCd) {
        Retrofit rf = new Retrofit.Builder()
                .baseUrl("https://www.kobis.or.kr/kobisopenapi/webservice/rest/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        KobisService service = rf.create(KobisService.class);
        final String KEY = "1a0a7ecf96ad3364d8de70e91560767a";
        Call<MovieInfoResultBodyVO> call = service.searchMovieInfo(KEY, movieCd);
        call.enqueue(new Callback<MovieInfoResultBodyVO>() {
            @Override
            public void onResponse(Call<MovieInfoResultBodyVO> call, Response<MovieInfoResultBodyVO> res) {
                if (res.isSuccessful()) {   // state : 200 - 통신 성공
                    MovieInfoResultBodyVO result = res.body();

                    MovieInfoVO data = result.getMovieInfoResult().getMovieInfo();

                    tvMovieNm.setText(data.getMovieNm());
                    tvMovieNmEn.setText(data.getMovieNmEn());
                    tvShowTm.setText(data.getShowTm());

                    adaptor.setList(data.getActors());
                    adaptor.notifyDataSetChanged();

                } else {

                }
            }

            @Override
            public void onFailure(Call<MovieInfoResultBodyVO> call, Throwable t) {   // baseUrl 이 틀리거나, 그 서버가 죽어있을 때, 망에 문제가 있을 떼

            }
        });

    }
}

class ActorAdaptor extends RecyclerView.Adapter<ActorAdaptor.MyViewHolder> {
    private List<ActorVO> list;

    public void setList(List<ActorVO> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.item_actor, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ActorVO vo = list.get(position);
        holder.setItem(vo);
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvPeopleNm;
        private TextView tvPeopleNmEn;
        private TextView tvCast;
        private TextView tvCastEn;

        public MyViewHolder(@NonNull View v) {
            super(v);

            tvPeopleNm = v.findViewById(R.id.tvPeopleNm);
            tvPeopleNmEn = v.findViewById(R.id.tvPeopleNmEn);
            tvCast = v.findViewById(R.id.tvCast);
            tvCastEn = v.findViewById(R.id.tvCastEn);
        }

        public void setItem(ActorVO vo) {
            tvPeopleNm.setText(vo.getPeopleNm());
            tvPeopleNmEn.setText(vo.getPeopleNmEn());
            tvCast.setText(vo.getCast());
            tvCastEn.setText(vo.getCastEn());
        }
    }
}