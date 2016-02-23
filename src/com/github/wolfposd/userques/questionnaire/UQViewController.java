package com.github.wolfposd.userques.questionnaire;

import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.SwingUtilities;

import com.github.wolfposd.userques.UQFileHandler;
import com.github.wolfposd.userques.items.Setup;
import com.github.wolfposd.userques.questionnaire.UQView.ButtonActionListener;

public class UQViewController implements ButtonActionListener
{
    private final Setup _setup;

    private final List<String> _words;

    private final UQView _view;

    private long _timeSinceLastClick;

    private Timer _timer;

    public UQViewController(Setup setup, List<String> words)
    {
        _setup = setup;
        _words = words;
        _view = new UQView(_setup);
        _view.setButtonActionListener(this);
        _view.setVisible(true);
        _timeSinceLastClick = System.currentTimeMillis();
        _view.setCenterString(_words.get(0));
        startTimer();
    }

    @Override
    public void buttonClicked(int buttonindex, ActionEvent action)
    {
        System.out.println("timer cancelled");
        _timer.cancel();

        long timediff = writeCurrentWordAndTimeToFile(buttonindex);

        System.out.println("Waiting for: " + (_setup.timeforvote - timediff));
        displayEmptyStringForTimeAndPresentNext(_setup.timeforvote - timediff);
    }

    private void startTimer()
    {
        System.out.println("Starting Timer");
        _timer = new Timer();
        _timer.schedule(new TimerTask()
        {
            public void run()
            {
                timeRanOutForQuestion();
            }
        }, _setup.timeforvote);
    }

    private void timeRanOutForQuestion()
    {
        System.out.println("TIME RAN OUT");
        writeCurrentWordAndTimeToFile(-1);

        displayEmptyStringForTimeAndPresentNext(_setup.additionalTimeForNotVoting);
    }

    private void displayEmptyStringForTimeAndPresentNext(final long time)
    {
        _view.performOnAllButtons(button -> button.setEnabled(false));
        _view.setCenterString("");

        new Thread(() -> {
            // dont block UI-Thread
                sleep(time);
                try
                {
                    SwingUtilities.invokeAndWait(() -> {
                        // Back on UI-Thread
                            setupForNextWord();
                            _view.performOnAllButtons(button -> button.setEnabled(true));
                        });
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }).start();

    }

    private long writeCurrentWordAndTimeToFile(int buttonindex)
    {
        long timeDiff = System.currentTimeMillis() - _timeSinceLastClick;
        String buttonText = buttonindex < 0 ? "NOSELECTION" : _setup.buttons.get(buttonindex);
        UQFileHandler.appendString(_words.get(0) + ";" + buttonText + ";" + timeDiff);
        return timeDiff;
    }

    private void setupForNextWord()
    {
        System.out.println("setting up next word");
        _words.remove(0);
        if (_words.size() > 0)
        {
            _view.setCenterString(_words.get(0));
            _timeSinceLastClick = System.currentTimeMillis();

            startTimer();
        }
        else
        {
            // finished with all words
            setUpFinishScreen();
        }
    }

    private void setUpFinishScreen()
    {
        _view.performOnAllButtons(button -> button.setVisible(false));
        _view.setCenterString(_setup.finishtext);
        JButton closeButton = new JButton("Close");
        _view.getButtonPanel().add(closeButton);
        closeButton.addActionListener(e -> closeButtonAction());
    }

    private void closeButtonAction()
    {
        System.exit(0);
    }

    private void sleep(long ms)
    {
        try
        {
            System.out.println("SLEEPING " + ms);
            Thread.sleep(ms);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
