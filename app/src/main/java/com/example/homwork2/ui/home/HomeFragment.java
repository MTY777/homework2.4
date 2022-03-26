package com.example.homwork2.ui.home;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.homwork2.App;
import com.example.homwork2.Interfaces.OnClickListener;
import com.example.homwork2.R;
import com.example.homwork2.databinding.FragmentHomeBinding;
import com.example.homwork2.models.News;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private NewsAdapter adapter;
    private boolean isChanged = false;
    private int position;
    private News news;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        adapter = new NewsAdapter();
        adapter.addList(App.getDataBase().newsDao().getAll());
    }

    private void searchView() {
        binding.editTextRecycle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                adapter.setList(App.getDataBase().newsDao().getItemTitle(editable.toString()));
            }
        });
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isChanged = false;
                open(null);
            }
        });
        getParentFragmentManager().setFragmentResultListener("rk_news", getViewLifecycleOwner(), new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                News news = (News) result.getSerializable("news");
                Log.e("Home", "text = " + news.getTitle());
                if (isChanged){
                    adapter.updateItem(news, position);
                }else{
                    adapter.addItem(news);
                }
            }
        });
        binding.recycleView.setAdapter(adapter);
        adapter.setOnClickListener(new OnClickListener() {
            @Override
            public void onItemClick(int position) {
                news = adapter.getItem(position);
                isChanged = true;
                open(news);
                HomeFragment.this.position = position;
            }
            @Override
            public void onItemLongClick(int position) {
                new AlertDialog.Builder(view.getContext()).setTitle("Удаление")
                        .setMessage("Вы точно хотите удалить?")
                        .setNegativeButton("нет", null)
                        .setPositiveButton("да", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                news = adapter.getItem(position);
                                adapter.removeItem(position);
                                App.getDataBase().newsDao().deleteTask(news);
                                adapter.notifyDataSetChanged();
                                Toast.makeText(view.getContext(), "Delete", Toast.LENGTH_LONG).show();

                            }
                        }).show();
            }
        });
        searchView();
    }

    private void open(News news) {
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);
        Bundle bundle = new Bundle();
        bundle.putSerializable("updateTask", news);
        navController.navigate(R.id.newsFragment, bundle);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.sorting) {
            adapter.setList(App.getDataBase().newsDao().sort());

            binding.recycleView.setAdapter(adapter);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    //    @Override
//    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
//        inflater.inflate(R.menu.activity_menu, menu);
//        super.onCreateOptionsMenu(menu, inflater);
//    }
}