package crop.computer.askey.bopomopractice.base;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Random;

import crop.computer.askey.bopomopractice.model.BoPoMoPlayer;
import crop.computer.askey.bopomopractice.model.MediaPlayerHolder;

public class BaseBoPoMoActivity extends AppCompatActivity {

    private String[] mBoPoMoList = {
            "ㄅ","ㄆ","ㄇ","ㄈ",
            "ㄉ", "ㄊ", "ㄋ", "ㄌ",
            "ㄍ", "ㄎ", "ㄏ",
            "ㄐ", "ㄑ", "ㄒ",
            "ㄓ", "ㄔ", "ㄕ", "ㄖ",
            "ㄗ", "ㄘ", "ㄙ",
            "ㄚ", "ㄛ", "ㄜ", "ㄝ",
            "ㄞ", "ㄟ", "ㄠ", "ㄡ",
            "ㄢ", "ㄣ", "ㄤ", "ㄥ",
            "ㄦ", "ㄧ", "ㄨ", "ㄩ"
    };

    private BoPoMoPlayer boPoMoPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boPoMoPlayer = new BoPoMoPlayer(this);
    }


    protected void setActionBarTitle(String title) {
        if(getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }
    }

    protected int randomIndex() {
        return randomIndex(36);
    }

    protected int randomIndex(int range) {
        Random ran = new Random();
        return ran.nextInt(range); // 0~26
    }

    protected void loadAndPlaySound(int index) {
        boPoMoPlayer.loadRawMedia(index);
    }

    protected String getBoPoMo(int index) {
        return mBoPoMoList[index];
    }
}
