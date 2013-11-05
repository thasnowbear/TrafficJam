package com.example.TrafficJam;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.*;
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
    public String[] solved = new String[40];

    public int level;
    private int m_cellWidth;
    private int m_cellHeight;
    private int diff;

    public void setLevel(int level) {
        this.level = level;
    }

    public int getlevel(){
        return level;
    }

    public int getDiff() {
        return diff;
    }

    public void setDiff(int diff) {
        this.diff = diff;
    }

    public String[] getSolved() {
        return solved;
    }

    private class MyShape {

        private MyShape(int color, char direction, int colum, int row, int length) {
            this.color = color;
            this.direction = direction;
            this.colum = colum;
            this.row = row;
            this.length = length;
        }

        private void makeRect(int width, int height) {
            if (direction == 'H')
                this.rect = new Rect(colum * width, row * height, colum * width + width * length, row * height + height);
            if (direction == 'V')
                this.rect = new Rect(colum * width, row * height, colum * width + width, row * height + height * length);
        }

        int colum, row, length;
        char direction;
        Rect rect;
        int color;
    }

    public DrawView(Context context) {
        super(context);
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

    void previous() {
        if (level != 0) {
            level--;
            getLevel();
        }
    }

    void restart(){
        getLevel();
    }

    void next() {
        if (level < 40) {
            level++;
            getLevel();
        }
    }

    void getLevel() {
        mShapes.clear();
        invalidate();
        Puzzle p = new Puzzle();
        p.LoadPuzzles();
        Puzzle currentlvl = p.puzzles.get(level);
        String setup = currentlvl.getSetup();
        String[] shapes = setup.split(",");
        mShapes.add(new MyShape(Color.RED, setup.charAt(1), Character.getNumericValue(setup.charAt(3)), Character.getNumericValue(setup.charAt(5)), Character.getNumericValue(setup.charAt(7))));
        for (int i = 1; i < shapes.length; ++i) {
            int rad = 0;
            if(getDiff() == 0)
                rad = i%6;
            if(getDiff() == 1)
                rad = i%3;
            if(getDiff() == 2)
                rad = 2;
            int[]  colors= {Color.YELLOW,Color.BLUE,Color.GREEN,Color.CYAN,Color.DKGRAY,Color.GRAY};
            MyShape m = new MyShape(colors[rad], shapes[i].charAt(2), Character.getNumericValue(shapes[i].charAt(4)), Character.getNumericValue(shapes[i].charAt(6)), Character.getNumericValue(shapes[i].charAt(8)));
            mShapes.add(m);
        }
        invalidate();
    }

    public DrawView(Context context, AttributeSet attrs) {
        super(context, attrs);
        getLevel();
    }

    protected void onDraw(Canvas canvas) {
        for (MyShape ms : mShapes) {
            mPaint.setColor(ms.color);
            ms.makeRect(m_cellWidth, m_cellHeight);


            if (ms.rect != null)
                canvas.drawRect(ms.rect, mPaint);
        }
    }


    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mMovingShape = findShape(x, y);
                break;
            case MotionEvent.ACTION_UP:
                if (mMovingShape != null) {
                    if (mMovingShape.color == Color.RED)
                        if (mMovingShape.rect.right == getHeight()) {
                            Toast.makeText(getContext(), "Puzzle Solved!", Toast.LENGTH_LONG).show();
                            solved[level] = "Y";
                            next();
                        }

                    mMovingShape = null;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (mMovingShape != null) {
                    boolean intersect = false;
                    y = Math.max(0, Math.min(y, getHeight() - mMovingShape.rect.height()));
                    x = Math.max(0, Math.min(x, getWidth() - mMovingShape.rect.width()));
                    if (mMovingShape.direction == 'V') {
                        boolean above = false;
                        boolean below = false;
                        if (findShape(mMovingShape.colum * m_cellWidth, (mMovingShape.row + mMovingShape.length) * m_cellHeight) != null)
                            below = true;
                        if (findShape(mMovingShape.colum * m_cellWidth, (mMovingShape.row - 1) * m_cellHeight) != null) {
                            above = true;
                        }
                        if (above && below) {
                            // Nothing to do Block is stuck
                        } else if (above)
                            mMovingShape.row = Math.max(yToRow(y), mMovingShape.row);
                        else if (below) {
                            mMovingShape.row = Math.min(yToRow(y), mMovingShape.row);
                        } else {
                            mMovingShape.row = yToRow(y);
                        }

                        invalidate();
                    }
                    if (mMovingShape.direction == 'H') {
                        boolean left = false;
                        boolean right = false;
                        if (findShape((mMovingShape.colum - 1) * m_cellWidth, mMovingShape.row * m_cellHeight) != null) {
                            left = true;
                        }
                        if (findShape((mMovingShape.colum + mMovingShape.length) * m_cellWidth, mMovingShape.row * m_cellHeight) != null) {
                            right = true;
                        }
                        if (left && right) {
                            // Nothing to do Block is stuck
                        } else if (left)
                            mMovingShape.colum = Math.max(xToCol(x), mMovingShape.colum);
                        else if (right) {
                            mMovingShape.colum = Math.min(xToCol(x), mMovingShape.colum);
                        } else {
                            mMovingShape.colum = xToCol(x);
                        }
                        invalidate();
                    }
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

    private MyShape findShape(int x, int y) {
        for (MyShape ms : mShapes) {
            if (ms.rect.contains(x, y)) {
                return ms;
            }
        }
        return null;
    }


}
