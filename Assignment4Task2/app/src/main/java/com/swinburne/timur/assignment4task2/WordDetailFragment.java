package com.swinburne.timur.assignment4task2;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.swinburne.timur.assignment4task2.R;
import com.swinburne.timur.assignment4task2.word.WordContent;
import com.swinburne.timur.assignment4task2.word.WordItem;

import java.net.URLEncoder;

/**
 * A fragment representing a single Word detail screen.
 * This fragment is either contained in a {@link WordListActivity}
 * in two-pane mode (on tablets) or a {@link WordDetailActivity}
 * on handsets.
 */
public class WordDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy content this fragment is presenting.
     */
    private WordItem mItem;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public WordDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mItem = WordContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.word_detail, container, false);

        // Show the dummy content as text in a TextView.
        if (mItem != null) {
            // Set word
            TextView wordDetail = (TextView) rootView.findViewById(R.id.word_detail);
            // Set color based on count
            wordDetail.setText(mItem.word);
            if (mItem.count > 5) {
                wordDetail.setTextColor(Color.RED);
            } else {
                wordDetail.setTextColor(Color.GREEN);
            }
            // Set sentence
            ((TextView) rootView.findViewById(R.id.word_detail_sentence)).setText(mItem.sentence);
        }

        return rootView;
    }
}
