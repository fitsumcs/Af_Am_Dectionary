package com.example.a3020.mydict;

public class Word
{
    public String key = "";
    public String value = "";

    public Word()
    {
    }
    public Word(String key, String value)
    {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }
}
