package com.example.homwork2.ui.board;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.homwork2.Prefs;
import com.example.homwork2.R;
import com.tbuonomo.viewpagerdotsindicator.SpringDotsIndicator;

public class BoardFragment extends Fragment implements BoardAdapter.FinishBoard {
    private TextView textskip;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return   inflater.inflate(R.layout.fragment_board, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textskip = view.findViewById(R.id.skipped);
        SpringDotsIndicator dotsIndicator = view.findViewById(R.id.spring_dots_indicator);
        BoardAdapter adapter = new BoardAdapter();
        ViewPager2 viewPager2 = view.findViewById(R.id.view_pager);
        viewPager2.setAdapter(adapter);
        dotsIndicator.setViewPager2(viewPager2);
        adapter.setFinishBoard(this);
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(),
                new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                requireActivity().finish();
            }
        });

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                if (position == 2){
                    textskip.setVisibility(View.GONE);
                }else {
                    textskip.setVisibility(View.VISIBLE);
                }
            }
        });
        textskip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigate();
            }
        });

    }

    public void navigate(){
        Prefs prefes =new Prefs(requireContext());
        prefes.saveBoardState();
        NavController navController = Navigation.findNavController(requireActivity(),
                R.id.nav_host_fragment_activity_main);
        navController.navigateUp();
    }

    @Override
    public void btnClickFinishBoard() {
        navigate();
    }


}