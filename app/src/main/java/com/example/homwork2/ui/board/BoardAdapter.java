package com.example.homwork2.ui.board;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homwork2.R;
import com.example.homwork2.models.Board;

import java.util.ArrayList;

public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.ViewHolder>{
    private ArrayList<Board> list;
    private FinishBoard finishBoard;

    public void setFinishBoard(FinishBoard finishBoard) {
        this.finishBoard = finishBoard;
    }

    public BoardAdapter() {
        list = new ArrayList<>();
        list.add(new Board("Салам", "Бул меним үй ишим", R.drawable.ic_one_puch_man));
        list.add(new Board("Привет", "За эту домашку можно поставить 10 баллов", R.drawable.ic_dio));
        list.add(new Board("Hello", "You can visit my homework", R.drawable.ic_human));
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pager_board, parent, false);
        return new ViewHolder(view);
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
        private Button btn_start;
        private TextView textTitle;
        private TextView textInfo;
        private ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.textTitle);
            btn_start = itemView.findViewById(R.id.btnStart);
            textInfo = itemView.findViewById(R.id.text_info);
            imageView = itemView.findViewById(R.id.image_view);
            btn_start.setOnClickListener(view -> {
            finishBoard.btnClickFinishBoard();
            });
        }

        public void bind(int position) {
            Board board= list.get(position);
            textTitle.setText(board.getTitle());
            textInfo.setText(board.getDesc());
            imageView.setImageResource(board.getImage());
            if (position == list.size() - 1){
                btn_start.setVisibility(View.VISIBLE);
            }else{
                btn_start.setVisibility(View.INVISIBLE);
            }
        }
    }

    interface FinishBoard{
        void  btnClickFinishBoard();
    }

}
