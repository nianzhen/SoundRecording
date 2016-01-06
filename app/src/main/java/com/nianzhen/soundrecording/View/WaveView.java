package com.nianzhen.soundrecording.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2015/12/25.
 */
public class WaveView extends View {
    /**
     * 外圈效果
     */
    private Paint mWaveCircle;

    /**
     * 容器
     */
    private Paint mContainerCircle;

    /**
     * 是否展示动态效果
     */
    private boolean isEffect = false;

    /**
     * 半径
     */
    private int radius;

    /**
     *
     */
    private int CENTER_X;
    private int CENTER_Y;

    /**
     *
     */
    private int width;
    private int height;

    private int firstCount = 1;
    private boolean isAdd = true;
    private boolean isReduce;

    public WaveView(Context context) {
        super(context);
        initView();
    }

    public WaveView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public WaveView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }


    private void initView() {
        radius = 200;
        mWaveCircle = new Paint();
        mWaveCircle.setColor(Color.WHITE);
        mWaveCircle.setStyle(Paint.Style.STROKE);
        mWaveCircle.setAntiAlias(true);
        mContainerCircle = new Paint(mWaveCircle);
        mContainerCircle.setStrokeWidth(10);
        mContainerCircle.setAntiAlias(true);
        handler.sendEmptyMessageDelayed(2, 10);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        width = MeasureSpec.getSize(widthMeasureSpec);
        height = MeasureSpec.getSize(heightMeasureSpec);
        radius = width / 6;
        CENTER_X = CENTER_Y = width / 2;
        setMeasuredDimension(width, height);
    }

    Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            if (msg.what == 1) {
                invalidate();
                handler.sendEmptyMessageDelayed(1, 1);
            } else {
                // 判断是否达到最大，达到最大时往回缩
                if (firstCount > 0 && isAdd) {
                    firstCount += 2;
                }
                if (firstCount > 100 || isReduce) {
                    isAdd = false;
                    isReduce = true;
                    firstCount -= 2;
                    if (firstCount <= 1) {
                        isAdd = true;
                        isReduce = false;
                    }
                }
                postInvalidate();
                handler.sendEmptyMessageDelayed(2, 5);
            }
        }

        ;
    };

    @Override
    protected void onDraw(Canvas canvas) {

        // 最多能扩大多少
        float f1 = 3 * radius / 10;
//         计算百分比距离
        float f2 = f1 * firstCount / 100.0F;

        // 最小圆形
        mWaveCircle.setAlpha(0);
        mWaveCircle.setStrokeWidth(5);
        canvas.drawCircle(width / 2, height / 2, radius + f2, mWaveCircle);
        // 第二层圆形
        mWaveCircle.setAlpha(120);
        mWaveCircle.setStrokeWidth(3);
        canvas.drawCircle(width / 2, height / 2, radius + f2 + 2, mWaveCircle);
        // 第二层圆形
        mWaveCircle.setAlpha(220);
        mWaveCircle.setStrokeWidth(1);
        canvas.drawCircle(width / 2, height / 2, radius + f2 + 3, mWaveCircle);
//        canvas.drawCircle(width / 2, height / 2, radius + 40, mWaveCircle);
        // 波浪的容器
        canvas.drawCircle(width / 2, height / 2, radius - f2 / 3, mContainerCircle);
        // 扩散光圈效果

    }
}
