package com.example.paraggelies.activities.seller;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.CursorLoader;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.paraggelies.R;
import com.example.paraggelies.api.RetrofitClient;
import com.example.paraggelies.models.createProductResponse;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class addProduct extends AppCompatActivity implements View.OnClickListener {
    private EditText productName,productPrice;
    private Button selectImageBtn,uploadProduct;
    private ImageView img;
    private static final int IMG_REQUEST=1;
    private Bitmap bitmap;
    private String token;

    String imagePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        SharedPreferences result=getSharedPreferences("SellerData", Context.MODE_PRIVATE);
         token=result.getString("token","");


        productName=findViewById(R.id.addProductName);
        productPrice=findViewById(R.id.addProductPrice);
        selectImageBtn=findViewById(R.id.addproductImageBtn);
        uploadProduct=findViewById(R.id.addProductBtn);
        img=findViewById(R.id.productImage);

        selectImageBtn.setOnClickListener(this);
        uploadProduct.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
            switch(view.getId()){
                case R.id.addproductImageBtn:
                    SelectImage();
                    break;
                case R.id.addProductBtn:

                    RequestBody createName=RequestBody.create(MediaType.parse("multipart/form-data"),productName.getText().toString());
                    RequestBody createPrice=RequestBody.create(MediaType.parse("multipart/form-data"),productPrice.getText().toString());
                    MultipartBody.Part pic;
                    File file=new File(imagePath);
                    RequestBody requestFile=RequestBody.create(MediaType.parse("image/*"),file);
                    pic=MultipartBody.Part.createFormData("productImage",file.getName(),requestFile);


                    //Toast.makeText(this,productName.getText(),Toast.LENGTH_LONG).show();
                   uploadProduct("Bearer "+token,createName,createPrice,pic);
                    break;
            }
    }

    private void SelectImage(){
        Intent showGallery = new Intent(Intent.ACTION_PICK);
        showGallery.setType("image/*");
       // showGallery.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(showGallery,0);

    }





    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        if(requestCode==IMG_REQUEST && resultCode==RESULT_OK && data!=null && data.getData()!=null){
//            Uri path=data.getData();
//            try {
//                bitmap=MediaStore.Images.Media.getBitmap(getContentResolver(),path);
//                img.setImageBitmap(bitmap);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//
//
//        }

        if(resultCode==RESULT_OK){
            if(data==null){
                Toast.makeText(this,"Unable to choose image!",Toast.LENGTH_LONG).show();
                return;
            }

            Uri imageUri=data.getData();
            imagePath=getRealPathFromUri(imageUri);
            try {
                bitmap=MediaStore.Images.Media.getBitmap(getContentResolver(),imageUri);
                img.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
//                img.setImageBitmap(bitmap);
        }


    }
    private String getRealPathFromUri(Uri uri){
        String[] projection={MediaStore.Images.Media.DATA};
        CursorLoader cursorLoader=new CursorLoader(getApplicationContext(),uri,projection,null,null,null);
        Cursor cursor=cursorLoader.loadInBackground();
        int column_idx=cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result=cursor.getString(column_idx);
        return result;
    }



     private void uploadProduct(String token,RequestBody name,RequestBody price,MultipartBody.Part img){


        Call<createProductResponse> call= RetrofitClient.getInstance().getApi().createProduct(token,name,price,img);
        call.enqueue(new Callback<createProductResponse>() {
            @Override
            public void onResponse(Call<createProductResponse> call, Response<createProductResponse> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(addProduct.this,response.code(),Toast.LENGTH_LONG).show();
                }
                createProductResponse createProductResponse=response.body();
                Toast.makeText(addProduct.this,createProductResponse.getMessage(),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<createProductResponse> call, Throwable t) {
                Toast.makeText(addProduct.this,t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });


    }

}
