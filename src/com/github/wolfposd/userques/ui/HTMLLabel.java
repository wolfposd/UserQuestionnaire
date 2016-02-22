package com.github.wolfposd.userques.ui;

import javax.swing.JLabel;

/**
 * HTML-Label
 * 
 * @author w.posdorfer
 */
public final class HTMLLabel extends JLabel
{
    private static final long serialVersionUID = -4155206501971316452L;
    private String _unftext;
    private boolean _center;

    public HTMLLabel(String text, boolean center)
    {
        _center = center;
        this.setText(text);
    }

    public String getUnformattedText()
    {
        return _unftext;
    }

    public void setText(String text)
    {
        this._unftext = text;

        if (_center)
        {
            super.setText("<html><center>" + text + "</center></html>");
        }
        else
        {
            super.setText("<html>" + text + "</html>");
        }

    }
}