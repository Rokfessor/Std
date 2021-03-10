package org.maxim.model.searchEngine;

import java.util.*;

public class SearchEngine {
    private static SearchEngine instance;
    private static WordsStorage[] wordsStorage;

    public void setWords(List<String> words) {
        //Инициализация базы слов
        int len = 0;
        for (String word : words)
            if (word.length() > len)
                len = word.length();
        wordsStorage = new WordsStorage[len];

        for (int i = 1; i <= len; i++) {
            wordsStorage[i - 1] = new WordsStorage(i);
        }

        for (String word : words) {
            wordsStorage[word.length() - 1].add(word);
        }

        for (int i = 0; i < wordsStorage.length; i++) {
            System.err.println(wordsStorage[i].size);
            System.err.println(wordsStorage[i].toString() + "\n\n");
        }
    }

    public void addWord(String word) {
        //Добавление слова в базу
        if (word.length() > wordsStorage.length) {
            WordsStorage[] temp = Arrays.copyOf(wordsStorage, word.length());
            wordsStorage = temp;
        }
        wordsStorage[word.length() - 1].add(word);
    }

    public void removeWord(String word) throws ArrayIndexOutOfBoundsException {
        //Удаления слова из базы
        if (word.length() != 0)
            wordsStorage[word.length() - 1].remove(word);
    }

    public List<String> findByMask(String mask) {
        //ПОиск по маске
        if (mask.length() == 0 || mask.length() > wordsStorage.length)
            return new ArrayList<>();

        mask = mask.toLowerCase(Locale.ROOT);

        Words[][] words = wordsStorage[mask.length() - 1].getWords();
        List<String> result;

        int i = 0;
        if (mask.charAt(i) == '?') {
            for (int j = 0; j < mask.length(); j++) {
                if (mask.charAt(j) != '?') {
                    i = j;
                    break;
                }
            }
        }

        result = words[(int) mask.charAt(i) - (int) 'а'][i].getWords();

        for (; i < mask.length(); i++) {
            if (result.size() == 0)
                break;

            if (mask.charAt(i) != '?') {
                List<String> temp = words[(int) mask.charAt(i) - (int) 'а'][i].getWords();
                List<String> t = new ArrayList<>();
                for (String value : result) {
                    for (String s : temp) {
                        if (value.equals(s)) {
                            t.add(value);
                            break;
                        }
                    }
                }
                result = t;
            }
        }
        return result;
    }

    public static SearchEngine getInstance() {
        //Паттерн SINGLETON
        if (instance == null) {
            instance = new SearchEngine();
        }
        return instance;
    }
}
