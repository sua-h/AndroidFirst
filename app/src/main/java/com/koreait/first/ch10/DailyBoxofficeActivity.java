package com.koreait.first.ch10;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;

import com.koreait.first.R;
import com.koreait.first.ch10.boxofficemodel.BoxOfficeResultBodyVO;
import com.koreait.first.ch10.boxofficemodel.BoxOfficeResultVO;
import com.koreait.first.ch10.boxofficemodel.BoxOfficeVO;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DailyBoxofficeActivity extends AppCompatActivity {

    private KobisBoxofficeAdapter adapter;

    private DatePicker dpTargetDt;
    private RecyclerView rvList;

    private Retrofit rf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_boxoffice);

        adapter = new KobisBoxofficeAdapter();

        dpTargetDt = findViewById(R.id.dpTargetDt);
        rvList = findViewById(R.id.rvList);

        rvList.setAdapter(adapter);

        rf = new Retrofit.Builder()
                .baseUrl("https://www.kobis.or.kr/kobisopenapi/webservice/rest/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private void network(String targetDt) {
        KobisService service = rf.create(KobisService.class);
        final String KEY = "1a0a7ecf96ad3364d8de70e91560767a";
        Call<BoxOfficeResultBodyVO> call = service.searchDailyBoxOfficeList(KEY, targetDt);

        call.enqueue(new Callback<BoxOfficeResultBodyVO>() {
            @Override
            public void onResponse(Call<BoxOfficeResultBodyVO> call, Response<BoxOfficeResultBodyVO> res) {
                if(res.isSuccessful()) {
                    BoxOfficeResultBodyVO vo = res.body();
                    BoxOfficeResultVO resultVo = vo.getBoxOfficeResult();
                    List<BoxOfficeVO> list = resultVo.getDailyBoxOfficeList();

                    adapter.setList(list);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<BoxOfficeResultBodyVO> call, Throwable t) {

            }
        });
    }

    public void clkSearch(View v) {
        int day = dpTargetDt.getDayOfMonth();
        int mon = dpTargetDt.getMonth() + 1;  // month는 0부터 시작하기 때문에 +1
        int year = dpTargetDt.getYear();

//        // 싱글톤 패턴 - 하나의 인스턴스만 생성하는 경우에 사용됨.
//        Calendar c = Calendar.getInstance();
//        c.set(year, mon, day);
//
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//        String date = sdf.format(c.getTime());

        String date = String.format("%s%02d%02d", year, mon, day);
        Log.i("myLog", date);

        network(date);
    }
}

