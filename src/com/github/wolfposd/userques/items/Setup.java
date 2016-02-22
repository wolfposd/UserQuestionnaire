package com.github.wolfposd.userques.items;

import java.awt.Dimension;
import java.util.Arrays;
import java.util.List;

public class Setup
{

    public String windowtitle;

    public String finishtext;

    public String greetingtext;

    public List<String> buttons;

    public float timeforvote;

    public float additionalTimeForNotVoting;

    public Dimension windowsize;

    private Setup()
    {

    }

    public static Setup defaultSetup()
    {
        Setup s = new Setup();
        s.windowtitle = "User Questionnaire";
        s.finishtext = "Thank You";
        s.greetingtext = "Welcome";
        s.buttons = Arrays.asList(new String[] { "Yes", "No", "Maybe" });
        s.timeforvote = 1000 * 20;
        s.windowsize = new Dimension(400, 200);
        s.additionalTimeForNotVoting = 1000 * 2;
        return s;
    }

}
