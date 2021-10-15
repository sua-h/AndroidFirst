package com.koreait.first;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

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
        }

        Intent intent = new Intent(this, c);
        startActivity(intent);
    }
}