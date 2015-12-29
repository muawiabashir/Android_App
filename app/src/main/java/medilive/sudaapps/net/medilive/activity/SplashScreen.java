package medilive.sudaapps.net.medilive.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import medilive.sudaapps.net.medilive.R;

/**
 * Created by muawia.ibrahim on 10/14/2015.
 */
public class SplashScreen extends Activity {
    Animation myAnimation;
    TextView myText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_layout);
        myText = (TextView) findViewById(R.id.title);
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setAnimation(myAnimation);

        ProgressBar progressBar =new ProgressBar(this);
        progressBar.setVisibility(View.VISIBLE);
        Button btn_arabic = (Button) findViewById(R.id.Button03);
        btn_arabic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login = new Intent(SplashScreen.this, LoginActivity.class);
                startActivity(login);
            }
        });

    }
}
