package com.github.wolfposd.userques.questionnaire;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.github.wolfposd.userques.items.Setup;
import com.github.wolfposd.userques.ui.HTMLLabel;

public class UQView
{
    private JFrame _frame;
    private HTMLLabel _label;

    private List<JButton> _buttons = new ArrayList<JButton>();

    private ButtonActionListener _buttonActionListener;
    private JPanel _buttonPanel;

    public UQView(final Setup setup)
    {
        _frame = new JFrame(setup.windowtitle);
        _frame.setLayout(new BorderLayout());

        JPanel p1 = new JPanel(new GridBagLayout());
        
        _label = new HTMLLabel(setup.greetingtext, true);
        _label.setFont(_label.getFont().deriveFont(20.0f));
        p1.add(_label);

        _buttonPanel = new JPanel();

        for (int i = 0; i < setup.buttons.size(); i++)
        {
            final int index = i;
            String btText = setup.buttons.get(i);
            JButton button = new JButton(btText);
            _buttonPanel.add(button);
            _buttons.add(button);
            button.addActionListener(e -> buttonClicked(index, e));
        }

        _frame.add(p1, BorderLayout.CENTER);
        _frame.add(_buttonPanel, BorderLayout.SOUTH);

        _frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        _frame.setSize(setup.windowsize);
        _frame.setLocationRelativeTo(null);

    }

    public void setCenterString(String text)
    {
        _label.setText(text);
    }

    public void setVisible(boolean visible)
    {
        _frame.setVisible(visible);
    }

    public void setButtonActionListener(ButtonActionListener bal)
    {
        _buttonActionListener = bal;
    }

    public void performOnAllButtons(ButtonEach beach)
    {
        for (JButton button : _buttons)
        {
            beach.onButton(button);
        }
    }

    public void buttonClicked(int index, ActionEvent actionEvent)
    {
        if (_buttonActionListener != null)
        {
            _buttonActionListener.buttonClicked(index, actionEvent);
        }

    }

    public JPanel getButtonPanel()
    {
        return _buttonPanel;
    }

    public interface ButtonActionListener
    {
        public void buttonClicked(int index, ActionEvent action);
    }

    public interface ButtonEach
    {
        public void onButton(JButton button);
    }

}
