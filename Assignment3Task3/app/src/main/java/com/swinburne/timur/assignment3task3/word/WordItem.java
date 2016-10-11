package com.swinburne.timur.assignment3task3.word;

public class WordItem {
    public String word;
    public String sentence;
    public int count;

    /**
     * Word container class
     *
     * @param word The word to count
     * @param sentence The sentence the word appears in
     * @param count The number of times the word appears
     */
    public WordItem(String word, String sentence, int count) {
        this.word = word;
        this.sentence = sentence;
        this.count = count;
    }

    /**
     * Word container class, sets count to 1 by default
     *
     * @param word The word to count
     * @param sentence The sentence the word appears in
     */
    public WordItem(String word, String sentence) {
        this(word, sentence, 1);
    }

    /**
     * Increments word count
     */
    public void increment() {
        this.count += 1;
    }

    /**
     * Adds a sentence instance
     *
     * @param sentence Sentence to add
     */
    public void addSentence(String sentence) {
        this.sentence += "\n" + sentence;
    }

    @Override
    public String toString() {
        return word;
    }
}
