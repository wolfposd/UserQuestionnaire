package com.github.wolfposd.userques;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
            Setup s = new Gson().fromJson(new FileReader("setup.json"), Setup.class);
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
            FileReader reader = new FileReader(new File("woerter.txt"));
            BufferedReader bf = new BufferedReader(reader);
            String line = null;
            while ((line = bf.readLine()) != null)
            {
                words.add(line);
            }

            bf.close();
            reader.close();
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
