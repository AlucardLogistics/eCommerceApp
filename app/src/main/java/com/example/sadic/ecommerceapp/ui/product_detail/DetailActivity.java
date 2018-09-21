package com.example.sadic.ecommerceapp.ui.product_detail;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sadic.ecommerceapp.R;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity implements IViewDetail {
    private static final String TAG = "DetailActivity";

    TextView tvNameDetail, tvPriceDetail, tvDescriptionDetail;
    ImageView ivImageDetail;
    IPresenterDetail presenterDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        presenterDetail = new PresenterDetail(this);

        init();
        setProductDetail();
    }

    private void init() {
        tvNameDetail = findViewById(R.id.tvNameDetail);
        tvPriceDetail = findViewById(R.id.tvPriceDetail);
        tvDescriptionDetail = findViewById(R.id.tvDescriptionDetail);
        ivImageDetail = findViewById(R.id.ivImageDetail);
    }

    private void setProductDetail() {
        Bundle b = getIntent().getExtras();

        String pName = b.getString("pName");
        String pPrice = b.getString("pPrice");
        String pDescription = b.getString("pDescription");
        String pImgUrl = b.getString("pImgUrl");

        Log.d(TAG, "setProductDetail: pictureURL: " + pImgUrl);

        tvNameDetail.setText(pName);
        tvPriceDetail.setText(pPrice);
        tvDescriptionDetail.setText(pDescription);
        Picasso.get().load(pImgUrl)
                .placeholder(R.drawable.placeholder)
                .into(ivImageDetail);
    }

    public void eventHandler(View view) {
        presenterDetail.onButtonClickHandler(view);
    }
}
