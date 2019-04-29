package me.zhang.lab.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.View;

import me.zhang.lab.utils.MeasureUtil;


@SuppressLint("NewApi")
public class ShadowView extends View {
	private Paint mPaint;// 画笔

	private int left, top, right, bottom;// 绘制时坐标

	public ShadowView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// setShadowLayer不支持HW
		setLayerType(LAYER_TYPE_SOFTWARE, null);

		// 初始化画笔
		initPaint();

		// 初始化资源
		initRes(context);
	}

	/**
	 * 初始化画笔
	 */
	private void initPaint() {
		// 实例化画笔
		mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mPaint.setColor(Color.RED);
		mPaint.setStyle(Style.FILL);
		mPaint.setShadowLayer(10, 3, 3, Color.DKGRAY);
	}

	/**
	 * 初始化资源
	 */
	private void initRes(Context context) {
		/*
		 * 计算位图绘制时左上角的坐标使其位于屏幕中心
		 */
		left = MeasureUtil.getScreenSize((Activity) context)[0] / 4;
		top = MeasureUtil.getScreenSize((Activity) context)[1] / 4;
		right = MeasureUtil.getScreenSize((Activity) context)[0] * 3 / 4;
		bottom = MeasureUtil.getScreenSize((Activity) context)[1] * 3 / 4;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		// 先绘制位图
		canvas.drawRect(left, top, right, bottom, mPaint);
	}
}