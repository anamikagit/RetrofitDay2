package com.example.aarya.retrofitday2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.example.aarya.retrofitday2.model.QuestPojo;
import com.example.aarya.retrofitday2.rest.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

import static android.R.attr.data;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    @BindView(R.id.spinner)
    Spinner spinner;
    @BindView(R.id.get_spinner)
    Spinner get_spinner;
    @BindView(R.id.submitButton)
    Button submit_btn;
    @BindView(R.id.get_submitButton)
    Button getSubmit_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

       //spinner.setOnItemClickListener(this);
        List<String> data = new ArrayList<String>();
        data.add("anu");
        data.add("afff");
        data.add("afs");
        data.add("sdf");
        data.add("amk");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,data);
        spinner.setAdapter(dataAdapter);
        spinner.setOnItemSelectedListener(this);

        getSubmit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"making get call",Toast.LENGTH_LONG).show();
                networkGetCAll();
            }
        });


        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"submitting item --->"+spinner.getSelectedItem(),Toast.LENGTH_LONG).show();
                networkPostCall((String) spinner.getSelectedItem());
            }
        });
    }

        public void networkGetCAll(){
         getApi().getRequest().enqueue(new Callback<List<QuestPojo>>() {
             @Override
             public void onResponse(Call<List<QuestPojo>> call, Response<List<QuestPojo>> response) {

                 List<QuestPojo> dataList = response.body();
                 List<String>topicList = new ArrayList();
                 for (int i = 0 ; i<dataList.size() ; i++){
                  String nameTopic = dataList.get(i).getTopicName();
                     topicList.add(nameTopic);
                 }


                 ArrayAdapter<String> adapter = new ArrayAdapter<String>
                         (MainActivity.this,R.layout.support_simple_spinner_dropdown_item,topicList);
                 get_spinner.setAdapter(adapter);


             }

             @Override
             public void onFailure(Call<List<QuestPojo>> call, Throwable t) {

             }
         });
        }

        public void networkPostCall(String pressedItem){
            getApi().postRequest(new QuestPojo(pressedItem)).enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {

                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {

                }
            });
        }



        public  ApiInterface getApi(){

            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient okHttpClient = new OkHttpClient().newBuilder().addInterceptor(loggingInterceptor).build();

            Retrofit retrofit = new Retrofit.Builder()
                                            .baseUrl("http://192.168.0.2:8080")
                                            .addConverterFactory(MoshiConverterFactory.create())
                                            .client(okHttpClient)
                                            .build();

            String pressedItem = (String) spinner.getSelectedItem();

            ApiInterface apiInterface = retrofit.create(ApiInterface.class);

            return apiInterface;
    }



    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    Toast.makeText(MainActivity.this,"Item Selected--->"+ spinner.getSelectedItem(),Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
