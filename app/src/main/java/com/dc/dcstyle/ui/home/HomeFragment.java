package com.dc.dcstyle.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dc.dcstyle.R;
import com.dc.dcstyle.adapter.CategoryAdapter;
import com.dc.dcstyle.adapter.HomeAdapter;
import com.dc.dcstyle.adapter.WomanAdapter;
import com.dc.dcstyle.databinding.FragmentHomeBinding;
import com.dc.dcstyle.models.CategoryModel;
import com.dc.dcstyle.models.HomeCategory;
import com.dc.dcstyle.models.WomanModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    ScrollView scrollView;
    ProgressBar progressBar;
    RecyclerView categoryRec,homeCatRec,womanRec;
    FirebaseFirestore db;
    List<CategoryModel> categoryModelList;
    CategoryAdapter categoryAdapter;
    //home category
    List<HomeCategory>categoryList;
    HomeAdapter homeAdapter;
    //woman
    List<WomanModel> womanModelList;
    WomanAdapter womanAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

      View root = inflater.inflate(R.layout.fragment_home, container,false);
      db = FirebaseFirestore.getInstance();

      categoryRec = root.findViewById(R.id.categoryrec);
      homeCatRec = root.findViewById(R.id.womanrec);
      womanRec = root.findViewById(R.id.womanrec1);
      scrollView = root.findViewById(R.id.scroll_view);
      progressBar = root.findViewById(R.id.progressbar);

      progressBar.setVisibility(View.VISIBLE);
      scrollView.setVisibility(View.GONE);

      categoryRec.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
      categoryModelList = new ArrayList<>();
      categoryAdapter = new CategoryAdapter(getActivity(),categoryModelList);
      categoryRec.setAdapter(categoryAdapter);


        db.collection("Category")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                             CategoryModel categoryModel = document.toObject(CategoryModel.class);
                             categoryModelList.add(categoryModel);
                             categoryAdapter.notifyDataSetChanged();

                                progressBar.setVisibility(View.GONE);
                                scrollView.setVisibility(View.VISIBLE);
                            }
                        } else {
                            Toast.makeText(getActivity(), "Error"+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        homeCatRec.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        categoryList = new ArrayList<>();
        homeAdapter = new HomeAdapter(getActivity(),categoryList);
        homeCatRec.setAdapter(homeAdapter);
        db.collection("HomeCategory")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                HomeCategory homeCategory = document.toObject(HomeCategory.class);
                                categoryList.add(homeCategory);
                                homeAdapter.notifyDataSetChanged();
                            }
                        } else {
                            Toast.makeText(getActivity(), "Error"+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        womanRec.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        womanModelList = new ArrayList<>();
        womanAdapter = new WomanAdapter(getActivity(),womanModelList);
        womanRec.setAdapter(womanAdapter);
        db.collection("Woman")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                WomanModel womanModel = document.toObject(WomanModel.class);
                                womanModelList.add(womanModel);
                                womanAdapter.notifyDataSetChanged();
                            }
                        } else {
                            Toast.makeText(getActivity(), "Error"+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });




        return root;
    }

//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        binding = null;
//    }
}