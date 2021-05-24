package com.example.groceryfinal.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.groceryfinal.R;
import com.example.groceryfinal.adapters.NavCategoryAdapter;
import com.example.groceryfinal.adapters.NavCateogryDetailedAdapter;
import com.example.groceryfinal.models.NavCategoryDetailedModel;
import com.example.groceryfinal.models.PopularModel;
import com.example.groceryfinal.models.ViewAllModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class NavCategoryActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<NavCategoryDetailedModel> list;
    NavCateogryDetailedAdapter adapter;

    FirebaseFirestore db;

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_category);

        db= FirebaseFirestore.getInstance();

        recyclerView=findViewById(R.id.nav_cat_det_rec);
        progressBar= findViewById(R.id.progessbar);
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);

        String type=getIntent().getStringExtra("type");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list= new ArrayList<>();
        adapter= new NavCateogryDetailedAdapter(this,list);
        recyclerView.setAdapter(adapter);

        //getting drink
        if (type!= null && type.equalsIgnoreCase("drink")){
            db.collection("NavCategoryDetailed").whereEqualTo("type","drink").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {
                    for (DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                        NavCategoryDetailedModel navCategoryDetailedModel = documentSnapshot.toObject(NavCategoryDetailedModel.class);
                        list.add(navCategoryDetailedModel);
                        adapter.notifyDataSetChanged();

                        progressBar.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);

                    }
                }
            });
        }
        //getting beer
        if (type!= null && type.equalsIgnoreCase("beer")){
            db.collection("NavCategoryDetailed").whereEqualTo("type","beer").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {
                    for (DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                        NavCategoryDetailedModel navCategoryDetailedModel = documentSnapshot.toObject(NavCategoryDetailedModel.class);
                        list.add(navCategoryDetailedModel);
                        adapter.notifyDataSetChanged();


                        progressBar.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                    }
                }
            });
        }
        //getting cookie
        if (type!= null && type.equalsIgnoreCase("cookie")){
            db.collection("NavCategoryDetailed").whereEqualTo("type","cookie").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {
                    for (DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                        NavCategoryDetailedModel navCategoryDetailedModel = documentSnapshot.toObject(NavCategoryDetailedModel.class);
                        list.add(navCategoryDetailedModel);
                        adapter.notifyDataSetChanged();


                        progressBar.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                    }
                }
            });
        }
        //getting wash
        if (type!= null && type.equalsIgnoreCase("wash")){
            db.collection("NavCategoryDetailed").whereEqualTo("type","wash").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {
                    for (DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                        NavCategoryDetailedModel navCategoryDetailedModel = documentSnapshot.toObject(NavCategoryDetailedModel.class);
                        list.add(navCategoryDetailedModel);
                        adapter.notifyDataSetChanged();


                        progressBar.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                    }
                }
            });
        }
        //getting candy
        if (type!= null && type.equalsIgnoreCase("candy")){
            db.collection("NavCategoryDetailed").whereEqualTo("type","candy").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {
                    for (DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                        NavCategoryDetailedModel navCategoryDetailedModel = documentSnapshot.toObject(NavCategoryDetailedModel.class);
                        list.add(navCategoryDetailedModel);
                        adapter.notifyDataSetChanged();


                        progressBar.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                    }
                }
            });
        }
        //getting face
        if (type!= null && type.equalsIgnoreCase("face")){
            db.collection("NavCategoryDetailed").whereEqualTo("type","face").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {
                    for (DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                        NavCategoryDetailedModel navCategoryDetailedModel = documentSnapshot.toObject(NavCategoryDetailedModel.class);
                        list.add(navCategoryDetailedModel);
                        adapter.notifyDataSetChanged();


                        progressBar.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                    }
                }
            });
        }

        //getting alcohol
        if (type!= null && type.equalsIgnoreCase("noodle")){
            db.collection("NavCategoryDetailed").whereEqualTo("type","noodle").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {
                    for (DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                        NavCategoryDetailedModel navCategoryDetailedModel = documentSnapshot.toObject(NavCategoryDetailedModel.class);
                        list.add(navCategoryDetailedModel);
                        adapter.notifyDataSetChanged();


                        progressBar.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                    }
                }
            });
        }


        //getting meat
        if (type!= null && type.equalsIgnoreCase("wine")){
            db.collection("NavCategoryDetailed").whereEqualTo("type","wine").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {
                    for (DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                        NavCategoryDetailedModel navCategoryDetailedModel = documentSnapshot.toObject(NavCategoryDetailedModel.class);
                        list.add(navCategoryDetailedModel);
                        adapter.notifyDataSetChanged();


                        progressBar.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                    }
                }
            });
        }

    }
}