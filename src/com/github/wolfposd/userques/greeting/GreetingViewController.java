package com.github.wolfposd.userques.greeting;

import java.util.List;

import com.github.wolfposd.userques.UQFileHandler;
import com.github.wolfposd.userques.items.Setup;
import com.github.wolfposd.userques.questionnaire.UQViewController;

public class GreetingViewController
{

    private final GreetingView _view;
    private Setup _setup;
    private List<String> _words;

    public GreetingViewController()
    {
        _view = new GreetingView();

        _view.getGoButton().addActionListener(e -> startButtonClicked());
        _view.getGoButton().setEnabled(false);

        _view.setVisible(true);

        _view.getActivityIndicator().startRotating();

        loadSetupWords();

        //_view.getActivityIndicator().stopRotating();
        _view.removeActivityIndicator();
    }

    private void loadSetupWords()
    {

        _setup = UQFileHandler.readSetup();
        if (_setup == null)
        {
            _view.setCenterText("Fehler beim Laden der Konfiguration!");
        }
        _words = UQFileHandler.getWordList();
        if (_words == null)
        {
            _view.setCenterText("Fehler beim Laden der Wörter!");
        }
        try
        {
            Thread.sleep(1000);
        }
        catch (InterruptedException e)
        {
        }

        if (_words != null && _setup != null)
        {
            _view.getGoButton().setEnabled(true);
            _view.updateFromSetup(_setup);
        }

    }

    private void startButtonClicked()
    {
        new UQViewController(_setup, _words);
        _view.setVisible(false);
    }

}
