package com.db.ui;

import javax.swing.*;

public class LoginJFrame extends JFrame {
    public LoginJFrame(){
        //设置窗口大小
        this.setSize(500,400);
        //设置窗口可见
        this.setVisible(true);
        //标题
        this.setTitle("登录");
        //置顶
        this.setAlwaysOnTop(true);
        //居中
        this.setLocationRelativeTo(null);
        //关闭
        this.setDefaultCloseOperation(3);
    }
}
