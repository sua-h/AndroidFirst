package com.koreait.first;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;

public class WriteActivity extends AppCompatActivity {

    private EditText etMsg;
    private Button btnSend;
    private RecyclerView rvList;   // view

    private List<String> msgList;  // data

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

        // 아이디 주소 값 가져오기
        etMsg = findViewById(R.id.etMsg);
        btnSend = findViewById(R.id.btnSend);
        rvList = findViewById(R.id.rvList);

        msgList = new LinkedList<>();

        // layout은 xml 에서도 설정 가능 (recyclerview에 layoutManager, span 추가)
        LinearLayoutManager llm = new LinearLayoutManager(this);   // vertical 로 정렬
        //LinearLayoutManager llm = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);   // horizontal 로 정렬, true 하면 반대순서로 정렬 (리버스). horizontal 사용 시 width 값을 parent에서 wrap으로 변경해주어야 한다.
        rvList.setLayoutManager(llm);

        SimpleTextAdapter sta = new SimpleTextAdapter(msgList);
        rvList.setAdapter(sta);

        // 인터페이스는 객체화 불가능, 아래는 객체화가 아니라 implements 임.
        // 1. class 작성 필요
        View.OnClickListener event1 = new MyOnClickListener();

        // 2. 변수 할당 필요
        View.OnClickListener event2 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {}
        };
        btnSend.setOnClickListener(event2);

        // 3. 가장 간략하게 작성
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //콜백 메소드 (Call Back)
                String msg = etMsg.getText().toString();
                Log.i("myLog", msg);
                etMsg.setText("");
                msgList.add(msg);
                sta.notifyDataSetChanged();   // data가 새로 setting 되었다는 걸 adapter에게 알려줌
            }
        });


    }
}

// 1. class 작성 필요
class MyOnClickListener implements View.OnClickListener {
    @Override
    public void onClick(View v) {

    }
}





class SimpleTextAdapter extends RecyclerView.Adapter<SimpleTextAdapter.MyViewHolder> {

    private List<String> list;

    SimpleTextAdapter(List<String> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_textview, parent, false);
        return new SimpleTextAdapter.MyViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Log.i("myLog", "position : " + position);
        String str = list.get(position);
        holder.tvMsg.setText(str);
    }

    @Override
    public int getItemCount() {
        Log.i("myLog", "getItemCount : " + list.size());
        return list.size();
    }


    // 클래스 안의 클래스 - 내부에서만 사용. (private 처럼)
    // ViewHolder - 똑같은 레이아웃을 사용할 때 가져와서 씀. 시간이 더 빨라짐. 레이아웃이 다르다면 사용할 수 없다.
    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvMsg;

        public MyViewHolder(View v) {
            super(v);
            tvMsg = v.findViewById(R.id.tvMsg);
        }
    }
}