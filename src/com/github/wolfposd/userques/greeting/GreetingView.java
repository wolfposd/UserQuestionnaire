package com.github.wolfposd.userques.greeting;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.github.wolfposd.userques.JActivityIndicator;
import com.github.wolfposd.userques.items.Setup;
import com.github.wolfposd.userques.ui.HTMLLabel;

public class GreetingView
{
    private JFrame _frame;
    private HTMLLabel _label;
    private JButton _goButton;
    private JActivityIndicator _activity;

    public GreetingView()
    {
        _frame = new JFrame("");
        _frame.setLayout(new BorderLayout());

        JPanel p1 = new JPanel();

        _activity = new JActivityIndicator(JActivityIndicator.CIRCLE_LIGHT_GREY);
        _label = new HTMLLabel("", true);
        _label.setFont(_label.getFont().deriveFont(20.0f));
        p1.add(_label);
        p1.add(_activity);

        JPanel panel = new JPanel();

        _goButton = new JButton("Starten");
        panel.add(_goButton);

        _frame.add(p1, BorderLayout.CENTER);
        _frame.add(panel, BorderLayout.SOUTH);

        _frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        _frame.setSize(200, 200);
        _frame.setLocationRelativeTo(null);
    }

    public void updateFromSetup(final Setup setup)
    {
        _label.setText(setup.greetingtext);
        _frame.setTitle(setup.windowtitle);
        _frame.setSize(setup.windowsize);
    }

    public JActivityIndicator getActivityIndicator()
    {
        return _activity;
    }

    public void setCenterText(String text)
    {
        _label.setText(text);
    }

    public void setVisible(boolean visible)
    {
        _frame.setVisible(visible);
    }

    public JButton getGoButton()
    {
        return _goButton;
    }

}
