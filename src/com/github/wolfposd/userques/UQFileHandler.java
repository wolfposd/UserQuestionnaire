package com.github.wolfposd.userques;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.github.wolfposd.userques.items.Setup;
import com.google.gson.Gson;

public class UQFileHandler
{

    private static FileWriter _filewriter;

    static
    {
        try
        {
            _filewriter = new FileWriter("output.txt", true);
            File f = new File("output.txt");
            if (!f.exists())
            {
                f.createNewFile();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static Setup readSetup()
    {
        try
        {
            Setup s = new Gson().fromJson(new InputStreamReader(new FileInputStream("setup.json"), "UTF-8"), Setup.class);
            return s;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public static List<String> getWordList()
    {
        List<String> words = new ArrayList<String>();
        try
        {
            BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream("woerter.txt"), "UTF-8"));
            String line = null;
            while ((line = bf.readLine()) != null)
            {
                words.add(line);
            }

            bf.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
        return words;
    }

    public static void appendString(String toAppend)
    {
        try
        {
            _filewriter.write(toAppend + "\r\n");
            _filewriter.flush();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    protected void finalize() throws Throwable
    {
        super.finalize();
        _filewriter.close();
    }
}
