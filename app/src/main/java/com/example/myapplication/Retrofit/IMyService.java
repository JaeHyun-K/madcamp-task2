package com.example.myapplication.Retrofit;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface IMyService {

    @POST("insert")
    @FormUrlEncoded
    Observable<String> givename(@Field("name") String name);


    @POST("contacts/crud/create")
    @FormUrlEncoded
    Observable<String> makeContact(@Field("name") String name,
                                   @Field("number") String number,
                                   @Field("email") String email);
    @POST("contacts/crud/delete")
    @FormUrlEncoded
    Observable<String> deleteContact(@Field("name") String name,
                                   @Field("number") String number,
                                   @Field("email") String email);

    @POST("contacts/crud/research")
    @FormUrlEncoded
    Observable<String> researchContact(@Field("name") String name,
                                   @Field("number") String number,
                                   @Field("email") String email);

    @POST("contacts/crud/update")
    @FormUrlEncoded
    Observable<String> updateContact(@Field("name") String name,
                                   @Field("number") String number,
                                   @Field("email") String email);

    @POST("todo/crud/create")
    @FormUrlEncoded
    Observable<String> makeTodo(@Field("maketodo") String maketodo);


    @POST("todo/crud/delete")
    @FormUrlEncoded
    Observable<String> deleteTodo(@Field("tododelete") String tododelete);

    @POST("todo/crud/research")
    @FormUrlEncoded
    Observable<String> researchTodo(@Field("researchtodo") String researchtodo);


    @POST("todo/crud/update")
    @FormUrlEncoded
    Observable<String> updateTodo(@Field("updatetodo") String updatetodo);



}
