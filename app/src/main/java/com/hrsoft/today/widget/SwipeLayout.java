package com.hrsoft.today.widget;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

/**
 * @author YangCihang.
 * @since 17/11/20 20:38.
 * email yangcihang@hrsoft.net.
 */

public class SwipeLayout extends LinearLayout {
    private ViewDragHelper viewDragHelper;
    private View contentView;
    private View actionView;

    private int dragDistance;
    private final double AUTO_OPEN_SPEED_LIMIT = 400.0;
    private int draggedX;

    /**
     * 滑动监听
     */
    private onSlideListener onSlide;
    //按下的x
    private float downX;

    private float downY;

    public SwipeLayout(Context context) {
        this(context, null);
    }

    public SwipeLayout(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public SwipeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        // 创建一个带有回调接口的ViewDragHelper
        viewDragHelper = ViewDragHelper.create(this, new DragHelperCallback());
    }

    // 当View中所有的子控件 均被映射成xml后触发
    @Override
    protected void onFinishInflate() {
        // 拿到第一个内容显示视图
        super.onFinishInflate();
        contentView = getChildAt(0);
        // 拿到第二个内容显示视图（即删除视图）
        actionView = getChildAt(1);
        // 默认不显示
        actionView.setVisibility(GONE);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        dragDistance = actionView.getMeasuredWidth();
    }

    /**
     * 还原
     */
    public void revert() {
        if (viewDragHelper != null) {
            viewDragHelper.smoothSlideViewTo(contentView, 0, 0);
            invalidate();
        }
    }

    /**
     * 手势处理的监听实现
     */
    private class DragHelperCallback extends ViewDragHelper.Callback {

        // tryCaptureView如何返回ture则表示可以捕获该view，你可以根据传入的第一个view参数决定哪些可以捕获
        @Override
        public boolean tryCaptureView(View view, int i) {
            return view == contentView || view == actionView;
        }

        // 当captureview的位置发生改变时回调
        @Override
        public void onViewPositionChanged(View changedView, int left, int top,
                                          int dx, int dy) {
            //左边移动了多少
            draggedX = left;
            // 拦截父视图事件，不让父试图事件影响
            getParent().requestDisallowInterceptTouchEvent(true);
            if (changedView == contentView) {
                actionView.offsetLeftAndRight(dx);
            } else {
                contentView.offsetLeftAndRight(dx);
            }
            if (actionView.getVisibility() == View.GONE) {
                actionView.setVisibility(View.VISIBLE);
            }
            //刷新视图
            invalidate();
        }

        /**
         * clampViewPositionHorizontal,
         * clampViewPositionVertical可以在该方法中对child移动的边界进行控制， left , top
         * 分别为即将移动到的位置，比如横向的情况下，我希望只在ViewGroup的内部移动，即：最小>=paddingleft，
         * 最大<=ViewGroup.getWidth()-paddingright-child.getWidth。就可以按照如下代码编写：
         */
        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            if (child == contentView) {
                final int leftBound = getPaddingLeft();
                final int minLeftBound = -leftBound - dragDistance;
                final int newLeft = Math.min(Math.max(minLeftBound, left), 0);
                return newLeft;
            } else {
                final int minLeftBound = getPaddingLeft()
                        + contentView.getMeasuredWidth() - dragDistance;
                final int maxLeftBound = getPaddingLeft()
                        + contentView.getMeasuredWidth() + getPaddingRight();
                final int newLeft = Math.min(Math.max(left, minLeftBound),
                        maxLeftBound);
                return newLeft;
            }
        }

        /**
         * 原因是什么呢？主要是因为，如果子View不消耗事件，那么整个手势（DOWN-MOVE*-UP）
         * 都是直接进入onTouchEvent，在onTouchEvent的DOWN的时候就确定了captureView。
         * 如果消耗事件，那么就会先走onInterceptTouchEvent方法，判断是否可以捕获， 而在判断的过程中会去判断另外两个回调的方法：
         * getViewHorizontalDragRange和getViewVerticalDragRange，
         * 只有这两个方法返回大于0的值才能正常的捕获。所以， 如果你用Button测试，或者给TextView添加了clickable = true
         * ，都要记得重写下面这两个方法：
         */
        @Override
        public int getViewHorizontalDragRange(View child) {
            return dragDistance;
        }

        // 手指释放的时候回调
        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            super.onViewReleased(releasedChild, xvel, yvel);
            boolean settleToOpen = false;
            if (xvel > AUTO_OPEN_SPEED_LIMIT) {
                settleToOpen = false;
            } else if (xvel < -AUTO_OPEN_SPEED_LIMIT) {
                settleToOpen = true;
            } else if (draggedX <= -dragDistance / 2) {
                settleToOpen = true;
            } else if (draggedX > -dragDistance / 2) {
                settleToOpen = false;
            }

            final int settleDestX = settleToOpen ? -dragDistance : 0;
            if (onSlide != null) {
                if (settleDestX == 0) {
                    onSlide.onSlided(false);
                } else {
                    onSlide.onSlided(true);
                }
            }
            viewDragHelper.smoothSlideViewTo(contentView, settleDestX, 0);
            ViewCompat.postInvalidateOnAnimation(SwipeLayout.this);
        }
    }

    public void setOnSlide(onSlideListener onSlide) {
        this.onSlide = onSlide;
    }

    /**
     * 由于整个视图都用了ViewDragHelper手势处理，
     * 所以导致不滑动的视图点击事件不可用，所以需要自己处理点击事件
     */
    public interface onSlideListener {
        /**
         * 侧滑完了之后调用 true已经侧滑，false还未侧滑
         */
        void onSlided(boolean isSlide);

        /**
         * 未侧滑状态下的默认显示整体的点击事件
         */
        void onClick();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        // 刚开始开启父视图事件.让onTouchEvent监听是移动还是点击
        getParent().requestDisallowInterceptTouchEvent(false);
        return viewDragHelper.shouldInterceptTouchEvent(event) || super.onInterceptTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //记录按下的坐标
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            downX = event.getRawX();
            downY = event.getRawY();
        }
        if (event.getAction() == MotionEvent.ACTION_UP) {
            //x，y移动的距离小于10就出发点击事件
            if (Math.abs(downX - event.getRawX()) < 10
                    && Math.abs(downY - event.getRawY()) < 10) {
                if (onSlide != null) {
                    onSlide.onClick();
                }
            }
        }
//      处理拦截到的事件，这个方法会在返回前分发事件
        viewDragHelper.processTouchEvent(event);
//      表示消费了事件，不会再往下传递
        return true;
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (viewDragHelper.continueSettling(true)) {
            /**
             * 导致失效发生在接下来的动画时间步,通常下显示帧。 这个方法可以从外部的调用UI线程只有当这种观点是附加到一个窗口。
             */
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }
}