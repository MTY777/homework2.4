package com.example.homwork2.ui.ProfileFragment;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.homwork2.Prefs;
import com.example.homwork2.R;
import com.example.homwork2.databinding.FragmentProfileBinding;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnPausedListener;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.UUID;


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

    @SuppressLint("WrongThread")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        prefes = new Prefs(requireContext());
        binding.tvUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

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
                    prefes.saveImageViev(String.valueOf(uri));
                    Glide.with(requireActivity()).load(uri).circleCrop().into(binding.image);
                    binding.image.setImageURI(uri);
                    upload(uri);

                }


            });

    @Override
    public void onDestroy() {
    prefes.saveEditText(binding.editext.getText().toString());
        super.onDestroy();
    }

    private void upload(Uri uri) {
        String userId = FirebaseAuth.getInstance().getUid();
        StorageReference ref = FirebaseStorage.getInstance().getReference().child(userId + ".jpg");
        ref.putFile(uri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                if (task.isSuccessful()){
                    Toast.makeText(requireContext(), "Uploaded", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(requireContext(), "Uploaded", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
