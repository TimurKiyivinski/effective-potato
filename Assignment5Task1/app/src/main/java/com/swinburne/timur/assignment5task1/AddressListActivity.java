package com.swinburne.timur.assignment5task1;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.swinburne.timur.assignment5task1.address.Address;
import com.swinburne.timur.assignment5task1.address.AddressContent;

import java.util.List;
import java.util.Random;

/**
 * An activity representing a list of Addresses. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link AddressDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class AddressListActivity extends AppCompatActivity {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;
    private SimpleItemRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        // Handler to create new item
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, AddressCreateActivity.class);

                startActivityForResult(intent, 2);
            }
        });

        updateRecyclerView();

        if (findViewById(R.id.address_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }
    }

    /**
     * Initializes recycler list
     */
    public void updateRecyclerView() {
        View recyclerView = findViewById(R.id.address_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);
    }

    /**
     * Create & set recycler adapter
     * @param recyclerView
     */
    public void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        this.adapter = new SimpleItemRecyclerViewAdapter(AddressContent.ITEMS);
        recyclerView.setAdapter(this.adapter);
    }

    /**
     * Update adapter whenever an intent returns
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (this.adapter != null)
            this.adapter.update();
    }

    /**
     * Address adapter
     */
    public class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private List<Address> mValues;

        /**
         * Adapter for list
         * @param items Addresses store
         */
        public SimpleItemRecyclerViewAdapter(List<Address> items) {
            mValues = items;
        }

        /**
         * Update list contents
         */
        public void update() {
            mValues = AddressContent.ITEMS;
            notifyDataSetChanged();
        }

        /**
         * Inflate cards into view
         * @param parent
         * @param viewType
         * @return
         */
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.address_list_content, parent, false);
            return new ViewHolder(view);
        }

        /**
         * ViewHolder bind handler
         * @param holder ViewHolder card pointer
         * @param position Get list position
         */
        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            // Set card content
            holder.mItem = mValues.get(position);
            holder.mContentName.setText(mValues.get(position).getName());
            holder.mContentPhone.setText(mValues.get(position).getNumber());

            // Select random image
            Integer[] icons = new Integer[]{R.drawable.icon1, R.drawable.icon2, R.drawable.icon3, R.drawable.icon4};
            holder.mContentImageView.setImageDrawable(getDrawable(icons[new Random().nextInt(icons.length)]));

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mTwoPane) {
                        Bundle arguments = new Bundle();
                        arguments.putString(AddressDetailFragment.ARG_ITEM_ID, holder.mItem.getID().toString());
                        AddressDetailFragment fragment = new AddressDetailFragment();
                        fragment.setArguments(arguments);
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.address_detail_container, fragment)
                                .commit();
                    } else {
                        Context context = v.getContext();
                        Intent intent = new Intent(context, AddressDetailActivity.class);
                        intent.putExtra(AddressDetailFragment.ARG_ITEM_ID, holder.mItem.getID().toString());

                        startActivityForResult(intent, 1);
                        // context.startActivity(intent);
                    }
                }
            });

            holder.mView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                    builder.setTitle(R.string.confirm_deletion_title);
                    builder.setMessage(R.string.confirm_deletion_text);

                    // Dialog action handlers
                    builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Remove Address based on ID
                            AddressContent.removeItem(holder.mItem.getID().toString());
                            update();
                        }
                    });
                    builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            return;
                        }
                    });

                    // Create dialog
                    AlertDialog deleteDialog = builder.create();
                    deleteDialog.show();
                    return false;
                }
            });
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        /**
         * List Card class
         */
        public class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            public final TextView mContentName;
            public final TextView mContentPhone;
            public final ImageView mContentImageView;
            public Address mItem;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                mContentName = (TextView) view.findViewById(R.id.content);
                mContentPhone = (TextView) view.findViewById(R.id.phone);
                mContentImageView = (ImageView) view.findViewById(R.id.imageViewFace);
            }
        }
    }
}
