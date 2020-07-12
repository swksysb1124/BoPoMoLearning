package crop.computer.askey.bopomopractice;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import crop.computer.askey.bopomopractice.base.BaseBoPoMoActivity;

public class KnowBoPoMoActivity extends BaseBoPoMoActivity {

    private static final String TAG = "LOG_"+ListenPracticeActivity.class.getSimpleName();

    private int mForPlayIndex;
    private TextView txtBoPoMo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_know_bopomo);

        setActionBarTitle("認識ㄅㄆㄇ");

        txtBoPoMo = findViewById(R.id.txtBoPoMo);

        findViewById(R.id.btnPlay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadAndPlaySound(mForPlayIndex);
            }
        });

        findViewById(R.id.btbNext).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextToLearn();
            }
        });

        nextToLearn();
    }

    protected void nextToLearn() {
        mForPlayIndex = randomIndex();
        txtBoPoMo.setText(getForPlayBoPoMo());
        loadAndPlaySound(mForPlayIndex);
    }

    protected String getForPlayBoPoMo() {
        return getBoPoMo(mForPlayIndex);
    }

}
