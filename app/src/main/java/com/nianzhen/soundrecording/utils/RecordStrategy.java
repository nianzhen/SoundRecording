package com.nianzhen.soundrecording.utils;

/**
 * Created by Administrator on 2015/12/16.
 */
public interface RecordStrategy {
    /**
     * 在这里进行录音准备工作，重置录音文件名等
     */
    public void ready();

    /**
     * 开始录音
     */
    public void start();

    /**
     * 录音结束
     */
    public void stop();

    /**
     * 录音失败时删除原来的旧文件
     */
    public void deleteOldFile();

    /**
     * 获取录音音量的大小
     *
     * @return
     */
    public double getAmplitude();

    /**
     * 返回录音文件完整路径
     *
     * @return
     */
    public String getFilePath();
}
