package cn.lian.start;

import cn.lian.utils.Const;
import cn.lian.vo.DayWeek;
import cn.lian.vo.YearMonth;
import sun.util.resources.cldr.rm.CalendarData_rm_CH;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Calendar;

import java.io.File;


//1.窗体居中  xy中间 窗体中心的坐标
//2.窗体的大小用常量类Const中的常量
//用常量类来保存参数
//3.setBounds,简洁


/**
 * @author 张英琪
 */
public class MyCalend extends JFrame implements ActionListener {
    //面板容器
    private JPanel chooseJpl =  new JPanel();
    private JPanel weekJpl = new JPanel();
    private JPanel dateJpl = new JPanel();

    //年月的选择框
    private JComboBox cboYear = new JComboBox();
    private JComboBox cboMonth = new JComboBox();

    //年月的标签
    private JLabel labYear = new JLabel("年");
    private JLabel labMonth = new JLabel("月");

    // 创建一个Font对象，指定字体名称、风格和大小
    Font newFont = new Font("宋体", Font.BOLD, 36); // 例如，设置为宋体，加粗，24号字体

    public void init(){
        // 创建背景面板
        BackgroundPanel backgroundPanel = new BackgroundPanel("D:\\图片素材\\动漫人物\\微信图片_20231224212558.jpg");
        backgroundPanel.setBounds(0, 0, this.getWidth(), this.getHeight());
        this.add(backgroundPanel); // 将背景面板添加到JFrame中

        /**
         * 容器如果使用了绝对布局，
         * 那么容器中的组件就需要用setSize（w,h）
         * 来指定大小
         */
        /**容器如果使用了布局管理器，layout
         * 那么容器中的组件就需要用setPreferredSize（w,h）
         * 来指定大小
         */

        // 设置面板为透明
        chooseJpl.setOpaque(false);
        weekJpl.setOpaque(false);
        dateJpl.setOpaque(false);

        // 设置JLabel的字体
        labYear.setFont(newFont);
        labMonth.setFont(newFont);
        cboYear.setFont(newFont);
        cboMonth.setFont(newFont);

        chooseJpl.add(cboYear);
        chooseJpl.add(labYear);
        chooseJpl.add(cboMonth);
        chooseJpl.add(labMonth);


        cboMonth.setPreferredSize(new Dimension(300,50));
        cboYear.setPreferredSize(new Dimension(300,50));



        labMonth.setBounds(220, 10, 300, 100);
        labYear.setBounds(270, 10, 100, 100);



        chooseJpl.setBounds(100,50,7*Const.SIZE,Const.SIZE);
        weekJpl.setBounds(100,150,7*Const.SIZE,Const.SIZE);
        dateJpl.setLocation(100,300);

// 创建一个透明背景的内容面板
        JPanel contentPanel = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // 在这里绘制背景图片
                ImageIcon backgroundImage = new ImageIcon("D:\\图片素材\\动漫人物\\微信图片_20231224212041.jpg");
                g.drawImage(backgroundImage.getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
            }
        };
        contentPanel.setOpaque(false); // 设置内容面板为透明

        // 设置内容面板的大小与窗口大小一致
        contentPanel.setBounds(0, 0, this.getWidth(), this.getHeight());

        // 将内容面板设置为JFrame的内容面板
        this.setContentPane(contentPanel);

        this.add(chooseJpl);
        this.add(weekJpl);
        this.add(dateJpl);

