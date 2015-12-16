package com.nianzhen.soundrecording;

import android.app.Activity;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.nianzhen.soundrecording.View.RecordButton;
import com.nianzhen.soundrecording.utils.AudioRecorder;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnItemClick;

public class MainActivity extends Activity {

    private static String TAG = "";
    //语音文件保存路径
    private static final String FILENAME = Environment.getExternalStorageDirectory().getPath() + "/SoundRecording/MP3/amr_";
    private static final String FILE_NAME_SUFFIX = ".amr";
    //语音操作对象
    private MediaPlayer mPlayer = null;
    private MediaRecorder mRecorder = null;

    private String path;

    @Bind(R.id.recounding)
    TextView recounding;

    private Cursor cursor;

    @Bind(R.id.list)
    ListView list;

    @Bind(R.id.soundrecording)
    RecordButton soundrecording;

    @Bind(R.id.editTexte)
    EditText editTexte;


    @OnItemClick(R.id.list)
    public void startPlay() {
        mPlayer = new MediaPlayer();
        try {
            mPlayer.setDataSource(path);
            mPlayer.prepare();
            mPlayer.start();
        } catch (IOException e) {
            Log.e(TAG, "播放失败");
        }
        //停止播放
//        mPlayer.release();
//        mPlayer = null;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        soundrecording.setAudioRecord(new AudioRecorder());
//        soundrecording.setOnTouchListener(new SoundRecordTouchLister());
    }

    private class SoundRecordTouchLister implements View.OnTouchListener {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                mRecorder = new MediaRecorder();
                mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
                long current = System.currentTimeMillis();
                path = FILENAME + String.valueOf(current) + FILE_NAME_SUFFIX;
                mRecorder.setOutputFile(path);
                mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
                try {
                    mRecorder.prepare();
                    mRecorder.start();
                } catch (IOException e) {
                    Log.e(TAG, "prepare() failed");
                } catch (IllegalStateException e) {
                    Log.e(TAG, e.toString());
                }

                recounding.setVisibility(View.VISIBLE);
            } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
                if (mRecorder == null) {
                    Log.d(TAG, "not found MediaRecorder");
                }
                recounding.setVisibility(View.VISIBLE);
            } else if (event.getAction() == MotionEvent.ACTION_UP) {
                try {
                    mRecorder.stop();
                    mRecorder.release();
                } catch (IllegalStateException e) {
                    Log.e(TAG, e.toString());
                }
                mRecorder = null;
                recounding.setVisibility(View.GONE);
            }
            return true;
        }
    }

}
