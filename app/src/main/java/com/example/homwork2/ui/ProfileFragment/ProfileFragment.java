package com.example.homwork2.ui.ProfileFragment;

import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.bumptech.glide.Glide;
import com.example.homwork2.Prefs;
import com.example.homwork2.R;
import com.example.homwork2.databinding.FragmentProfileBinding;

import java.util.ArrayList;


public class ProfileFragment extends Fragment {
    private Prefs prefes;
    private FragmentProfileBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        prefes = new Prefs(requireContext());
        String text = binding.editext.getText().toString().trim();

        binding.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mGetContent.launch("image/*");
            }
        });

            binding.editext.setText(prefes.isEditText());
            if (prefes.isImageView() != null){
                Glide.with(binding.image).load(prefes.isImageView()).circleCrop().into(binding.image);
            }
    }

    ActivityResultLauncher<String> mGetContent = registerForActivityResult(new ActivityResultContracts.GetContent(),
            new ActivityResultCallback<Uri>() {
                @Override
                public void onActivityResult(Uri uri) {
                    Glide.with(binding.image).load(uri).circleCrop().into(binding.image);
                    prefes.saveImageViev(String.valueOf(uri));

                }
            });

    @Override
    public void onDestroy() {
    prefes.saveEditText(binding.editext.getText().toString());
        super.onDestroy();
    }
}
