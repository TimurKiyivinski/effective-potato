package com.example.timur.assignment2task1;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.support.v7.app.AlertDialog;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * TODO: document your custom view class.
 */
public class LampView extends LinearLayout {
    private Drawable on;
    private Drawable off;
    private ImageView imageView;
    private TextView textView;
    private Camera camera;
    private Parameters cameraParameters;
    private boolean lampOn;
    private boolean hasFlash;

    public LampView(Context context) {
        super(context);
        init(null, 0);
    }

    public LampView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public LampView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    public void setOn() {
        this.imageView.setImageDrawable(this.on);
        this.textView.setText(R.string.lamp_on);
        this.lampOn = true;
        if (hasFlash) {
            if (this.camera == null || this.cameraParameters == null) return;
            this.cameraParameters.setFlashMode(Parameters.FLASH_MODE_TORCH);
            this.camera.setParameters(this.cameraParameters);
            this.camera.startPreview();
        }
    }

    public void setOff() {
        this.imageView.setImageDrawable(this.off);
        this.textView.setText(R.string.lamp_off);
        this.lampOn = false;
        if (hasFlash) {
            if (this.camera == null || this.cameraParameters == null) return;
            this.cameraParameters.setFlashMode(Parameters.FLASH_MODE_OFF);
            this.camera.setParameters(this.cameraParameters);
            this.camera.stopPreview();
        }
    }

    public boolean isLampOn() {
        return this.lampOn;
    }

    private void init(AttributeSet attrs, int defStyle) {
        // Set LinearLayour orientation
        this.setOrientation(LinearLayout.VERTICAL);

        // Initialize drawables
        this.on = getResources().getDrawable(R.drawable.on);
        this.off = getResources().getDrawable(R.drawable.off);

        // Configure ImageView
        this.imageView = new ImageView(super.getContext());
        this.imageView.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));

        // Configure TextView
        this.textView = new TextView(super.getContext());
        this.textView.setTextSize(22f);
        this.textView.setGravity(1);

        // Set View visibility
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            this.imageView.setVisibility(View.VISIBLE);
            this.textView.setVisibility(View.GONE);
        } else {
            this.imageView.setVisibility(View.GONE);
            this.textView.setVisibility(View.VISIBLE);
        }

        // Add Views to self
        this.addView(this.imageView);
        this.addView(this.textView);

        // Check if has flash
        this.hasFlash = super.getContext()
                .getPackageManager()
                .hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);

        if (this.hasFlash) {
            try {
                this.camera = Camera.open();
                this.cameraParameters = this.camera.getParameters();
            } catch (RuntimeException e) {
                AlertDialog alert = new AlertDialog.Builder(super.getContext()).create();
                alert.setTitle("Error");
                alert.setMessage("Sorry, your device doesn't support flash light!");
            }
        }

        // Set default state as off
        this.setOff();

        // Event handlers
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                LampView lampView = (LampView) v;
                if (lampView.isLampOn()) {
                    lampView.setOff();
                } else {
                    lampView.setOn();
                }
            }
        });

        this.setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                LampView lampView = (LampView) v;
                if (lampView.isLampOn()) {
                    lampView.setOff();
                } else {
                    lampView.setOn();
                }
                return true;
            }
        });
    }

    @Override
    protected void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            if (this.imageView != null && this.textView != null) {
                this.imageView.setVisibility(View.VISIBLE);
                this.textView.setVisibility(View.GONE);
            }
        } else {
            if (this.imageView != null && this.textView != null) {
                this.imageView.setVisibility(View.GONE);
                this.textView.setVisibility(View.VISIBLE);
            }
        }
    }
}
