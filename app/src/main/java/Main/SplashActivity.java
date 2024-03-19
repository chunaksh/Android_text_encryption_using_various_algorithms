package Main;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.Algorithms.R;



public class SplashActivity extends AppCompatActivity {

    private int Splash_Screen = 3000;
    private ImageView LogoImg;
    private TextView Appname;
    private Animation topAnimation, leftAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        LogoImg = findViewById(R.id.SplashPC);
        //Appname = findViewById(R.id.SplashKeyboard);


        topAnimation = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        //leftAnimation = AnimationUtils.loadAnimation(this, R.anim.left_aimation);

        LogoImg.setAnimation(topAnimation);
        //Appname.setAnimation(leftAnimation);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        },Splash_Screen);
    }
}
