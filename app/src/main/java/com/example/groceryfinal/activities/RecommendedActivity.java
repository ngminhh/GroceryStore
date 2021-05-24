package com.example.groceryfinal.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.groceryfinal.R;
import com.example.groceryfinal.models.RecommendedModel;
import com.example.groceryfinal.models.ViewAllModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class RecommendedActivity extends AppCompatActivity {

    TextView quantity;
    int totalQuantity =1;

    int totalPrice=0;

    ImageView detailedImg1;
    TextView price,rating,description;
    Button addToCart;
    ImageView addItem,removeItem;
    Toolbar toolbar;

    FirebaseFirestore firestore;
    FirebaseAuth auth;

    RecommendedModel recommendedModel=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommended);


        toolbar=findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final Object object=getIntent().getSerializableExtra("detail1");
        if (object instanceof ViewAllModel){
            recommendedModel=(RecommendedModel) object;
        }

        firestore=FirebaseFirestore.getInstance();
        auth=FirebaseAuth.getInstance();

        quantity=findViewById(R.id.quantity1);

        detailedImg1=findViewById(R.id.detailed_img1);
        addItem=findViewById(R.id.add_item1);
        removeItem=findViewById(R.id.remove_item1);
        price=findViewById(R.id.detailed_price1);
        rating=findViewById(R.id.detailed_rating1);
        description=findViewById(R.id.detailed_dec1);

        if (recommendedModel!= null){
            Glide.with(getApplicationContext()).load(recommendedModel.getImg_url()).into(detailedImg1);
            rating.setText(recommendedModel.getRating());
            description.setText(recommendedModel.getDescription());
            price.setText("Giá: "+recommendedModel.getPrice()+"VNĐ/kg");
            totalPrice=recommendedModel.getPrice()*totalQuantity;





        }
        addToCart=findViewById(R.id.add_to_cart1);

        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addedToCart();
            }
        });

        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (totalQuantity<20){
                    totalQuantity++;
                    quantity.setText(String.valueOf(totalQuantity));

                }

            }
        });
        removeItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (totalQuantity>1){
                    totalQuantity--;
                    quantity.setText(String.valueOf(totalQuantity));

                }

            }
        });


    }

    private void addedToCart() {
        String saveCurrentDate, saveCurrentTime;
        Calendar calForDate = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("MM dd, yyyy");
        saveCurrentDate= currentDate.format(calForDate.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime= currentTime.format(calForDate.getTime());

        final HashMap<String, Object> cartMap = new HashMap<>();
        cartMap.put("productName",recommendedModel.getName());
        cartMap.put("productPrice",price.getText().toString());
        cartMap.put("currentDate",saveCurrentDate);
        cartMap.put("currentTime",saveCurrentTime);
        cartMap.put("totalQuantity",quantity.getText().toString());
        cartMap.put("totalPrice",totalPrice);

        firestore.collection("CurrentUser2").document(auth.getCurrentUser().getUid())
                .collection("AddToCart2").add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<DocumentReference> task) {
                Toast.makeText(RecommendedActivity.this, "Đã thêm vào giỏ hàng", Toast.LENGTH_SHORT).show();
                finish();
            }
        });


    }
}