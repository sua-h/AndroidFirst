package com.koreait.first;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // 이벤트 연결 (event binding) 버튼 클릭 시 실행될 메소드 연결
    public void clkBtn(View v) {
        Button btn = (Button)v;  // 실행한(누른) 버튼 객체의 주소가 View v 로 넘어온다. Button 클래스는 View 클래스를 상속 받는다.
        //String btnText = (String)btn.getText();
        //Toast.makeText(this, btnText + "를 클릭했어요.", Toast.LENGTH_LONG).show();
        CharSequence charSequence = btn.getText() + "를 클릭했어요";
        Toast.makeText(this, charSequence, Toast.LENGTH_LONG).show();
    }

    public void aaa(View v) {
        TextView textView = (TextView)v;
        //String str = (String)textView.getText();
        //Toast.makeText(this, str, Toast.LENGTH_LONG).show();
        Toast.makeText(this, textView.getText(), Toast.LENGTH_LONG).show();
    }

    public void ddd(View v) {
        // 누를 때 마다 1씩 증가
        // v에 담겨있는 객체주소값을 TextView타입으로 저장할 수 있으면 true, 없으면 false
        if (v instanceof TextView) {
            TextView textView = (TextView)v;
            String val = (String)textView.getText();
            int intVal = Integer.parseInt(val);
            intVal += 1;
            String parseStrVal = String.valueOf(intVal);
            textView.setText(parseStrVal);
        }
    }
}