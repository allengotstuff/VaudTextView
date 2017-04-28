package com.tubitv.ui;

import android.content.Context;
import android.util.Log;

/**
 * Created by stoyan on 4/28/17.
 */
public enum VaudType {
    VAUD_REGULAR(0, "Vaud-Regular.otf"),
    VAUD_BOLD(1, "Vaud-Bold.otf");

    /**
     * The int value of this enum, defined in attr.xml
     */
    private int mValue;

    /**
     * The resource name in assets of the typeface file
     */
    private String mAssetName;

    /**
     * Constructor to set the enum int value and corresponding asset
     * file name
     *
     * @param value     The value
     * @param assetName The file name
     */
    VaudType(int value, String assetName) {
        this.mValue = value;
        this.mAssetName = assetName;
    }

    /**
     * Get the {@link VaudType} for the given enum value that matches the
     * {@link #mValue} of the {@link VaudType}
     *
     * @param val The enum value
     * @return
     */
    public static VaudType getTypeface(int val) {
        switch (val) {
            case 0:
                return VAUD_REGULAR;
            case 1:
                return VAUD_BOLD;
            default:
                Log.w("VaudType", "Typeface  not supported, defaulting to roboto_regular");
                return VAUD_REGULAR;
        }
    }

    public static VaudType getType(String name) {
        switch (name) {
            case "vaud_regular":
                return VAUD_REGULAR;
            case "vaud_bold":
                return VAUD_BOLD;
            default:
                Log.w("VaudType", "VaudType " + name + " not supported");
                return VAUD_REGULAR;

        }
    }

    /**
     * Gets the enum value of this typeface as defined in attr.xml
     *
     * @return
     */
    public int getValue() {
        return mValue;
    }

    /**
     * Gets the resource name in assets of the typeface file
     *
     * @return
     */
    public String getAssetFileName() {
        return mAssetName;
    }

    public static int getDefaultTypeface(Context context) {
        return VAUD_REGULAR.getValue();
    }
}
