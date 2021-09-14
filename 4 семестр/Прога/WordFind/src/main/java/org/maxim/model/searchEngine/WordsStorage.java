package org.maxim.model.searchEngine;

import java.util.List;
import java.util.Locale;

public class WordsStorage {
    private Words[][] words;
    public int size = 0;

    public WordsStorage(int size) {
        //Инициализация хранилища
        words = new Words[32][size];
        for (int i = 0; i < 32; i++) {
            for (int j = 0; j < size; j++) {
                words[i][j] = new Words();
            }
        }
    }

    public void add(String word) {
        //Добавление слова в хранилище
        word = word.toLowerCase(Locale.ROOT);
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) - 1072 >= 0 && word.charAt(i) - 1072 <= 32) {
                words[word.charAt(i) - 1072][i].addWord(word);
            }
        }
        size++;
    }

    public void remove(String word) {
        //Удаление слова из хранилища
        word = word.toLowerCase(Locale.ROOT);
        for (int i = 0; i < word.length(); i++) {
            List<String> list = words[word.charAt(i) - 1072][i].getWords();
            for (String w : list) {
                if (w.equals(word)) {
                    list.remove(w);
                    break;
                }
            }
        }
        size--;
    }

    public Words[][] getWords() {
        return words;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < words[0].length; i++) {
            sb.append(i).append(" - ");
            for (int j = 0; j < 32; j++) {
                sb.append(words[j][i].getWords().size()).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
