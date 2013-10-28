package com.example.TrafficJam;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Notandi
 * Date: 24.10.2013
 * Time: 17:51
 * To change this template use File | Settings | File Templates.
 */
public class DrawView extends View {

    char[][] m_board = new char[6][6];
    private int m_cellWidth;
    private int m_cellHeight;
    private class MyShape{
        private MyShape(Rect rect, int color, char direction) {
            this.rect = rect;
            this.color = color;
            this.direction = direction;

        }


        char direction;
        Rect rect;
        int color;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int size = Math.min(getMeasuredWidth(), getMeasuredHeight());
        setMeasuredDimension(size, size);
    }

    @Override
    protected void onSizeChanged(int xNew, int yNew, int xOld, int yold) {
        m_cellHeight = yNew / 6;
        m_cellWidth = xNew / 6;
    }

    Paint mPaint = new Paint();
    ArrayList<MyShape> mShapes = new ArrayList<MyShape>();
    MyShape mMovingShape = null;

    public DrawView(Context context, AttributeSet attrs) {
        super(context, attrs);


        mShapes.add(new MyShape(new Rect(0,100,100,150) , Color.RED, 'V'));
        mShapes.add(new MyShape(new Rect(160,50,210,150), Color.BLUE, 'H'));
        mShapes.add(new MyShape(new Rect(220,80,270,180) , Color.BLUE, 'H'));
        mShapes.add(new MyShape(new Rect(200,200,300,250), Color.BLUE, 'V'));
    }

    protected void onDraw(Canvas canvas){
        for(MyShape ms: mShapes){
            mPaint.setColor(ms.color);
            canvas.drawRect(ms.rect, mPaint);
        }
    }


    public boolean onTouchEvent(MotionEvent event){

        int x =(int) event.getX();
        int y =(int) event.getY();
        switch( event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mMovingShape = findShape(x,y);
                break;
            case MotionEvent.ACTION_UP:
                if(mMovingShape != null){
                    if(mMovingShape.color == Color.RED)
                        if(mMovingShape.rect.right == getHeight())
                            Toast.makeText(getContext(), "Puzzle Solved!", Toast.LENGTH_LONG).show();

                    mMovingShape = null;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if(mMovingShape != null){
                    boolean intersect = false;
                    y = Math.min(y, getHeight() - mMovingShape.rect.height());
                    x = Math.min(x, getWidth() - mMovingShape.rect.width());
                    if(mMovingShape.direction == 'V'){
                        for(MyShape ms: mShapes){
                        if(mMovingShape.rect.intersect(ms.rect) && mMovingShape.rect != ms.rect){
                            intersect = true;
                            mMovingShape.rect.offsetTo(mMovingShape.rect.left - 101,mMovingShape.rect.top);
                            mMovingShape.rect.right = mMovingShape.rect.left + 100;
                            System.out.println(mMovingShape.rect.width());
                            invalidate();
                        }
                    }
                    if(!intersect)
                    mMovingShape.rect.offsetTo(x,mMovingShape.rect.top);
                    }
                    if(mMovingShape.direction == 'H'){
                        for(MyShape ms: mShapes){
                            if(mMovingShape.rect.intersect(ms.rect) && mMovingShape.rect != ms.rect){
                                intersect = true;
                            }
                        }
                            if(!intersect)
                                mMovingShape.rect.offsetTo(mMovingShape.rect.left,y);
                    }
                    if(!intersect)
                        invalidate();
                }
                break;
        }
        return true;
    }

    private int xToCol(int x) {
        return x / m_cellWidth;
    }

    private int yToRow(int y) {
        return y / m_cellHeight;
    }

    private MyShape findShape(int x, int y){
        for(MyShape ms: mShapes){
            if(ms.rect.contains(x,y)){
                return ms;
            }
        }
        return null;
    }



}
