package com.example.mydemo_retrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {
    private TextView textViewResult;
    JsonPlaceHolderApi jsonPlaceHolderApi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewResult = findViewById(R.id.text_view_result);
        Gson gson = new GsonBuilder().serializeNulls().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")  //base url
                .addConverterFactory(GsonConverterFactory.create())
                .build(); //Prepare request, use the builder to set some general options for all requests,
                            // i.e. the base URL or the converter.


        // Create a very simple REST adapter which points the GitHub API endpoint.
       jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

//       getPosts();

//        getComments();

//        createPost();

        updatePost();
    }


    private void getPosts()
    {
//for @QueryMap variable type
        Map<String, String> parameters = new HashMap<>();
        parameters.put("userId", "1");
        parameters.put("_sort", "id");
        parameters.put("_order", "desc");


// Fetch a list of the Github repositories.
//        Call<List<Post>> call = jsonPlaceHolderApi.getPosts(parameters);
//        Call<List<Post>> call = jsonPlaceHolderApi.getPosts();
//        Call<List<Post>> call = jsonPlaceHolderApi.getPosts(new Integer[]{1,2,3},"id","desc");
//        Call<List<Post>> call = jsonPlaceHolderApi.getPosts(null,null,null);
//        Call<List<Post>> call = jsonPlaceHolderApi.getPosts(new Integer[]{1,2,3},null,null);
        Call<List<Post>> call = jsonPlaceHolderApi.getPosts(1,2,null,null);

        // Execute the call asynchronously. Get a positive or negative callback.
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                // The network call was a success and we got a response
                // TODO: use the repository list and display it
                if (!response.isSuccessful()) {
                    textViewResult.setText("Code: " + response.code());
                    return;
                }

                List<Post> posts = response.body();

                for (Post post : posts) {
                    String content = "";
                    content += "ID: " + post.getId() + "\n";
                    content += "User ID: " + post.getUserId() + "\n";
                    content += "Title: " + post.getTitle() + "\n";
                    content += "Text: " + post.getText() + "\n\n";

                    textViewResult.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                // the network call was a failure
                // TODO: handle error
                textViewResult.setText(t.getMessage());
            }
        });
    }

    private void getComments()
    {
        Call<List<Comment>> call=jsonPlaceHolderApi.getComments(3);

        call.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                if(!response.isSuccessful())
                {
                    textViewResult.setText("Code: "+response.code());
                }

                List<Comment> comments= response.body();

                for(Comment comment:comments)
                {
                    String content = "";
                    content += "ID: " + comment.getId() + "\n";
                    content += "Post ID: " + comment.getPostId() + "\n";
                    content += "Name: " + comment.getName() + "\n";
                    content += "Email: " + comment.getEmail() + "\n";
                    content += "Text: " + comment.getText() + "\n\n";

                    textViewResult.append(content);
                }


            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {

            }
        });
    }

    private void createPost()
    {

        Map<String, String> map=new HashMap<>();
        map.put("userId","29");
        map.put("title","Monika");
        map.put("body","abababa");


        Post post=new Post(23,"New Title","New Text");

        Call<Post> call= jsonPlaceHolderApi.createPost(map);

        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (!response.isSuccessful()) {
                    textViewResult.setText("Code: " + response.code());
                    return;
                }


                Post postResponse=response.body();

                String content = "";
                content += "Code: " + response.code() + "\n";
                content += "ID: " + postResponse.getId() + "\n";
                content += "User ID: " + postResponse.getUserId() + "\n";
                content += "Title: " + postResponse.getTitle() + "\n";
                content += "Text: " + postResponse.getText() + "\n\n";


                textViewResult.setText(content);
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });

    }

    private void updatePost()
    {

        Post post = new Post(12, null, "New Text");

        Call<Post> call = jsonPlaceHolderApi.putPost(5, post);


        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {

                if(!response.isSuccessful())
                {
                    textViewResult.setText("Code:" +response.code());
                }

                Post postResponse=response.body();

                String content = "";
                content += "Code: " + response.code() + "\n";
                content += "ID: " + postResponse.getId() + "\n";
                content += "User ID: " + postResponse.getUserId() + "\n";
                content += "Title: " + postResponse.getTitle() + "\n";
                content += "Text: " + postResponse.getText() + "\n\n";


                textViewResult.setText(content);

            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                textViewResult.setText(t.getMessage());

            }
        });

    }
}
