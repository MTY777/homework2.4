package com.example.homwork2.ui.board;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homwork2.R;
import com.example.homwork2.databinding.FragmentBoardBinding;
import com.example.homwork2.databinding.ItemNewsBinding;
import com.example.homwork2.databinding.PagerBoardBinding;
import com.example.homwork2.models.Board;
import com.example.homwork2.ui.home.NewsAdapter;

import java.util.ArrayList;

public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.ViewHolder>{
    private ArrayList<Board> list;
    private FinishBoard finishBoard;


    public void setFinishBoard(FinishBoard finishBoard) {
        this.finishBoard = finishBoard;
    }

    public BoardAdapter() {
        list = new ArrayList<>();
        list.add(new Board("Салам", "Бул меним үй ишим"));
        list.add(new Board("Привет", "За эту домашку можно поставить 10 баллов"));
        list.add(new Board("Hello", "You can visit my homework"));
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new BoardAdapter.ViewHolder(PagerBoardBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    holder.bind(position);
    }


    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private PagerBoardBinding binding;

        public ViewHolder(@NonNull PagerBoardBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;

            binding.btnStart.setOnClickListener(view -> {
            finishBoard.btnClickFinishBoard();
            });
        }

        public void bind(int position) {
            Integer positions = position;
            Board board= list.get(position);
            binding.textTitle.setText(board.getTitle());
            binding.textInfo.setText(board.getDesc());
            if (position == list.size() - 1){
                binding.btnStart.setVisibility(View.VISIBLE);
            }else{
                binding.btnStart.setVisibility(View.INVISIBLE);
            }

            if (positions == 1) {
                binding.animationView.setAnimation(R.raw.world);
            }else if (positions == 2){
                binding.animationView.setAnimation(R.raw.animka);
            }else{
                binding.animationView.setAnimation(R.raw.news);
            }
        }
    }

    interface FinishBoard{
        void  btnClickFinishBoard();
    }

}
