package com.example.a3020.mydict;

public class DB
{
    public static String[] getData(int id)
    {
        if(id== R.id.action_entk)
        {
          return getAmhSol();
        }
        else if(id== R.id.action_kten)
        {
            return getSomAmh();
        }
        else if(id== R.id.action_ktk)
        {
            return getAmhAmh();
        }

           return new String[0];
    }

    private static String[]getAmhSol()
    {
        String[] source = new String[]{
                "a ...The First",
                "abandon",
                "ablity",
                "able",
                "about",
                "above",
                "abroad",
                "after",
                "afterwards",
                "again",
                "against",
                "all",
                "almost",
                "alone",
                "along",
                "already",
                "also",
                "although",
                "always",
                "am",
                "among",
                "amongst",
                "amoungst",
                "amount",
                "an",
                "and",
                "another",
                "any",
                "anyhow",
                "anyone",
                "anything",


        };

        return source;

    }


    private static String[] getSomAmh()
    {

        String[] source = new String[]{
                "a...The Second",
                "abandon",
                "ablity",
                "able",
                "about",
                "above",
                "abroad",
                "after",
                "afterwards",
                "again",
                "against",
                "all",
                "almost",
                "alone",
                "along",
                "already",
                "also",
                "although",
                "always",
                "am",
                "among",
                "amongst",
                "amoungst",
                "amount",
                "an",
                "and",
                "another",
                "any",
                "anyhow",
                "anyone",
                "anything",


        };

        return source;
    }

    private static String[] getAmhAmh()
    {

        String[] source = new String[]{
                "a...The Third",
                "abandon",
                "ablity",
                "able",
                "about",
                "above",
                "abroad",
                "after",
                "afterwards",
                "again",
                "against",
                "all",
                "almost",
                "alone",
                "along",
                "already",
                "also",
                "although",
                "always",
                "am",
                "among",
                "amongst",
                "amoungst",
                "amount",
                "an",
                "and",
                "another",
                "any",
                "anyhow",
                "anyone",
                "anything",


        };

        return source;
    }


}
