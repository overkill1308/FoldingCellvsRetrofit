package com.example.foldingcellanimation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ramotion.foldingcell.FoldingCell;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private Context context;
    private List<User> userList;

    public UserAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<User> list){
        userList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        User user = userList.get(position);
        if (user == null){
            return;
        }
        holder.tv_full_name.setText(user.getFullName());
        holder.tv_birthday.setText(user.getBirthDate().substring(0,10));
        Glide.with(context).load(user.getAvatar()).into(holder.img_avt);
        Glide.with(context).load(user.getAvatar()).into(holder.img_avt_detail);
        holder.foldingCell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.foldingCell.toggle(false);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (userList ==  null){
            return 0;
        }
        return userList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private FoldingCell foldingCell;
        private TextView tv_full_name, tv_birthday;
        private CircleImageView img_avt, img_avt_detail;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            foldingCell = itemView.findViewById(R.id.folding_cell);
            tv_full_name = itemView.findViewById(R.id.tv_full_name);
            tv_birthday = itemView.findViewById(R.id.tv_birthday);
            img_avt = itemView.findViewById(R.id.img_avt);
            img_avt_detail = itemView.findViewById(R.id.img_avt_detail);
        }
    }
}
