package sinabro.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

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
        final CheckBox checkService = (CheckBox)findViewById(R.id.checkService);
        final CheckBox checkPrivacy = (CheckBox)findViewById(R.id.checkPrivacy);
        final EditText idText=(EditText)findViewById(R.id.idText);
        final EditText passwordText=(EditText)findViewById(R.id.passwordText);
        final EditText nameText=(EditText)findViewById(R.id.nameText);
        final EditText phoneText=(EditText)findViewById(R.id.phoneText);
        final EditText photo=(EditText)findViewById(R.id.photo);
        final Button registerBtn=(Button)findViewById(R.id.registerBtn);
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

                final String userID = idText.getText().toString();
                final String userPassword = passwordText.getText().toString();
                final String userName = nameText.getText().toString();
                final String userPhone = phoneText.getText().toString();
                final String userPhoto = photo.getText().toString();
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            if (checkPrivacy.isChecked() == true && checkService.isChecked() == true && userID.getBytes().length > 0&& userPassword.getBytes().length > 0&& userName.getBytes().length > 0&& userPhone.getBytes().length > 0)
                            {
                                JSONObject jsonResponse = new JSONObject(response);
                                boolean success = jsonResponse.getBoolean("success");
                                if (success) {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(DisabledRegisterActivity.this);
                                    builder.setMessage("회원 등록에 성공했습니다.")
                                            .setPositiveButton("확인", null)
                                            .create()
                                            .show();
                                    Intent intent = new Intent(DisabledRegisterActivity.this, DisabledLoginActivity.class);
                                    DisabledRegisterActivity.this.startActivity(intent);
                                } else {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(DisabledRegisterActivity.this);
                                    builder.setMessage("회원 등록에 실패했습니다.")
                                            .setNegativeButton("다시 시도", null)
                                            .create()
                                            .show();
                                }
                            }else{
                                Toast.makeText(DisabledRegisterActivity.this, "약관에 동의를 하지 않았거나 입력되지 않은 내용이 있습니다.", Toast.LENGTH_SHORT).show();

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
