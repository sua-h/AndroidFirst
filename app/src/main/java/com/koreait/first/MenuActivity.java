package com.koreait.first;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.koreait.first.ch07.BookPersonActivity;
import com.koreait.first.ch07.Utils;
import com.koreait.first.ch10.DailyBoxofficeActivity;
import com.koreait.first.ch10.MovieListActivity;
import com.koreait.first.ch10.WeeklyBoxofficeActivity;
import com.koreait.first.picsum.PicsumActivity;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void call(View v) {
        // 전화 걸기
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:010-1111-2222"));
        startActivity(intent);
    }

    public void moveToActivity(View v) {
        int id = v.getId();

        Class c = null;
        // id int형 번호 확인 : R.id.menuBtn1

        if (id == R.id.menuBtn1) {
            c = MainActivity.class;
        } else if (id == R.id.menuBtn2) {
            c = LinearActivity.class;
        } else if (id == R.id.menuBtn3) {
            c = ConstraintActivity.class;
        } else if (id == R.id.menuBtn4) {
            c = WriteActivity.class;
        } else if (id == R.id.menuBtn5) {
            c = BookPersonActivity.class;
        } else if (id == R.id.menuBtn6) {
            c = ImageViewActivity.class;
        } else if (id == R.id.menuBtn7) {
            c = PicsumActivity.class;
        } else if (id == R.id.menuBtn8) {
            c = DailyBoxofficeActivity.class;
        } else if (id == R.id.menuBtn9) {
            c = WeeklyBoxofficeActivity.class;
        } else if (id == R.id.menuBtn10) {
            c = MovieListActivity.class;
        }

        if (c == null) {
            Snackbar.make(v, "준비중입니다.", Snackbar.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(this, c);   // new Intent(from, 목적지);
        startActivity(intent);
    }

    public void moveToActivityWithText(View v) {
        TextView textView = (TextView) v;
        String text = (String) textView.getText();
        // Log.i 확인하는 방법 = 하단의 Logcat 이용
        Log.i("myLog", text);

        Class c = null;

        switch (text) {
            case "메인" :
                c = MainActivity.class;
                break;
            case "리니어레이아웃" :
                c = LinearActivity.class;
                break;
            case "제약레이아웃" :
                c = ConstraintActivity.class;
                break;
            case "글쓰기" :
                c = WriteActivity.class;
        }

        Intent intent = new Intent(this, c);
        startActivity(intent);
    }
}