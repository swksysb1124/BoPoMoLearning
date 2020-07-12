package crop.computer.askey.bopomopractice;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashSet;

import crop.computer.askey.bopomopractice.base.BaseBoPoMoActivity;

public class ListenPracticeActivity extends BaseBoPoMoActivity {

    private static final String TAG = "LOG_"+ListenPracticeActivity.class.getSimpleName();

    private int quizIndex = 0;
    int[] selectedIndexs = new int[4];

    private TextView txtSelection1;
    private TextView txtSelection2;
    private TextView txtSelection3;
    private TextView txtSelection4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listen_practice);

        setActionBarTitle("注音聽力練習");

        txtSelection1 = findViewById(R.id.txtSelection1);
        txtSelection2 = findViewById(R.id.txtSelection2);
        txtSelection3 = findViewById(R.id.txtSelection3);
        txtSelection4 = findViewById(R.id.txtSelection4);

        txtSelection1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkQuizAnswer(0);
            }
        });

        txtSelection2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkQuizAnswer(1);
            }
        });

        txtSelection3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkQuizAnswer(2);
            }
        });

        txtSelection4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkQuizAnswer(3);
            }
        });

        findViewById(R.id.btnPlay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadAndPlaySound(quizIndex);
            }
        });

        findViewById(R.id.btbNext).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextQuiz();
            }
        });

        nextQuiz();
    }


    private void nextQuiz() {

        HashSet<Integer> set = new HashSet<>();
        while(set.size() < 4){
            set.add(randomIndex());
        }
        Log.d(TAG, "selections = "+set.toString());

        // 隨機選擇四個注音符號，作為選項
        int i = 0;
        for(Integer index : set) {
            selectedIndexs[i] = index;
            i++;
        }

        txtSelection1.setText(getBoPoMo(selectedIndexs[0]));
        txtSelection2.setText(getBoPoMo(selectedIndexs[1]));
        txtSelection3.setText(getBoPoMo(selectedIndexs[2]));
        txtSelection4.setText(getBoPoMo(selectedIndexs[3]));

        // 任選一個作為考題
        quizIndex = selectedIndexs[randomIndex(4)];

        Log.e(TAG, "quizIndex = "+quizIndex);
        loadAndPlaySound(quizIndex);
    }

    private void checkQuizAnswer(int select) {
        if(selectedIndexs[select] == quizIndex) {
            new AlertDialog.Builder(this)
                    .setTitle("恭喜")
                    .setMessage("答對了")
                    .setPositiveButton("下一題", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            nextQuiz();
                        }
                    })
                    .create().show();
        }else {
            new AlertDialog.Builder(this)
                    .setTitle("真遺憾")
                    .setMessage("答錯了，再接再厲")
                    .setPositiveButton("再聽一次", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            loadAndPlaySound(quizIndex);
                        }
                    })
                    .create().show();
        }
    }
}
