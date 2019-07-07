package com.example.myapplication.Retrofit;

import java.sql.Array;
import java.util.Arrays;
import java.util.Observable;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Path;

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
    Observable<String> makeTodo(@Field("name") String name,
                                   @Field("number") String number,
                                   @Field("email") String email);
    @POST("todo/crud/delete")
    @FormUrlEncoded
    Observable<String> deleteTodo(@Field("name") String name,
                                     @Field("number") String number,
                                     @Field("email") String email);

    @POST("todo/crud/research")
    @FormUrlEncoded
    Observable<String> researchTodo(@Field("name") String name,
                                       @Field("number") String number,
                                       @Field("email") String email);

    @POST("todo/crud/update")
    @FormUrlEncoded
    Observable<String> updateTodo(@Field("name") String name,
                                     @Field("number") String number,
                                     @Field("email") String email);


}
