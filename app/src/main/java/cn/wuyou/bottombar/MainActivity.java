package cn.wuyou.bottombar;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;

import java.util.ArrayList;

import cn.wuyou.bottombar.view.NavItem;
import cn.wuyou.bottombar.view.NavViewBottomContainer;

public class MainActivity extends AppCompatActivity {

    private LottieAnimationView mTestLottie;

    private NavViewBottomContainer mNavBottomContainer;

    private ArrayList<NavItem> mNavItem = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        addItem();

        //设置Lottie动画资源文件
        mTestLottie.setAnimation(R.raw.lottielogo);
        //设置无限重复
        //mTestLottie.loop(true);
        mTestLottie.setRepeatCount(ValueAnimator.INFINITE);
        //运行动画
        mTestLottie.playAnimation();

    }


    /**
     * 初始化控件
     */
    private void initView() {
        mTestLottie = findViewById(R.id.test_lottie);
        mNavBottomContainer = findViewById(R.id.nav_container);
        mNavBottomContainer.setOnItemClickListener(new NavViewBottomContainer.OnItemClickListener() {
            @Override
            public void onItemSelect(int position) {
                Toast.makeText(getApplication(), "当前Item: " + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 添加Item
     */
    private void addItem() {
        mNavItem.add(new NavItem(NavItem.NAV_HOME,
                R.string.nav_item_home, R.raw.home_pressed));
        mNavItem.add(new NavItem(NavItem.NAV_CATEGORY,
                R.string.nav_item_category, R.raw.groups_pressed));
        mNavItem.add(new NavItem(NavItem.NAV_MINE,
                R.string.nav_item_mine, R.raw.mine));
        mNavBottomContainer.addItem(mNavItem);
    }

}
