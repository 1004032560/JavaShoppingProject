package com.looper.main;

public class ViewInfo {

    //选择有误
    public void chooseErr(){
        System.out.println("输入有误,当前没有该选项,请重新选择！");
    }

    //退出
    public void signOut(){
        System.out.println("正在退出....");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("退出成功！");
    }

    //返回上一级
    public void backLevel(){

    }

}
