package com.github.wolfposd.userques.greeting;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.github.wolfposd.userques.items.Setup;
import com.github.wolfposd.userques.ui.HTMLLabel;
import com.github.wolfposd.userques.ui.JActivityIndicator;

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

        JPanel p1 = new JPanel(new GridBagLayout());

        _activity = new JActivityIndicator(JActivityIndicator.CIRCLE_LIGHT_GREY);
        _label = new HTMLLabel("", true);
        _label.setFont(_label.getFont().deriveFont(20.0f));
        p1.add(_label);//, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,10,0,10), 0, 0));
        p1.add(_activity);//,new GridBagConstraints(1, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,0,0,0), 0, 0));

        JPanel panel = new JPanel();

        _goButton = new JButton("Starten");
        panel.add(_goButton);

        _frame.add(p1, BorderLayout.CENTER);
        _frame.add(panel, BorderLayout.SOUTH);

        _frame.setSize(200, 200);
        _frame.setLocationRelativeTo(null);
    }

    public void updateFromSetup(final Setup setup)
    {
        _label.setText(setup.greetingtext);
        _frame.setTitle(setup.windowtitle);
        _frame.setSize(setup.windowsize);
        _frame.setLocationRelativeTo(null);
    }
    
    public void removeActivityIndicator()
    {
        JPanel parent = (JPanel)_activity.getParent();
        parent.remove(_activity);
       // parent.revalidate();
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