        this.addYear();
        this.addMonth();
        this.addWeeks();
        this.addDays();


//        this.setSize(Const.FRAME_WIDTH,Const.FRAME_HEIGHT);
//        this.setLocation(Const.FRAME_X,Const.FRAME_Y);//需要设置窗体中心的坐标，
//                                                      // 默认在原点（左上角位置）
        this.setTitle("张英琪的日历");
        this.setBounds(Const.FRAME_X,Const.FRAME_Y,Const.FRAME_WIDTH,Const.FRAME_HEIGHT);
        this.setResizable(false);//禁止窗口被拉伸
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//默认的退出窗口，即关闭——》
        // 点击窗口即可退出程序
        this.setLayout(null);
        this.setVisible(true);
    }
    public void addYear(){
        for(int i = Const.FIRST_YEAR; i <= Const.LAST_YEAR; i++){
            this.cboYear.addItem(i);
        }
        int year =  Calendar.getInstance().get(Calendar.YEAR);
        this.cboYear.setSelectedItem(Integer.valueOf(year));
    }
    public void addMonth(){
        for(int i = Const.FIRST_MONTH; i <= Const.LAST_MONTH; i++){
            this.cboMonth.addItem(i);
        }
        int month = Calendar.getInstance().get(Calendar.MONTH);
        this.cboMonth.setSelectedItem(Integer.valueOf(month+1));
        //由于Calendar中的月份是从0开始计数的，而人们习惯于从1开始计数的月份，
        // 所以这里加1是为了将月份转换为人们习惯的计数方式。
    }
    public void addWeeks(){
        this.weekJpl.setLayout(new GridLayout(1,7));
        for(int i = 0;i<Const.WEEKS.length;i++){
            JLabel  label = new JLabel();
            label.setText(Const.WEEKS[i]);
            label.setFont(newFont);
            label.setSize(Const.SIZE,Const.SIZE);
            label.setHorizontalAlignment(SwingConstants.CENTER);
            this.weekJpl.add(label);
        }
    }
    public void addDays(){
        //清空面板
        clearDays();

        dateJpl.setLayout(null);

        //设置size
        dateJpl.setSize(7*Const.SIZE,7*Const.SIZE);

        DayWeek dw = getWeekAndDays();
        for(int i = 0;i < dw.getDay();i++){
            JButton btn = new JButton();
            btn.setText(i+1+"");
            btn.setFont(newFont);
            btn.setBackground(Color.WHITE);
            btn.setSize(Const.SIZE,Const.SIZE);
//            btn.setLocation((i%7)*Const.SIZE,(i/7)*Const.SIZE);
            btn.setLocation(((i+dw.getWeek())%7)*Const.SIZE,((i+dw.getWeek())/7)*Const.SIZE);
            btn.addActionListener(this);
            dateJpl.add(btn);
        }
    }

    public DayWeek getWeekAndDays(){
       YearMonth ym = getSelectYearAndMonth();
        Calendar c =  Calendar.getInstance();
        //自定义日期
        c.set(Calendar.YEAR,ym.getYear());
        c.set(Calendar.MONTH,ym.getMonth());
        c.set(Calendar.DATE,1);

        //每个月1号是星期几，前面就空几个格子
        int week = c.get(Calendar.DAY_OF_WEEK)-1;
        c.roll(Calendar.DATE,-1);
        int days = c.get(Calendar.DATE);

//        System.out.println(week);
//        System.out.println(days);

        DayWeek dayWeek = new DayWeek(week,days);
        return dayWeek;
    }
    public YearMonth  getSelectYearAndMonth(){
        int year = Integer.parseInt(cboYear.getSelectedItem().toString());
        int month = Integer.parseInt(cboMonth.getSelectedItem().toString())-1;//0-数字
        YearMonth yearMonth = new YearMonth(year,month);

//        System.out.println(year);
//        System.out.println(month);
        return  yearMonth;
    }
    public void changeMonthHandler(){
        cboMonth.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                addDays();
            }
        });
    }

    public void changeYearHandler(){
        cboYear.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED){
                    addDays();
                }
            }
        });
    }
    public void clearDays(){
        dateJpl.removeAll();
        dateJpl.repaint();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        YearMonth yearMonth = getSelectYearAndMonth();
        String day = e.getActionCommand().toString();
        String message = yearMonth.getYear() + "年" + (yearMonth.getMonth() + 1) + "月" + day + "日";

        // 创建一个自定义的面板来作为对话框的内容
        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel(message, SwingConstants.CENTER);
        label.setFont(new Font("SansSerif", Font.BOLD, 24)); // 设置字体和字号

        // 将标签添加到面板中
        panel.add(label, BorderLayout.CENTER);

        // 创建一个按钮
        JButton button = new JButton("彩蛋");
        button.addActionListener((ActionEvent event) -> {
            // 这里可以添加按钮点击后的逻辑
            JOptionPane.showMessageDialog(null, "恭喜你获取到了日期");
        });

        // 将按钮添加到面板中
        panel.add(button, BorderLayout.SOUTH);

        // 创建对话框
        JOptionPane optionPane = new JOptionPane(panel);
        JDialog dialog = optionPane.createDialog(null, "日期信息");
        dialog.setVisible(true);
    }

        public  static void playMusic(String filePath) {
            try {
                // 打开音频文件
                File audioFile = new File(filePath);
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);

                // 获取音频格式和数据
                AudioFormat format = audioStream.getFormat();
                DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);

                // 获取并打开数据行
                SourceDataLine audioLine = (SourceDataLine) AudioSystem.getLine(info);
                audioLine.open(format);

                // 播放音频
                audioLine.start();
                int nBytesRead = 0;
                byte[] abData = new byte[10000];

                while (nBytesRead != -1) {
                    nBytesRead = audioStream.read(abData, 0, abData.length);
                    if (nBytesRead >= 0) {
                        audioLine.write(abData, 0, nBytesRead);
                    }
                }

                // 停止播放
                audioLine.drain();
                audioLine.close();
                audioStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    // 自定义的JPanel类，用于显示背景图片
    private class BackgroundPanel extends JPanel {
        private ImageIcon backgroundImage;

        public BackgroundPanel(String imagePath) {
            backgroundImage = new ImageIcon(imagePath);
            setOpaque(false); // 设置面板为透明
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
        }
    }
//    private void setBackgroundImage(String imagePath) {
//        try {
//            // 加载背景图片
//            ImageIcon backgroundImage = new ImageIcon(imagePath);
//            // 设置背景图片
//            this.setLayout(null); // 允许绝对布局
//            JLabel backgroundLabel = new JLabel(backgroundImage);
//            backgroundLabel.setBounds(0, 0, this.getWidth(), this.getHeight()); // 设置背景图片大小与窗口大小一致
//            this.add(backgroundLabel); // 将背景图片添加到窗口中
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }


    public MyCalend() {
//        init();
//        setBackgroundImage("D:\\图片素材\\动漫人物\\微信图片_20231224212558.jpg"); // 替换为你的背景图片路径
//        changeYearHandler();
//        changeMonthHandler();
//         在初始化方法的最后调用setBackgroundImage来设置背景图片
//        setBackgroundImage("D:\\图片素材\\动漫人物\\微信图片_20231224212558.jpg"); // 替换为你的背景图片路径

    }



    public static void main(String[] args) {
        MyCalend myCalend = new MyCalend();
        myCalend.init();
        myCalend.changeYearHandler();
        myCalend.changeMonthHandler();
        // 播放音乐文件，确保文件路径正确
        playMusic("D:\\KuGou\\music\\谁不是-王子枫.128.wav");

//        DayWeek dayWeek = myCalend.getWeekAndDays();
//        YearMonth yearMonth = myCalend.getSelectYearAndMonth();

    }


}
