package com.koreait.first.ch07;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.koreait.first.R;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.MyViewHolder> {
    private List<Person> items = new LinkedList<>();

    public void addItem(Person item) {
        items.add(item);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());    // xml 파일을 객체화
        View itemView = inflater.inflate(R.layout.person_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
//        Person item = items.get(position);
//        holder.setItem(item);

        holder.setItem(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        TextView tvAge;

        public MyViewHolder(View v) {
            super(v);

            tvName = v.findViewById(R.id.tvName);
            tvAge = v.findViewById(R.id.tvAge);
        }

        public void setItem(Person item) {
            // setText() 메소드는 오버로딩 메소드. 파라미터 타입이 String, int 둘 다 가능하다.
            tvName.setText(item.getName());
            tvAge.setText(item.getAge() + "세");   // 정수값은 R에서 관리하고 있는 정수값만 들어갈 수 있다.(ex - R.string.tv_01) 임의의 정수값 사용 불가.
                                                    // strings.xml에서 관리하고 있는 문자열을 입력할 때 정수
        }
    }
}
