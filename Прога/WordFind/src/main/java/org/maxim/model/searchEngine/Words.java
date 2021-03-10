package org.maxim.model.searchEngine;

import java.util.ArrayList;

public class Words {
    ArrayList<String> words = new ArrayList<>();

    public ArrayList<String> getWords() {
        return words;
    }

    public void addWord(String word) {
        this.words.add(word);
    }
}
