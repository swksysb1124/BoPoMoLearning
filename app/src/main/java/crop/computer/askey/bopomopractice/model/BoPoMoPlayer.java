package crop.computer.askey.bopomopractice.model;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;

import java.io.IOException;

import crop.computer.askey.bopomopractice.util.AudioSourceUtil;

public class BoPoMoPlayer extends MediaPlayerHolder{

    public BoPoMoPlayer(Context context) {
        super(context);
    }

    public void loadRawMedia(int index) {
        int resourceId = AudioSourceUtil.getAudioResourceId(index);
        super.loadRawMedia(resourceId);
    }
}
