package sinabro.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class DisabledLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_disabled);


        final EditText idText=(EditText)findViewById(R.id.idText);
        final EditText passwordText=(EditText)findViewById(R.id.passwordText);

        final Button loginBtn=(Button)findViewById(R.id.loginBtn);
        final TextView registerBtn=(TextView)findViewById(R.id.registerBtn);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent=new Intent(DisabledLoginActivity.this,DisabledRegisterActivity.class);
                DisabledLoginActivity.this.startActivity(registerIntent);
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String userID=idText.getText().toString();
                final String userPassword=passwordText.getText().toString();

                Response.Listener<String> responseListener=new Response.Listener<String>(){

                    @Override
                    public void onResponse(String response){
                        try{
                            JSONObject jsonResponse=new JSONObject(response);
                            boolean success=jsonResponse.getBoolean("success");
                            if(success){
                                String userID=jsonResponse.getString("userID");
                                String userPassword=jsonResponse.getString("userPassword");
                                Intent intent=new Intent(DisabledLoginActivity.this,DisabledMainActivity.class);
                                intent.putExtra("userID",userID);
                                intent.putExtra("userPassword", userPassword);
                                DisabledLoginActivity.this.startActivity(intent);
                            }else{
                                AlertDialog.Builder builder=new AlertDialog.Builder(DisabledLoginActivity.this);
                                builder.setMessage("로그인에 실패하였습니다")
                                        .setNegativeButton("다시시도", null)
                                        .create()
                                        .show();
                            }
                        }catch(Exception e){
                            e.printStackTrace();
                        }

                    }
                };
                DisabledLoginRequest disabledloginRequest=new DisabledLoginRequest(userID, userPassword, responseListener);
                RequestQueue queue= Volley.newRequestQueue(DisabledLoginActivity.this);
                queue.add(disabledloginRequest);
            }

        });
    }
}
