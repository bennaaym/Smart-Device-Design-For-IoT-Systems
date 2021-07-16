package cc.ooad.project.helper;

import java.util.Scanner;

public  class Tools
{
    public static void delay(int time)
    {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void flashScreen()
    {
        System.out.flush();
    }

    public static  void PressKey(String msg)
    {
        System.out.print(msg);
        (new Scanner(System.in)).nextLine();
    }
}
