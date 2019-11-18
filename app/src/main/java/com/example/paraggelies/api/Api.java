package com.example.paraggelies.api;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

import com.example.paraggelies.models.createProductResponse;
import com.example.paraggelies.models.loginRespone;
import com.example.paraggelies.models.makeOrderResponse;
import com.example.paraggelies.models.orderResponse;
import com.example.paraggelies.models.productModel;
import com.example.paraggelies.models.productsResponse;
import com.example.paraggelies.models.sellerResponse;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

public interface Api {


    @FormUrlEncoded
    @POST("register")
    Call<ResponseBody> createUser(
      @Field("username") String username,
      @Field("password")String password,
      @Field("location") String location
    );

    @FormUrlEncoded
    @POST("seller/login")
    Call<sellerResponse> loginSeller(
            @Field("username") String username,
            @Field("password")String password
    );

    @FormUrlEncoded
    @POST("login")
    Call<loginRespone> loginUser(
        @Field("username") String username,
        @Field("password") String password
    );


    @GET("products")
    Call<productsResponse> getProducts(@Header("Authorization")String token);

    @Multipart
    @POST("products")
    Call<createProductResponse> createProduct(@Header("Authorization")String token, @Part("name") RequestBody name,@Part("price") RequestBody price,@Part MultipartBody.Part img );


    @GET("orders")
    Call<orderResponse> getAllorders(@Header("Authorization")String token);

    @GET("orders/customer/{customerId}")
    Call<orderResponse> getUserOrders(@Header("Authorization")String token, @Path("customerId") String customerId);

    @FormUrlEncoded
    @POST("orders")
    Call<makeOrderResponse> makeOrder(@Header("Authorization")String token, @Field("quantity") String quantity, @Field("productId") String productId, @Field("customerId") String customerId);


    @DELETE("orders/{orderId}")
    Call<ResponseBody> deleteOrder(@Header("Authorization")String token,@Path("orderId") String orderId);

    @FormUrlEncoded
    @POST("{customerId}")
    Call<ResponseBody> updateCustomer(@Header("Authorization") String token, @Path("customerId")String customerId,@Field("username") String username,@Field("location") String location);

}

