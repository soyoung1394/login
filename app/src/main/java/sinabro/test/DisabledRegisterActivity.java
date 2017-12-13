package sinabro.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class DisabledRegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_disabled);

        final EditText idText=(EditText)findViewById(R.id.idText);
        final EditText passwordText=(EditText)findViewById(R.id.passwordText);
        final EditText nameText=(EditText)findViewById(R.id.nameText);
        final EditText phoneText=(EditText)findViewById(R.id.phoneText);
        final EditText photo=(EditText)findViewById(R.id.photo);
        Button registerBtn=(Button)findViewById(R.id.registerBtn);
        Button service=(Button)findViewById(R.id.service);
        Button privacy=(Button)findViewById(R.id.privacy);

        service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent serviceIntent=new Intent(DisabledRegisterActivity.this,ServiceActivity.class);
                DisabledRegisterActivity.this.startActivity(serviceIntent);
            }
        });

        privacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent privacyIntent=new Intent(DisabledRegisterActivity.this,PrivacyActivity.class);
                DisabledRegisterActivity.this.startActivity(privacyIntent);
            }
        });


        registerBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String userID = idText.getText().toString();
                String userPassword = passwordText.getText().toString();
                String userName = nameText.getText().toString();
                String userPhone = phoneText.getText().toString();
                String userPhoto = photo.getText().toString();
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(DisabledRegisterActivity.this);
                                builder.setMessage("회원 등록에 성공했습니다.")
                                        .setPositiveButton("확인", null)
                                        .create()
                                        .show();
                                Intent intent = new Intent(DisabledRegisterActivity.this, LoginActivity.class);
                                DisabledRegisterActivity.this.startActivity(intent);
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(DisabledRegisterActivity.this);
                                builder.setMessage("회원 등록에 실패했습니다.")
                                        .setNegativeButton("다시 시도", null)
                                        .create()
                                        .show();
                            }
                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                };
                DisabledRegisterRequest disabledregisterRequest = new DisabledRegisterRequest(userID, userPassword, userName, userPhone, userPhoto, responseListener);
                RequestQueue disabledqueue= Volley.newRequestQueue(DisabledRegisterActivity.this);
                disabledqueue.add(disabledregisterRequest);
            }
        });

    }
}
