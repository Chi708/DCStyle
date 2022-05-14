package com.dc.dcstyle.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.dc.dcstyle.R;

import com.dc.dcstyle.adapter.ViewAllClothesAdapter;
import com.dc.dcstyle.models.ViewAllClothesModel;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ViewAllClothesActivity extends AppCompatActivity {
    FirebaseFirestore firestore;
    RecyclerView recyclerView;
    ViewAllClothesAdapter viewAllClothesAdapter;
    List<ViewAllClothesModel> viewAllClothesModelList;
    Toolbar toolbar2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        toolbar2 = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar2);
//      this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_view_all_clothes);
        firestore = FirebaseFirestore.getInstance();
        String type = getIntent().getStringExtra("type");
        recyclerView = findViewById(R.id.view_all_clothes_rec);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        viewAllClothesModelList = new ArrayList<>();
        viewAllClothesAdapter = new ViewAllClothesAdapter(this, viewAllClothesModelList);
        recyclerView.setAdapter(viewAllClothesAdapter);

        if (type != null && type.equalsIgnoreCase("áo phông")) {
            firestore.collection("AllClothes").whereEqualTo("type", "áo phông").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {
                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                        ViewAllClothesModel viewAllClothesModel = documentSnapshot.toObject(ViewAllClothesModel.class);
                        viewAllClothesModelList.add(viewAllClothesModel);
                        viewAllClothesAdapter.notifyDataSetChanged();
                    }
                }
            });
        }}}