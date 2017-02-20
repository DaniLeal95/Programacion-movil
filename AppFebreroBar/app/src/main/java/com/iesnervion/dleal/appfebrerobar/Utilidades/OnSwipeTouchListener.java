package com.iesnervion.dleal.appfebrerobar.Utilidades;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Dani on 20/02/2017.
 */

public class OnSwipeTouchListener implements View.OnTouchListener {
    private SwipeListener swipeListener;
    private int hundred;

    public static enum Action {
        LR, // Left to right
        RL, // Right to left
        TB, // Top to bottom
        BT, // Bottom to top
        None // Action not found
    }

    private static final int HORIZONTAL_MIN_DISTANCE = 30; // The minimum
    // distance for
// horizontal swipe
    private static final int VERTICAL_MIN_DISTANCE = 80; // The minimum distance
    // for vertical
// swipe
    private float downX, downY, upX, upY; // Coordinates
    private Action mSwipeDetected = Action.None; // Last action

    public OnSwipeTouchListener(Context context) {
        //hundred = (int) context.getResources().getDimension();
    }

    public boolean swipeDetected() {
        return mSwipeDetected != Action.None;
    }

    public Action getAction() {
        return mSwipeDetected;
    }

    /**
     * Swipe detection
     */@Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            {
                downX = event.getX();
                downY = event.getY();
                mSwipeDetected = Action.None;
                return false; // allow other events like Click to be processed
            }
            case MotionEvent.ACTION_MOVE:
            {
                upX = event.getX();
                upY = event.getY();

                float deltaX = downX - upX;
                float deltaY = downY - upY;

                float absX = Math.abs(deltaX);
                float absY = Math.abs(deltaY);

                if((absX >= (3 * absY)) && absX <= hundred) {
                    if (deltaX > 0) {
                        mSwipeDetected = Action.RL;
                        swipeListener.onSwipe(MotionEvent.ACTION_MOVE, Action.RL, absX);
                        return false;
                    }
                }
                return false;
            }
            case MotionEvent.ACTION_UP:
                swipeListener.onSwipe(MotionEvent.ACTION_UP, Action.BT, 0);
                return false;
        }
        return false;
    }

    /**
     * Set chat send listener
     * @param listener
     */
    public void setSwipeListener(SwipeListener listener) {
        swipeListener = listener;
    }

    public interface SwipeListener {
        void onSwipe(int event, Action action, float x);
    }
}
