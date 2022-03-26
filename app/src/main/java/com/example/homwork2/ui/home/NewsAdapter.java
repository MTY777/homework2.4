package com.example.homwork2.ui.home;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homwork2.Interfaces.OnClickListener;
import com.example.homwork2.databinding.ItemNewsBinding;
import com.example.homwork2.models.News;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@SuppressLint("NotifyDataSetChanged")
public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
    private List<News> list;
    private OnClickListener onClickListener;

    public NewsAdapter() {
        this.list = new ArrayList<>();
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NewsViewHolder(ItemNewsBinding.inflate(LayoutInflater.from(parent.getContext()), parent , false));
    }

    public void setList(List<News> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
    holder.bind(list.get(position));
    if (position % 2 == 0){
        holder.binding.title.setBackgroundColor(Color.GRAY);
    }else{
        holder.binding.title.setBackgroundColor(Color.BLACK);
    }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addItem(News news) {
        list.add(0, news);
        notifyItemInserted(list.indexOf(news));
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public News getItem(int position) {
        return list.get(position);
    }


    public void updateItem(News news, int position) {
        list.set(position, news);
        notifyItemChanged(position);
    }

    public void addList(List<News> list) {
        this.list.addAll(list);
        notifyDataSetChanged();
    }
    public void removeItem(int position) {
        this.list.remove(position);
        notifyItemRemoved(position);
    }


    class NewsViewHolder extends RecyclerView.ViewHolder {
        ItemNewsBinding binding;

        public NewsViewHolder(@NonNull ItemNewsBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickListener.onItemClick(getAdapterPosition());


                }
            });
            binding.getRoot().setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    onClickListener.onItemLongClick(getAdapterPosition());

                    return true;
                }
            });
        }

        public void bind(News news) {
            binding.title.setText(news.getTitle());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss, dd MMM yyyy", Locale.ROOT);
            String date = String.valueOf(simpleDateFormat.format(news.getCreatedAt()));
            binding.tvTime.setText(date);
        }
    }

    }

