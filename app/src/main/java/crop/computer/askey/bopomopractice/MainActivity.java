package crop.computer.askey.bopomopractice;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setActionBarTitle("學習選單");

        findViewById(R.id.btnGoToKnowBoPoMoPage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, KnowBoPoMoActivity.class));
            }
        });

        findViewById(R.id.btnGoToListenPracticePage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ListenPracticeActivity.class));
            }
        });

    }

    protected void setActionBarTitle(String title) {
        if(getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }
    }
}
