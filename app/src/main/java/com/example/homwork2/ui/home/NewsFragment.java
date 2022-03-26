package com.example.homwork2.ui.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.homwork2.App;
import com.example.homwork2.R;
import com.example.homwork2.databinding.FragmentNewsBinding;
import com.example.homwork2.models.News;


public class NewsFragment extends Fragment {
    private FragmentNewsBinding binding;
    private News news;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentNewsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        news = (News) requireArguments().getSerializable("updateTask");
        if (news != null) binding.EditText.setText(news.getTitle());

        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save();

            }
        });
    }

    private void save() {
        Bundle bundle = new Bundle();
        String text = binding.EditText.getText().toString().trim();
        if (text.isEmpty()) {
            Toast.makeText(requireContext(), "type task!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (news == null) {
            news = new News(text, System.currentTimeMillis());
        }else {
            news.setTitle(text);
        }
        App.getDataBase().newsDao().insert(news);
        bundle.putSerializable("news", news);
        getParentFragmentManager().setFragmentResult("rk_news", bundle);
        close();
    }

    private void close() {
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);
        navController.navigateUp();
    }

}
