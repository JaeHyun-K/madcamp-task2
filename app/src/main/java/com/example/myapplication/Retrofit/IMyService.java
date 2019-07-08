package com.example.myapplication.Retrofit;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface IMyService {

    @POST("insert")
    @FormUrlEncoded
    Observable<String> givename(@Field("name") String name);

    @POST("contacts")
    @FormUrlEncoded
    Observable<String> getContacts(@Field("name") String name);

    @POST("gallery")
    @FormUrlEncoded
    Observable<String> getGallery(@Field("name") String name);

    @POST("todo")
    @FormUrlEncoded
    Observable<String> getTodo(@Field("name") String name);

    @POST("contacts/crud/create")
    @FormUrlEncoded
    Observable<String> makeContact(@Field("account") String account,
                                    @Field("name") String name,
                                   @Field("number") String number,
                                   @Field("email") String email);
    @POST("contacts/crud/delete")
    @FormUrlEncoded
    Observable<String> deleteContact(@Field("account") String account,@Field("name") String name,
                                   @Field("number") String number);

    @POST("contacts/crud/research")
    @FormUrlEncoded
    Observable<String> researchContact(@Field("account") String account,@Field("name") String name,
                                   @Field("number") String number,
                                   @Field("email") String email);

    @POST("contacts/crud/update")
    @FormUrlEncoded
    Observable<String> updateContact(@Field("account") String account,@Field("name") String name,
                                   @Field("number") String number,
                                   @Field("email") String email);

    @POST("todo/crud/create")
    @FormUrlEncoded
    Observable<String> makeTodo(@Field("name") String name,@Field("maketodo") String maketodo);


    @POST("todo/crud/delete")
    @FormUrlEncoded
    Observable<String> deleteTodo(@Field("name") String name,@Field("tododelete") String tododelete);

    @POST("todo/crud/research")
    @FormUrlEncoded
    Observable<String> researchTodo(@Field("name") String name,@Field("researchtodo") String researchtodo);


    @POST("todo/crud/update")
    @FormUrlEncoded
    Observable<String> updateTodo(@Field("name") String name,@Field("updatetodo") String updatetodo);

    @POST("gallery/crud/delete")
    @FormUrlEncoded
    Observable<String> deleteGallery(@Field("image") String image);


}
