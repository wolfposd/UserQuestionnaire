package com.github.wolfposd.userques.questionnaire;

import java.awt.event.ActionEvent;
import java.util.List;

import com.github.wolfposd.userques.UQFileHandler;
import com.github.wolfposd.userques.items.Setup;
import com.github.wolfposd.userques.questionnaire.UQView.ButtonActionListener;

public class UQViewController implements ButtonActionListener
{
    private final Setup _setup;

    private final List<String> _words;

    private final UQView _view;

    private long _timeSinceLastClick;

    public UQViewController(Setup setup, List<String> words)
    {
        _setup = setup;
        _words = words;
        _view = new UQView(_setup);
        _view.setButtonActionListener(this);
        _view.setVisible(true);

        _timeSinceLastClick = System.currentTimeMillis();
        _view.setCenterString(_words.get(0));
    }

    @Override
    public void buttonClicked(int index, ActionEvent action)
    {
        long timeDiff = System.currentTimeMillis() - _timeSinceLastClick;

        UQFileHandler.appendString(_words.get(0) + ";" + _setup.buttons.get(index) + ";" + timeDiff);

        setupForNextWord();
    }

    private void timeRanOutForQuestion()
    {
        _view.performOnAllButtons(button -> button.setEnabled(false));
        _view.setCenterString("");

        // SLEEEEEEEEEP

        setupForNextWord();
        _view.performOnAllButtons(button -> button.setEnabled(true));
    }

    private void setupForNextWord()
    {
        _words.remove(0);
        if (_words.size() > 0)
        {
            _view.setCenterString(_words.get(0));
            _timeSinceLastClick = System.currentTimeMillis();

            // re-start timer
        }
        else
        {
            // finished
        }
    }

}
