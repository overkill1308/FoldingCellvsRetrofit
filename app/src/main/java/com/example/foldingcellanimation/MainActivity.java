package com.example.foldingcellanimation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import com.ramotion.foldingcell.FoldingCell;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity{

    RecyclerView rc_view;
    UserAdapter userAdapter;
//    AppCompatButton btn_left_to_right, btn_right_to_left, btn_top_to_bottom, btn_bottom_to_top;
    List<User> userList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rc_view = findViewById(R.id.rc_view);
        userList = new ArrayList<>();
//        btn_left_to_right = findViewById(R.id.btn_left_to_right);
//        btn_right_to_left = findViewById(R.id.btn_right_to_left);
//        btn_top_to_bottom = findViewById(R.id.btn_top_to_bottom);
//        btn_bottom_to_top = findViewById(R.id.btn_bottom_to_top);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rc_view.setLayoutManager(linearLayoutManager);
        rc_view.setFocusable(false);
        userAdapter = new UserAdapter(this);

//        btn_left_to_right.setOnClickListener(this);
//        btn_right_to_left.setOnClickListener(this);
//        btn_top_to_bottom.setOnClickListener(this);
//        btn_bottom_to_top.setOnClickListener(this);
        getDataServer();
    }

    public void getDataServer(){
        RetrofitService.service.getUserApi().enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful() && response.body() != null){
                    userList = response.body();
                    LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(MainActivity.this, R.anim.layout_anim_top_to_bottom);
                    rc_view.setLayoutAnimation(controller);
                    userAdapter.setData(userList);
                    rc_view.setAdapter(userAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.e("TAG", t.getMessage());
            }
        });
    }

//    @Override
//    public void onClick(View v) {
//        switch (v.getId()){
//            case R.id.btn_left_to_right:
//                setAnimation(R.anim.layout_anim_left_to_right);
//                break;
//            case R.id.btn_right_to_left:
//                setAnimation(R.anim.layout_anim_right_to_left);
//                break;
//            case R.id.btn_top_to_bottom:
//                setAnimation(R.anim.layout_anim_top_to_bottom);
//                break;
//            case R.id.btn_bottom_to_top:
//                setAnimation(R.anim.layout_anim_bottom_to_top);
//                break;
//        }
//    }

}