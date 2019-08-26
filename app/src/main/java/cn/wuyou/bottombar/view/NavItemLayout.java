package cn.wuyou.bottombar.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.airbnb.lottie.LottieAnimationView;
import cn.wuyou.bottombar.R;

public class NavItemLayout extends FrameLayout {

    //标题
    private TextView mTitle;

    //Lottie动画图标
    private LottieAnimationView mLottieView;

    private LottieAnimatorUpdateListener mAnimatorUpdateListener;


    public NavItemLayout(Context context) {
        super(context);
        initView();
    }

    public NavItemLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public NavItemLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    public void setSelectAnimator(boolean isSelected, NavItem navItem) {
        if (isSelected) {
            mLottieView.playAnimation();
            if (mAnimatorUpdateListener == null) {
                mAnimatorUpdateListener = new LottieAnimatorUpdateListener(navItem);
            }
            //设置动画监听事件
            mLottieView.addAnimatorUpdateListener(mAnimatorUpdateListener);
            if (NavItem.NAV_HOME.equals(navItem.getResourceCode())) {
                mLottieView.setSpeed(1.4f);
            }
            return;
        }
        mLottieView.setProgress(0.0f);
        mLottieView.cancelAnimation();
        mTitle.setTextColor(getResources().getColor(R.color.nav_title_color_normal));
    }


    //初始化控件
    protected void initView() {
        inflate(getContext(), R.layout.nav_item_layout, this);
        mTitle = findViewById(R.id.title);
        mLottieView = findViewById(R.id.icon);
    }

    //设置选中状态
    protected void setSelected(int rawResId, int titleResId) {
        mLottieView.setVisibility(View.VISIBLE);
        mLottieView.setAnimation(rawResId);
        mTitle.setText(titleResId);
        mTitle.setVisibility(View.VISIBLE);
    }


    //获取导航栏标题字体颜色资源ID
    private int getTitleColorResId(NavItem navItem) {
        //首页
        if (NavItem.NAV_HOME.equals(navItem.getResourceCode())) {
            return getResources().getColor(R.color.nav_home_title_color);
        }
        //分类
        if (NavItem.NAV_CATEGORY.equals(navItem.getResourceCode())) {
            return getResources().getColor(R.color.nav_category_title_color);
        }
        //我的
        if (NavItem.NAV_MINE.equals(navItem.getResourceCode())) {
            return getResources().getColor(R.color.nav_mine_title_color);
        }
        return getResources().getColor(R.color.nav_title_color);
    }



    class LottieAnimatorUpdateListener implements ValueAnimator.AnimatorUpdateListener {

        private NavItem navItem;

        LottieAnimatorUpdateListener(NavItem navItem) {
            this.navItem = navItem;
        }

        /**
         * 动画进度监听事件
         * @param valueAnimator
         */
        @Override
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            if (valueAnimator.getAnimatedFraction() >= 0.3f) {
                mTitle.setTextColor(getTitleColorResId(navItem));
            }
        }
    }

}
