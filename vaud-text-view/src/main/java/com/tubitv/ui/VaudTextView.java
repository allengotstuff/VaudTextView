package com.tubitv.ui;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.databinding.BindingAdapter;
import android.graphics.Typeface;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.TextView;

import com.tubitv.ui.vaud_text_view.R;

import java.util.Hashtable;

/**
 * Created by stoyan on 4/28/17.
 */
@SuppressLint("AppCompatCustomView")
public class VaudTextView extends TextView {
    /**
     * A hash we use to hold multiple typefaces for views
     */
    private static final Hashtable<String, Typeface> cache = new Hashtable<>();

    /**
     * The default typeface
     */
    public static int DEFAULT_TYPEFACE;

    /**
     * The current typeface that the font is set to
     */
    private VaudType mCurrentTypeface = VaudType.VAUD_REGULAR;

    public VaudTextView(Context context) {
        super(context);
        loadAttributes(context, null);
    }

    public VaudTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        loadAttributes(context, attrs);
    }

    public VaudTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        loadAttributes(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public VaudTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        loadAttributes(context, attrs);
    }

    /**
     * Data-binding method for custom attribute bind:vaud_typeface to be set
     * @param textView The instance of the object to set value on
     * @param type The string name of the typeface, same as in xml
     */
    @BindingAdapter("bind:vaud_typeface")
    public static void setCustomTypeface(VaudTextView textView, String type) {
        textView.mCurrentTypeface = VaudType.getType(type != null ? type : "");
        Typeface typeface = getFont(textView.getContext(), textView.mCurrentTypeface.getAssetFileName());
        textView.setTypeface(typeface);
    }

    /**
     * Sets the typeface for the view
     *
     * @param context
     * @param attrs
     */
    private void loadAttributes(Context context, AttributeSet attrs) {
        DEFAULT_TYPEFACE = VaudType.getDefaultTypeface(context);

        //Typeface.createFromAsset doesn't work in the layout editor. Skipping...
        if (isInEditMode() || attrs == null || Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
            return;
        }

        TypedArray styledAttrs = context.obtainStyledAttributes(attrs, R.styleable.VaudTextView);
        Integer fontInt = styledAttrs.getInt(R.styleable.VaudTextView_vaud_typeface, DEFAULT_TYPEFACE);
        styledAttrs.recycle();

        mCurrentTypeface = VaudType.getTypeface(fontInt);
        Typeface typeface = getFont(context, mCurrentTypeface.getAssetFileName());
        setTypeface(typeface);
    }

    /**
     * Avoid reloading assets every time
     */
    public static Typeface getFont(Context context, String fontName) {
        synchronized (cache) {
            if (!cache.containsKey(fontName)) {
                try {
                    Typeface t = Typeface.createFromAsset(context.getAssets(), fontName);
                    cache.put(fontName, t);
                } catch (Exception e) {
                    return null;
                }
            }
            return cache.get(fontName);
        }
    }

    /**
     * Returns the currently set typeface of this view
     */
    public VaudType getCurrentTypeface() {
        return mCurrentTypeface;
    }
}