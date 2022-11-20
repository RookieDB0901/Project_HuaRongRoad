package com.db.ui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Objects;
import java.util.Random;

public class GameJFrame extends JFrame implements KeyListener, ActionListener {
    //游戏主界面
    int[][] data = new int[4][4];
    //存储空缺块当前坐标
    int x=0,y=0;
    //用来统计步数
    int cnt = 0;
    //存储完成状态
    int[][] comp = {
            {1,5,9,13},
            {2,6,10,14},
            {3,7,11,15},
            {4,8,12,16}
    };
    //创建选项下面的条目
    JMenuItem ReplayItem = new JMenuItem("重新开始");
    JMenuItem ReloginItem = new JMenuItem("重新登录");
    JMenuItem CloseItem = new JMenuItem("关闭游戏");
    public GameJFrame() {
        //初始化界面
        initJFrame();
        //初始化菜单
        initJMenu();
        //打乱图片
        initdata();
        //添加图片
        initImage();
        //设置窗口可见
        this.setVisible(true);
    }

    private void initImage() {
        //清空已出现的所有图片
        this.getContentPane().removeAll();
        //判断是否已经复原
        if (vic()){
            JLabel congratulations = new JLabel(new ImageIcon("module1\\image\\win.png"));
            congratulations.setBounds(203,283,197,73);
            this.getContentPane().add(congratulations);
        }
        //显示步数
        JLabel steps = new JLabel("步数"+cnt);
        steps.setBounds(50,30,100,20);
        this.getContentPane().add(steps);
        //导入拼图
        for (int i=0;i<16;i++)
        {
            int num = data[i%4][i/4];
            if (num!=16) {
                JLabel jLabel1 = new JLabel(new ImageIcon("module1\\image\\animal\\animal1\\" + num + ".jpg"));
                //指定图片位置
                int x_label = i % 4, y_label = i / 4;
                jLabel1.setBounds(105 * x_label + 83, 105 * y_label + 134, 105, 105);
                //描边
                jLabel1.setBorder(new BevelBorder(1));
                //将图片添加到窗口
                this.getContentPane().add(jLabel1);
            }
        }
        //导入背景
        JLabel background = new JLabel(new ImageIcon("module1\\image\\background.png"));
        background.setBounds(40,40,508,560);
        this.getContentPane().add(background);
        //刷新界面
        this.getContentPane().repaint();
    }

    private void initdata() {
        int[] tempArray = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16};
        Random r = new Random();
        /*
        //随机调整图片顺序
        for (int i = 0; i < tempArray.length; i++) {
            int index = r.nextInt(tempArray.length);
            int temp = tempArray[i];
            tempArray[i] = tempArray[index];
            tempArray[index]= temp;
        }
        */
        //遍历数组存储成二维数组方便后续操作
        for (int i = 0; i < tempArray.length; i++) {
            if (tempArray[i]==16)
            {
                x = i%4;
                y = i/4;
            }
            data[i % 4][i / 4] = tempArray[i];
        }
        for (int i=0;i<1000;i++){
            int index = r.nextInt(4);
            init_1(index);
        }
    }

    private void initJMenu() {
        //初始化菜单
        JMenuBar jMenuBar = new JMenuBar();
        //创建“功能”和“关于”选项
        JMenu FunctionJMenu = new JMenu("功能");
        JMenu AboutJMenu = new JMenu("关于");
        //将条目加入到选项中
        FunctionJMenu.add(ReplayItem);
        FunctionJMenu.add(ReloginItem);
        FunctionJMenu.add(CloseItem);
        ReplayItem.addActionListener(this);
        ReloginItem.addActionListener(this);
        CloseItem.addActionListener(this);
        //将选项添加到菜单中
        jMenuBar.add(FunctionJMenu);
        jMenuBar.add(AboutJMenu);
        //将菜单添加到主界面中
        this.setJMenuBar(jMenuBar);

    }

    private void initJFrame() {
        //设置窗口大小
        this.setSize(600,750);
        //标题
        this.setTitle("DB的华容道");
        //置顶
        this.setAlwaysOnTop(true);
        //居中
        this.setLocationRelativeTo(null);
        //关闭
        this.setDefaultCloseOperation(3);
        //取消默认布局
        setLayout(null);
        this.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        //左37，上38，右39，下40
        if (vic()){
            return;
        }
        int code = e.getKeyCode();
        if (code == 65&&x<3) {
            data[x][y] = data[x+1][y];
            data[x+1][y] = 16;
            x++;
            cnt++;
            initImage();
        }else if (code == 87&&y<3) {
            System.out.println("上");
            data[x][y] = data[x][y+1];
            data[x][y+1] = 16;
            y++;
            cnt++;
            initImage();
        }else if (code == 68&&x>0) {
            data[x][y] = data[x-1][y];
            data[x-1][y] = 16;
            x--;
            cnt++;
            initImage();
        }else if (code == 83&&y>0) {
            data[x][y] = data[x][y-1];
            data[x][y-1] = 16;
            y--;
            cnt++;
            initImage();
        }else if (code == 73){
            recover(data);
            System.out.println(data[3][3]);
            initImage();
        }
    }
    public boolean vic(){
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (data[i][j]!=comp[i][j]){
                    return false;
                }
            }
        }
        return true;
    }
    public void init_1(int index){
        int code = index+37;
        if (code == 37&&x<3) {
            data[x][y] = data[x+1][y];
            data[x+1][y] = 16;
            x++;
            initImage();
        }else if (code == 38&&y<3) {
            data[x][y] = data[x][y+1];
            data[x][y+1] = 16;
            y++;
            initImage();
        }else if (code == 39&&x>0) {
            data[x][y] = data[x-1][y];
            data[x-1][y] = 16;
            x--;
            initImage();
        }else if (code == 40&&y>0) {
            data[x][y] = data[x][y-1];
            data[x][y-1] = 16;
            y--;
            initImage();
        }
    }
    public void recover(int[][] data){
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data.length; j++) {
                data[i][j]=j*4+i+1;
            }
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if (obj== ReplayItem){
            cnt = 0;
            initdata();
            initImage();
        }else if (obj==ReloginItem){
            this.setVisible(false);
            new LoginJFrame();
        }else if (obj==CloseItem){
            System.exit(0);
        }
    }
}
