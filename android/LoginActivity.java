package team.dmqqd.chengjitong;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText accountEdit;
    private EditText passwordEdit;
    private CheckBox monitor;
    int retCode;
    String s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        accountEdit = (EditText) findViewById(R.id.account);
        passwordEdit = (EditText) findViewById(R.id.password);
        monitor = (CheckBox)findViewById(R.id.is_monitor);
        Button login = (Button) findViewById(R.id.login_button);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start("http://surine.cn/php/androidlogin/Login.php");
            }
        });
    }

    private void start(String s) {
    //初始化okhttp客户端
    OkHttpClient client = new OkHttpClient.Builder().build();
    //创建post表单，获取username和password（没有做非空判断）
    RequestBody post = new FormBody.Builder()
            .add("username", accountEdit.getText().toString())
            .add("password", passwordEdit.getText().toString())
            .add("monitor","" + monitor.isChecked())
            .build();
    //开始请求，填入url，和表单
    final Request request = new Request.Builder()
            .url(s)
            .post(post)
            .build();

    //客户端回调
        client.newCall(request).enqueue(new Callback() {
        @Override
        public void onFailure(Call call, IOException e) {
            //失败的情况（一般是网络链接问题，服务器错误等）
        }

        @Override
        public void onResponse(Call call, final Response response) throws IOException {
            //UI线程运行
            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    try {
                        //临时变量（这是okhttp的一个锅，一次请求的response.body().string()只能用一次，否则就会报错）
                        LoginActivity.this.s = response.body().string();

                        //解析出后端返回的数据来
                        JSONObject jsonObject = new JSONObject(String.valueOf(LoginActivity.this.s));
                        retCode = jsonObject.getInt("success");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    //客户端自己判断是否成功。
                    if (retCode == 2) {
                        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                        intent.putExtra("isMonitor","0");
                        startActivity(intent);
                    }else if(retCode == 3){
                        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                        intent.putExtra("isMonitor","1");
                        startActivity(intent);
                    }else{
                        Toast.makeText(LoginActivity.this,"账号或密码错误!",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    });
}
}
