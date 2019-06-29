package com.example.mydemo_retrofit;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface JsonPlaceHolderApi {

    @GET("posts")  //retalive url
    Call<List<Post>> getPosts();

    @GET("posts")
    Call<List<Post>> getPosts(@QueryMap Map<String, String> parameters);

    @GET("posts")
    Call<List<Post>> getPosts(
            @Query("userId") Integer[] userId,
            @Query("_sort") String sort,
            @Query("_order") String order
    );

    @GET("posts")
    Call<List<Post>> getPosts(
            @Query("userId") Integer userId1,
            @Query("userId") Integer userId2,
            @Query("_sort") String sort,
            @Query("_order") String order
    );

    @GET("posts/{id}/comments")
    Call<List<Comment>> getComments(@Path("id") int postId);

    @GET
    Call<List<Comment>> getComments(@Url String url);


    //send data to the server by serializing post object into JSON format then put it to http
    // request body and the server retrive it and process further

    @POST("posts")
    Call<Post> createPost(@Body Post post);

    //@Query parameter send to url but @Field send direct to  http body-

    @POST("posts")
    Call<Post> createPost(
            @Field("userId") int id,
            @Field("title") int title,
            @Field("body") int text

    );

    //    just another way to encoding input
    @FormUrlEncoded
    @POST("posts")
    Call<Post> createPost(@FieldMap Map<String, String> fields);

    @PUT("posts/{id}")
    Call<Post> putPost(@Path("id") int id,@Body Post post);//@Path placeholders we dynamically specify the URL endpoint on which we want to modify a resource.

    @PATCH("posts/{id}")
    Call<Post> patchPost(@Path("id") int id, @Body Post post);

    @DELETE("posts/{id}")
    Call<Void> deletePost(@Path("id") int id);

}






