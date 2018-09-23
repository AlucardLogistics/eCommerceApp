package com.example.sadic.ecommerceapp.ui.product_detail;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sadic.ecommerceapp.R;
import com.example.sadic.ecommerceapp.data.database.model.CartProduct;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class DetailActivity extends AppCompatActivity implements IViewDetail {
    private static final String TAG = "DetailActivity";

    TextView tvNameDetail, tvPriceDetail, tvDescriptionDetail;
    ImageView ivImageDetail;
    IPresenterDetail presenterDetail;
    Uri outputFileUri;

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

    private CartProduct getCartProductObject() {
        Log.d(TAG, "getCartProductObject: started");
        Bundle b = getIntent().getExtras();

        String pId = b.getString("pId");
        String pName = b.getString("pName");
        String pQuantity = b.getString("pQuantity");
        String pPrice = b.getString("pPrice");
        String pDescription = b.getString("pDescription");

        saveCartImage(pId, pName);

        String imgLocalPath = outputFileUri.toString();

        CartProduct cartProduct = new CartProduct(pId, pName,
                pQuantity, pPrice, pDescription, imgLocalPath, 1, 0);

        return cartProduct;
    }

    private CartProduct getWishProductObject() {
        Log.d(TAG, "getWishProductObject: started");
        Bundle b = getIntent().getExtras();

        String pId = b.getString("pId");
        String pName = b.getString("pName");
        String pQuantity = b.getString("pQuantity");
        String pPrice = b.getString("pPrice");
        String pDescription = b.getString("pDescription");

        saveWishImage(pId, pName);

        String imgLocalPath = outputFileUri.toString();

        CartProduct cartProduct = new CartProduct(pId, pName,
                pQuantity, pPrice, pDescription, imgLocalPath, 0, 1);

        return cartProduct;
    }

    private void saveWishImage(String imgId, String imgName) {
        Log.d(TAG, "saveImage: started");
        checkPermissions();
        ivImageDetail.buildDrawingCache();
        Bitmap bitmap = ivImageDetail.getDrawingCache();
        String rootPath = Environment.getExternalStorageDirectory().getPath();
        String eComm = rootPath + "/DCIM/eComm";


        OutputStream fOut = null;

        try {
            File root = new File(eComm);
            root.mkdirs();
            File sdImageMainDir = new File(root, imgId + imgName + "Wish.png");
            outputFileUri = Uri.fromFile(sdImageMainDir);
            fOut = new FileOutputStream(sdImageMainDir);
            Log.d(TAG, "saveImage: save into: " + root.toString());
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
            fOut.close();
            fOut.flush();
        } catch (FileNotFoundException e) {
            Log.d(TAG, "saveImage: FileNotFoundException: " + e.getMessage());
        } catch (IOException e) {
            Log.d(TAG, "saveImage: IOException: " + e.getMessage());
        }
    }

    private void saveCartImage(String imgId, String imgName) {
        Log.d(TAG, "saveImage: started");
        checkPermissions();
        ivImageDetail.buildDrawingCache();
        Bitmap bitmap = ivImageDetail.getDrawingCache();
        String rootPath = Environment.getExternalStorageDirectory().getPath();
        String eComm = rootPath + "/DCIM/eComm";


        OutputStream fOut = null;

        try {
            File root = new File(eComm);
            root.mkdirs();
            File sdImageMainDir = new File(root, imgId + imgName + "Cart.png");
            outputFileUri = Uri.fromFile(sdImageMainDir);
            fOut = new FileOutputStream(sdImageMainDir);
            Log.d(TAG, "saveImage: save into: " + root.toString());
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
            fOut.close();
            fOut.flush();
        } catch (FileNotFoundException e) {
            Log.d(TAG, "saveImage: FileNotFoundException: " + e.getMessage());
        } catch (IOException e) {
            Log.d(TAG, "saveImage: IOException: " + e.getMessage());
        }
    }

    public void eventHandler(View view) {
        switch (view.getId()) {
            case R.id.btAddCart:
                Log.d(TAG, "eventHandler: btAddCart: " + getCartProductObject().toString());
                presenterDetail.onButtonClickHandler(view, getCartProductObject());
                break;
            case R.id.btWishList:
                presenterDetail.onButtonClickHandler(view, getWishProductObject());
                break;
        }


    }

    void checkPermissions() {
        if(ContextCompat.checkSelfPermission(DetailActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED) { //check if permission is granted
            Toast.makeText(DetailActivity.this, "Permission Granted", Toast.LENGTH_SHORT).show();
        } else {
            requestDataPermission();

        }
    }

    private void requestDataPermission() {

        if(ActivityCompat.shouldShowRequestPermissionRationale(
                DetailActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) { //if user declines permission
            final AlertDialog.Builder permissionDialog = new AlertDialog.Builder(DetailActivity.this);
            permissionDialog.setTitle("Asking for Permission!");
            permissionDialog.setMessage("Request permission to save and access the images to your device for offline use!");
            permissionDialog.setPositiveButton("Grant Permission", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ActivityCompat.requestPermissions(
                            DetailActivity.this, new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 1234);
                }
            });

            permissionDialog.setNegativeButton("Decline", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            permissionDialog.show();

        } else {
            ActivityCompat.requestPermissions(
                    DetailActivity.this, new String[] {Manifest.permission.READ_SMS}, 1234);
        }

    }
}
