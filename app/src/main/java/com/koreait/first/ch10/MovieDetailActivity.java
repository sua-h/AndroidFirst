package com.koreait.first.ch10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.koreait.first.R;
import com.koreait.first.ch10.searchmoviemodel.MovieInfoResultBodyVO;
import com.koreait.first.ch10.searchmoviemodel.MovieInfoVO;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieDetailActivity extends AppCompatActivity {
    private TextView tvMovieNm;
    private TextView tvMovieNmEn;
    private TextView tvShowTm;

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

                } else {

                }
            }

            @Override
            public void onFailure(Call<MovieInfoResultBodyVO> call, Throwable t) {   // baseUrl 이 틀리거나, 그 서버가 죽어있을 때, 망에 문제가 있을 떼

            }
        });

    }
}