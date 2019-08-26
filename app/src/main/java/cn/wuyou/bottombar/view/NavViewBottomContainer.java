package cn.wuyou.bottombar.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class NavViewBottomContainer extends LinearLayout implements View.OnClickListener {

    private List<NavItem> itemList = new ArrayList<>();

    private OnItemClickListener onItemClickListener;


    public NavViewBottomContainer(Context context) {
        super(context);
    }

    public NavViewBottomContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NavViewBottomContainer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    /**
     * 设置当前选中位置
     *
     * @param position 当前选中的item位置索引
     */
    public void setSelectedPosition(int position) {
        if (!itemList.isEmpty() && itemList.size() >= getChildCount()) {
            for (int i = 0; i < getChildCount(); i++) {
                if ((getChildAt(i) instanceof NavItemLayout) && !itemList.isEmpty()) {
                    ((NavItemLayout) getChildAt(i)).setSelectAnimator(false, itemList.get(i));
                }
            }
            if ((getChildAt(position) instanceof NavItemLayout) && !itemList.isEmpty()) {
                ((NavItemLayout) getChildAt(position)).setSelectAnimator(true, itemList.get(position));
            }
        }
    }

    /**
     * 添加Item
     * @param itemList
     */
    public void addItem(ArrayList<NavItem> itemList) {
        removeAllViews();
        this.itemList = itemList;
        for (int i = 0; i < itemList.size(); i++) {
            NavItemLayout itemLayout = new NavItemLayout(getContext());
            itemLayout.setSelected(itemList.get(i).getIconResId(),
                    itemList.get(i).getTitleResId());
            LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1, 1.0F);
            itemLayout.setTag(i);
            itemLayout.setOnClickListener(this);
            addView(itemLayout, layoutParams);
        }
    }

    /**
     * 设置Item点击事件
     * @param listener
     */
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }


    @Override
    public void onClick(View view) {
        int position = (int) view.getTag();
        setSelectedPosition(position);
        if (onItemClickListener != null) {
            onItemClickListener.onItemSelect(position);
        }
    }

    public interface OnItemClickListener {
        void onItemSelect(int position);
    }

}
