package com.swinburne.timur.assignment3task3.word;

import android.content.Context;
import android.util.Log;

import com.swinburne.timur.assignment3task3.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class WordContent {

    /**
     * An array of sample (word) items.
     */
    public static final List<WordItem> ITEMS = new ArrayList<WordItem>();

    /**
     * A map of sample (word) items, by ID.
     */
    public static final Map<String, WordItem> ITEM_MAP = new HashMap<String, WordItem>();

    /**
     * Uses application context to parse resource file and populate data
     *
     * @param context Application context
     */
    public static void setContext(Context context) {
        InputStream inputStream = context.getResources().openRawResource(R.raw.dev_agreement);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        try {
            String line = bufferedReader.readLine();
            while (line != null) {
                String[] words = line.replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");
                for (String word: words) {
                    WordItem wordItem = ITEM_MAP.get(word);
                    if (wordItem != null) {
                        wordItem.increment();
                        wordItem.addSentence(line);
                    } else {
                        wordItem = new WordItem(word, line);
                        WordContent.addItem(wordItem);
                    }
                    Log.i("word", word);
                }
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            WordItem wordItem = new WordItem("Error", "Error reading file", 1);
            addItem(wordItem);
        }
    }

    /**
     * Adds item to ITEMS list and map
     *
     * @param item WordItem to be added
     */
    public static void addItem(WordItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.word, item);
    }
}
