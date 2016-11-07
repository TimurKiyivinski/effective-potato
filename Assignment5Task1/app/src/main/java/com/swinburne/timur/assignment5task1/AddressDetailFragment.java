package com.swinburne.timur.assignment5task1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.swinburne.timur.assignment5task1.address.Address;
import com.swinburne.timur.assignment5task1.address.AddressContent;

/**
 * A fragment representing a single Address detail screen.
 * This fragment is either contained in a {@link AddressListActivity}
 * in two-pane mode (on tablets) or a {@link AddressDetailActivity}
 * on handsets.
 */
public class AddressDetailFragment extends Fragment implements View.OnClickListener {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The address content this fragment is presenting.
     */
    private Address mItem;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public AddressDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the address content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mItem = AddressContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));
        }
    }

    /**
     * Initialize fragment details
     * @param inflater Inflater
     * @param container Root view
     * @param savedInstanceState Instance state
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.address_detail, container, false);

        // Show the address content as text in a TextView.
        if (mItem != null) {
            EditText textName = (EditText) rootView.findViewById(R.id.address_name);
            EditText textPhone = (EditText) rootView.findViewById(R.id.address_phone);
            EditText textEmail = (EditText) rootView.findViewById(R.id.address_email);
            Button button = (Button) rootView.findViewById(R.id.call_button);
            textName.setText(mItem.getName());
            textPhone.setText(mItem.getNumber());
            textEmail.setText(mItem.getEmail());
            button.setText(button.getText() + " " + mItem.getName());
            textName.setEnabled(false);
            textPhone.setEnabled(false);
            textEmail.setEnabled(false);

            button.setOnClickListener(this);
        }

        return rootView;
    }

    /**
     * Handle call button press
     * @param v
     */
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + mItem.getNumber()));
        try {
            startActivity(intent);
        } catch (SecurityException e) {
            // Swallow exception
        }
    }
}
