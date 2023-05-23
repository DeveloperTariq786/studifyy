package com.example.studifyy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ChatGPTActivity extends AppCompatActivity {
    RecyclerView rview;
    TextView left,right;
    EditText inputmassage;
    ImageButton sendicon;
    List<ChatGPTModalClass> list;
    ChatGPTAdapter adapter;
    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");
    OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_gptactivity);
        rview=findViewById(R.id.MassageRecycleView);
        list=new ArrayList<>();
        left=findViewById(R.id.lefttext);
        right=findViewById(R.id.righttext);
        inputmassage=findViewById(R.id.typing);
        sendicon=findViewById(R.id.sendbtn);
        adapter=new ChatGPTAdapter(list);
        rview.setAdapter(adapter);
        LinearLayoutManager llm= new LinearLayoutManager(this);
        llm.setStackFromEnd(true);
        rview.setLayoutManager(llm);
        sendicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String question= inputmassage.getText().toString().trim();
                addtochat(question,ChatGPTModalClass.send_by_me);
                inputmassage.setText("");
                callApi(question);
            }
        });

    }
     void addtochat(String massage,String sendby){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                list.add(new ChatGPTModalClass(massage,sendby));
                adapter.notifyDataSetChanged();
                rview.smoothScrollToPosition(adapter.getItemCount());
            }
        });

    }
    void addrespone(String respone){
        list.remove(list.size()-1);
        addtochat(respone,ChatGPTModalClass.getSend_by_bot);

    }
    void callApi(String question){
        list.add(new ChatGPTModalClass("Generating...",ChatGPTModalClass.getSend_by_bot));
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("modal","text-davinci-003");
            jsonObject.put("prompt",question);
            jsonObject.put("max_tokens",4000);
            jsonObject.put("temperature",0);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        RequestBody body=RequestBody.create(jsonObject.toString(),JSON);
        Request request=new Request.Builder()
                .url("https://api.openai.com/v1/completions")
                .header("Authorization","Bearer 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")
                .post(body)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                addrespone("Failed Due to"+e.getMessage());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()){
                    JSONObject jsonObject1= null;
                    try {
                        jsonObject1 = new JSONObject(response.body().string());
                        JSONArray array=jsonObject1.getJSONArray("choices");
                        String result=array.getJSONObject(0).getString("text");
                        addrespone(result.trim());
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }

                }
                else {
                    addrespone("Failed Due to"+response.body().toString());
                }
            }
        });
    }
}