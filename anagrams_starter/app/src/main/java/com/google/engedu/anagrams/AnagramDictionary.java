package com.google.engedu.anagrams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

public class AnagramDictionary {

    private static final int MIN_NUM_ANAGRAMS = 5;
    private static final int DEFAULT_WORD_LENGTH = 3;
    private static final int MAX_WORD_LENGTH = 7;
    private Random random = new Random();
    public ArrayList<String> wordList= new ArrayList<String>();
    HashSet wordSet;
    HashMap<String, ArrayList> lettersToWord;
    public int wordLength=DEFAULT_WORD_LENGTH;
    public AnagramDictionary(InputStream wordListStream) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(wordListStream));
        String line;

        wordSet = new HashSet<>();
        lettersToWord = new HashMap<String, ArrayList>();

        while ((line = in.readLine()) != null) {
            String word = line.trim();
            wordList.add(word);
            wordSet.add(word);
            String sorte_word = sortLetters(word);
            if (lettersToWord.containsKey(sorte_word))
            {
                lettersToWord.get(sorte_word).add(word);
            }
            else
            {
                ArrayList<String> arr_List = new ArrayList<>();
                arr_List.add(word);

                lettersToWord.put(sorte_word, arr_List);
            }
        }
    }

    public boolean isGoodWord(String word, String base)
    {
        if(wordSet.contains(word))
        {
            if(!word.contains(base))
            {
                return true;
            }
        }
        return false;
    }

    public ArrayList<String> getAnagrams(String targetWord)
    {
        ArrayList<String> result= new ArrayList<String>();

        for(int i=0; i<wordList.size();i++)
        {
            String word = wordList.get(i);
            if(word.length()==targetWord.length())
            {
                String sorted= sortLetters(word);
                if(sorted.matches(sortLetters(targetWord)))
                {
                    result.add(word);
                }
            }
        }
        return result;
    }

    public ArrayList<String> getAnagramsWithOneMoreLetter(String word) {
        ArrayList<String> result = new ArrayList<String>();
        for(int i=97;i<=122;i++)
        {
            char a= (char) i;
            String result_new= word + a;
            String sorted_result= sortLetters(result_new);
            if(lettersToWord.containsKey(sorted_result))
            {
                for(int j=0;j<(lettersToWord.get(sorted_result)).size();j++)
                {
                    String fetched_word= (String) lettersToWord.get(sorted_result).get(j);
                    if(!word.matches(fetched_word)&&!fetched_word.contains(word))
                    {
                        result.add(fetched_word);
                    }
                }
            }
        }
        return result;
    }

    public String sortLetters(String a)
    {
        char[] chars = a.toCharArray();
        Arrays.sort(chars);
        String sorted = new String(chars);
        return sorted;
    }



    public String pickGoodStarterWord()
    {
        int max=wordList.size()+1;
        int min=0;
        int random_index=min+(int)(Math.random())+((max+min)+1);
        boolean flag=false;
        while (!flag)
        {
            if ((wordList.get(random_index).length()==wordLength) && getAnagramsWithOneMoreLetter(wordList.get(random_index)).size()==MIN_NUM_ANAGRAMS)
            {
                flag = true;
                if (wordLength != MAX_WORD_LENGTH)
                {
                    wordLength=wordLength+1;
                }
                break;
            }
            random_index=(random_index+1)%wordList.size();

        }
        return wordList.get(random_index);

    }


}