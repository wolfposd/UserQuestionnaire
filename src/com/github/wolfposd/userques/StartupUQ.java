package com.github.wolfposd.userques;

import javax.swing.UIManager;

import com.github.wolfposd.userques.greeting.GreetingViewController;

public class StartupUQ
{

    public static void main(String[] args)
    {
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        new GreetingViewController();

        // System.out.println(new Gson().toJson(Setup.defaultSetup()));

    }

}
