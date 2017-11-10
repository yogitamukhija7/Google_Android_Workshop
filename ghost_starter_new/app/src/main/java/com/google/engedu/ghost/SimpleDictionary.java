package com.google.engedu.ghost;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class SimpleDictionary implements GhostDictionary {
    private static final String TAG = "simple";
    private ArrayList<String> words;

    public SimpleDictionary(InputStream wordListStream) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(wordListStream));
        words = new ArrayList<>();
        String line = null;
        while((line = in.readLine()) != null) {
            String word = line.trim();
            if (word.length() >= MIN_WORD_LENGTH)
              words.add(line.trim());
        }


    }

    @Override
    public boolean isWord(String word) {


        return words.contains(word);
    }

    @Override
    public String getAnyWordStartingWith(String prefix)
    {String longerword = "";
        int flag=0;
        for(int i=0;i<words.size();i++)
    {
        String word=words.get(i);
        if(word.startsWith(prefix) == true && prefix != "")
        {
            longerword=word;
            flag=1;
        }
        if(prefix == "")
            longerword = "r";

    }
        Log.e("Tag",longerword);

            return longerword;

    }

    @Override
    public String getGoodWordStartingWith(String prefix) {
        return null;
    }
}
