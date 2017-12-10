package sinabro.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.ebanx.swipebtn.OnStateChangeListener;
import com.ebanx.swipebtn.SwipeButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //트윈 애니메이션
        ImageView iv = (ImageView) findViewById(R.id.ball);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.rotate);
        iv.startAnimation(animation);

        SwipeButton swipeButton=(SwipeButton)findViewById(R.id.swipe_btn);
        swipeButton.setOnStateChangeListener(new OnStateChangeListener(){
            @Override
            public void onStateChange(boolean active){
                Toast.makeText(MainActivity.this,"Active:"+active, Toast.LENGTH_SHORT).show();
            }
        });

        //여기서부터 로그인 관련
        Intent intent=getIntent();

    }

}


