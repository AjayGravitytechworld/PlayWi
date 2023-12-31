package com.abc.demo.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.abc.demo.R;
import com.abc.demo.model.aaLuckyItem;

import java.util.List;
import java.util.Random;

public class aaLuckyWheelView extends RelativeLayout implements aaPielView.PieRotateListener {
    private int mBackgroundColor;
    private int mTextColor;
    private int mTopTextSize;
    private int mSecondaryTextSize;
    private int mBorderColor;
    private int mTopTextPadding;
    private int mEdgeWidth;
    private Drawable mCenterImage;
    private Drawable mCursorImage;
    private aaPielView aaPielView;
    private ImageView ivCursorView;
    private LuckyRoundItemSelectedListener mLuckyRoundItemSelectedListener;

    @Override
    public void rotateDone(int index) {
        if (mLuckyRoundItemSelectedListener != null) {
            mLuckyRoundItemSelectedListener.LuckyRoundItemSelected(index);
        }
    }

    public interface LuckyRoundItemSelectedListener {
        void LuckyRoundItemSelected(int index);
    }

    public void setLuckyRoundItemSelectedListener(LuckyRoundItemSelectedListener listener) {
        this.mLuckyRoundItemSelectedListener = listener;
    }

    public aaLuckyWheelView(Context context) {
        super(context);
        init(context, null);
    }

    public aaLuckyWheelView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    /**
     * @param ctx
     * @param attrs
     */
    private void init(Context ctx, AttributeSet attrs) {
        if (attrs != null) {
            TypedArray typedArray = ctx.obtainStyledAttributes(attrs, R.styleable.LuckyWheelView);
            mBackgroundColor = typedArray.getColor(R.styleable.LuckyWheelView_lkwBackgroundColor, 0xffcc0000);
            mTopTextSize = typedArray.getDimensionPixelSize(R.styleable.LuckyWheelView_lkwTopTextSize, (int) convertDpToPixel(10f, getContext()));
            mSecondaryTextSize = typedArray.getDimensionPixelSize(R.styleable.LuckyWheelView_lkwSecondaryTextSize, (int) convertDpToPixel(20f, getContext()));
            mTextColor = typedArray.getColor(R.styleable.LuckyWheelView_lkwTopTextColor, 0);
            mTopTextPadding = typedArray.getDimensionPixelSize(R.styleable.LuckyWheelView_lkwTopTextPadding, (int) convertDpToPixel(10f, getContext())) + (int) convertDpToPixel(10f, getContext());
            mCursorImage = typedArray.getDrawable(R.styleable.LuckyWheelView_lkwCursor);
            mCenterImage = typedArray.getDrawable(R.styleable.LuckyWheelView_lkwCenterImage);
            mEdgeWidth = typedArray.getInt(R.styleable.LuckyWheelView_lkwEdgeWidth, 10);
            mBorderColor = typedArray.getColor(R.styleable.LuckyWheelView_lkwEdgeColor, 0);
            typedArray.recycle();
        }

        LayoutInflater inflater = LayoutInflater.from(getContext());
        FrameLayout frameLayout = (FrameLayout) inflater.inflate(R.layout.lucky_wheel_layout, this, false);

        aaPielView = frameLayout.findViewById(R.id.pieView);
        ivCursorView = frameLayout.findViewById(R.id.cursorView);

        aaPielView.setPieRotateListener(this);
        aaPielView.setPieBackgroundColor(mBackgroundColor);
        aaPielView.setTopTextPadding(mTopTextPadding);
        aaPielView.setTopTextSize(mTopTextSize);
        aaPielView.setSecondaryTextSizeSize(mSecondaryTextSize);
        aaPielView.setPieCenterImage(mCenterImage);
        aaPielView.setBorderColor(mBorderColor);
        aaPielView.setBorderWidth(mEdgeWidth);


        if (mTextColor != 0)
            aaPielView.setPieTextColor(mTextColor);

        ivCursorView.setImageDrawable(mCursorImage);
        addView(frameLayout);
    }

    public boolean isTouchEnabled() {
        return aaPielView.isTouchEnabled();
    }

    public void setTouchEnabled(boolean touchEnabled) {
        aaPielView.setTouchEnabled(touchEnabled);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        //This is to control that the touch events triggered are only going to the PieView
        for (int i = 0; i < getChildCount(); i++) {
            if (isPielView(getChildAt(i))) {
                return super.dispatchTouchEvent(ev);
            }
        }
        return false;
    }

    private boolean isPielView(View view) {
        if (view instanceof ViewGroup) {
            for (int i = 0; i < getChildCount(); i++) {
                if (isPielView(((ViewGroup) view).getChildAt(i))) {
                    return true;
                }
            }
        }
        return view instanceof aaPielView;
    }

    public void setLuckyWheelBackgrouldColor(int color) {
        aaPielView.setPieBackgroundColor(color);
    }

    public void setLuckyWheelCursorImage(int drawable) {
        ivCursorView.setBackgroundResource(drawable);
    }

    public void setLuckyWheelCenterImage(Drawable drawable) {
        aaPielView.setPieCenterImage(drawable);
    }

    public void setBorderColor(int color) {
        aaPielView.setBorderColor(color);
    }

    public void setLuckyWheelTextColor(int color) {
        aaPielView.setPieTextColor(color);
    }

    /**
     * @param data
     */
    public void setData(List<aaLuckyItem> data) {
        aaPielView.setData(data);
    }

    /**
     * @param numberOfRound
     */
    public void setRound(int numberOfRound) {
        aaPielView.setRound(numberOfRound);
    }

    /**
     * @param fixedNumber
     */
    public void setPredeterminedNumber(int fixedNumber) {
        aaPielView.setPredeterminedNumber(fixedNumber);
    }

    public void startLuckyWheelWithTargetIndex(int index) {
        aaPielView.rotateTo(index);
    }

    public void startLuckyWheelWithRandomTarget() {
        Random r = new Random();
        aaPielView.rotateTo(r.nextInt(aaPielView.getLuckyItemListSize() - 1));
    }

    public float convertDpToPixel(float dp, Context context) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
    }
}