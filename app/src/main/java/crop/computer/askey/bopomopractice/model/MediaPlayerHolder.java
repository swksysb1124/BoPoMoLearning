package crop.computer.askey.bopomopractice.model;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.util.Log;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MediaPlayerHolder{

    public static final int PLATBACK_POSITION_REFRESH_INTERVAL_MS = 1000;
    private static final String TAG = "LOG_"+MediaPlayerHolder.class.getSimpleName();

    private MediaPlayer mMediaPlayer;

    private Context mContext;

    // 利用 Executor 來確認目前的播放進度
    private ScheduledExecutorService mExecutor;
    private Runnable mSeekbarPositionUpdateTask;

    public MediaPlayerHolder(Context context) {
        initializeMediaPlayer();
        mContext = context;
    }

    private void initializeMediaPlayer() {
        mMediaPlayer = new MediaPlayer();

        mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Log.w(TAG, "play complete");
                stopUpdatingCallbackWithPosition(true);
            }
        });

        mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                play();
            }
        });
    }

    public void loadRawMedia(int resourceId) {
        Log.w(TAG, "load resource id: "+resourceId);
        mMediaPlayer.reset();

        AssetFileDescriptor assetFileDescriptor =
                mContext.getResources().openRawResourceFd(resourceId);
        try {
            mMediaPlayer.setDataSource(
                    assetFileDescriptor.getFileDescriptor(),
                    assetFileDescriptor.getStartOffset(),
                    assetFileDescriptor.getLength());
        } catch (IOException e) {
            return;
        }
        mMediaPlayer.prepareAsync();
    }

    public void loadHttpMedia(String url) {
        Log.w(TAG, "load url: "+url);
        mMediaPlayer.reset();

        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        try {
            mMediaPlayer.setDataSource(url);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        mMediaPlayer.prepareAsync();
    }

    public void release() {
        if(mMediaPlayer != null) {
            mMediaPlayer.release();
            mMediaPlayer = null;
        }

    }

    public boolean isPlaying() {
        if(mMediaPlayer != null){
            return mMediaPlayer.isPlaying();
        }
        return false;
    }

    public void play() {
        if(mMediaPlayer != null && !mMediaPlayer.isPlaying()){

            mMediaPlayer.start();

            startUpdatingCallbackWithPosition();
        }

    }

    public void pause() {
        if(mMediaPlayer != null && mMediaPlayer.isPlaying()) {
            mMediaPlayer.pause();
        }
    }

    public void seekTo(int position) {
        if(mMediaPlayer != null) {
            mMediaPlayer.seekTo(position);
        }
    }

    private void startUpdatingCallbackWithPosition() {
        if(mExecutor == null) {
            mExecutor = Executors.newSingleThreadScheduledExecutor();
        }

        if(mSeekbarPositionUpdateTask == null) {
            mSeekbarPositionUpdateTask = new Runnable() {
                @Override
                public void run() {
                    updateProgressCallbackTask();
                }
            };
        }

        mExecutor.scheduleAtFixedRate(
                mSeekbarPositionUpdateTask,
                0,
                PLATBACK_POSITION_REFRESH_INTERVAL_MS,
                TimeUnit.MILLISECONDS);
    }

    private void updateProgressCallbackTask() {
        if(mMediaPlayer != null && mMediaPlayer.isPlaying()) {
            int currentPosition = mMediaPlayer.getCurrentPosition();
            Log.d(TAG, "update progress: "+currentPosition);
        }
    }

    private void stopUpdatingCallbackWithPosition(boolean resetUIPlaybackPosition) {
        if(mExecutor != null) {
            mExecutor.shutdownNow();
            mExecutor = null;
            mSeekbarPositionUpdateTask = null;
        }
    }

}
