package com.nianzhen.soundrecording;

import android.app.Activity;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.nianzhen.soundrecording.bean.NoteDao;
import com.orhanobut.logger.Logger;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnItemClick;

public class MainActivity extends Activity {

    private static String TAG = "";

    private Cursor cursor;

    @Bind(R.id.list)
    ListView list;

    @Bind(R.id.soundrecording)
    Button soundrecording;

    @Bind(R.id.editTexte)
    EditText editTexte;


    @OnItemClick(R.id.soundrecording)
    public void startPlay() {
        mPlayer = new MediaPlayer();
        try {
            mPlayer.setDataSource(FileName);
            mPlayer.prepare();
            mPlayer.start();
        } catch (IOException e) {
            Logger.e("播放失败");
        }
        //停止播放
//        mPlayer.release();
//        mPlayer = null;

    }

    //语音文件保存路径
    private String FileName = null;
    //语音操作对象
    private MediaPlayer mPlayer = null;
    private MediaRecorder mRecorder = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
//        HttpClient.getInstance.getUser("asd").observeOn(AndroidSchedulers.mainThread())
//                .subscribeOn(Schedulers.
        soundrecording.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                return true;
            }
        });


        String textColumn = NoteDao.Properties.Text.columnName;

    }

    private class SoundRecordTouchLister implements View.OnTouchListener {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            // TODO: 2015/12/10 录音
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                // TODO: 2015/12/10 开始录音
                mRecorder = new MediaRecorder();
                mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
                mRecorder.setOutputFile(FileName);
                mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
                try {
                    mRecorder.prepare();
                } catch (IOException e) {
                    Logger.e("prepare() failed");
                }
                mRecorder.start();
            } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
                //// TODO: 2015/12/10 一直录音
                if (mRecorder == null) {
                    Logger.d("not found MediaRecorder");
                }
            } else if (event.getAction() == MotionEvent.ACTION_UP) {
                // TODO: 2015/12/10 结束录音
                mRecorder.stop();
                mRecorder.release();
                mRecorder = null;
            }
            return true;
        }
    }

}
