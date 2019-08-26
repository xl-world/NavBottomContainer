package cn.wuyou.bottombar.view;

public class NavItem {

    //首页
    public static final String NAV_HOME = "home";

    //分类
    public static final String NAV_CATEGORY = "category";

    //我的
    public static final String NAV_MINE = "mine";

    //标题资源ID
    private int titleResId;

    //图标资源ID
    private int iconResId;

    //当前Resource
    private String resourceCode;

    public NavItem(String resourceCode, int titleResId, int iconResId) {
        this.titleResId = titleResId;
        this.iconResId = iconResId;
        this.resourceCode = resourceCode;
    }

    //获取标题资源ID
    int getTitleResId() {
        return titleResId;
    }

    //获取图标资源ID
    int getIconResId() {
        return iconResId;
    }

    //获取当前资源值
    String getResourceCode() {
        return resourceCode;
    }
}
