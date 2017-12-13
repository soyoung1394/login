package sinabro.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class SelectiveActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selective);

        Button helperbtn=(Button)findViewById(R.id.helperbtn);
        Button disabledbtn=(Button)findViewById(R.id.disabledbtn);

        Button service=(Button)findViewById(R.id.service);
        Button privacy=(Button)findViewById(R.id.privacy);

        helperbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent helperIntent=new Intent(SelectiveActivity.this,LoginActivity.class);
                SelectiveActivity.this.startActivity(helperIntent);
            }
        });

        disabledbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent disabledIntent=new Intent(SelectiveActivity.this,DisabledLoginActivity.class);
                SelectiveActivity.this.startActivity(disabledIntent);
            }
        });
    }
}
