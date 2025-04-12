package cn.lian.utils;

import java.awt.*;

/**
 * @author 张英琪
 */
public class Const {
    public static final int FRAME_WIDTH =  900;
    public static final int FRAME_HEIGHT = 1000;
    public static  final int SIZE = 100;

    public static final int FIRST_YEAR = 1980;
    public static final int LAST_YEAR = 2080;
    public static final int FIRST_MONTH = 1;
    public static final int LAST_MONTH = 12;
    public static final String [] WEEKS = {"日","一","二","三","四","五","六"} ;


    public static final int SCREEN_W = Toolkit.getDefaultToolkit().getScreenSize().width;
    public static final int SCREEN_H = Toolkit.getDefaultToolkit().getScreenSize().height;
    public static final int FRAME_X  = (SCREEN_W-FRAME_WIDTH)/2;//多了一个窗体自身的的长和宽
    public static final int FRAME_Y  = (SCREEN_H-FRAME_HEIGHT)/2;
}
