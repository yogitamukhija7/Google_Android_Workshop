package com.google.engedu.ghost;

import android.util.Log;

import java.util.HashMap;


public class TrieNode {
    private HashMap<Character, TrieNode> children;
    private boolean isWord;

    public HashMap<Character,TrieNode> getChildren(){return children;}

    public void  setIsEnd(boolean val){isWord=val;}
    public TrieNode() {
        children = new HashMap<>();
        isWord = false;

    }
    TrieNode root=new TrieNode();

    public boolean getIsWord()
    {
        return this.isWord;
    }

    public void add(String s) {
        int length=s.length();
        TrieNode temp=this;

        for(int i=0;i<length;i++)
        {
            HashMap<Character,TrieNode> child =temp.getChildren();
            char ch=s.charAt(i);

            if(child.containsKey(ch))
            {
                temp=child.get(ch);
            }
            else
            {
                TrieNode new_node=new TrieNode();


            }
        }
        temp.setIsEnd(false);
    }


    public boolean isWord(String s) {
        TrieNode crawl = this;
        int length=s.length();

        for(int i=0;i<length;i++)
        {
            char ch=s.charAt(i);

            HashMap<Character,TrieNode> child= crawl.getChildren();

            if(child.containsKey(ch))
            {
                crawl=child.get(ch);
            }
            else
            {
                return false;
            }
        }
      return false;
    }

    public String getAnyWordStartingWith(String s) {

        TrieNode crawl=this;
        int length=s.length();
        String word="";

        if(length==0)
        {
            String starting_word="";
            int min_char_index=97;
            int max_char_index=122;

            int random_char_index=min_char_index+(int)(Math.random()*(max_char_index-min_char_index)+1);
                    for(int i=random_char_index;i<=122;i++)
                    {
                        char ch=(char)i;
                        HashMap<Character,TrieNode> child=crawl.getChildren();
                        if(child.containsKey(ch))
                        {
                            starting_word+=ch;
                            crawl=child.get(ch);
                            i=96;
                        }
                    }

        }
        else
        {
            for (int i = 0; i < length; i++)
            {
                char ch = s.charAt(i);
                HashMap<Character, TrieNode> child = crawl.getChildren();
                if (child.containsKey(ch)) {
                    word+=ch;
                    crawl=child.get(ch);

                }
                else {
                    return null;
                }

            }
        for(int i=97;i<=122;i++)
        {
            char ch=(char)i;
            HashMap<Character,TrieNode> child=crawl.getChildren();
            if(child.containsKey(ch))
            {
                word+=ch;
                crawl=child.get(ch);
            }
        }

        }
        return null;
    }

    public String getGoodWordStartingWith(String s) {
        TrieNode crawl=this;
        int length=s.length();
        String word="";
        String complete_word="";

        if(length==0)
        {
            String starting_word="";
            int min_char_index=97;
            int max_char_index=122;

            int random_char_index=min_char_index+(int)(Math.random()*(max_char_index-min_char_index)+1);
            for(int i=random_char_index;i<=122;i++)
            {
                char ch=(char)i;
                HashMap<Character,TrieNode> child=crawl.getChildren();
                if(child.containsKey(ch))
                {
                    starting_word+=ch;
                    crawl=child.get(ch);
                    i=96;
                }
            }
            return starting_word;
        }
        else {
            for (int i = 0; i < length; i++) {
                char ch = s.charAt(i);
                HashMap<Character, TrieNode> child = crawl.getChildren();
                if (child.containsKey(ch)) {
                    word += ch;
                    crawl = child.get(ch);

                } else {
                    return null;
                }

            }
        }
        TrieNode orig_crawl=crawl;
        for (int i=97;i<=122;i++)
        {
            char ch=(char)i;
            HashMap<Character,TrieNode> child=crawl.getChildren();
            if(child.containsKey(ch))
            {
                Log.e("Yogita","The word till now is "+ch);
                word+=ch;
                crawl=child.get(ch);
                if(crawl.getIsWord())  //If it is a word
                {
                    Log.e("Yogita","coming here");
                    complete_word=word;
                    word=s;
                    crawl=orig_crawl;
                    continue;
                }
            }
            else
            {
                return word;
            }
        }
            return complete_word;
    }
}
