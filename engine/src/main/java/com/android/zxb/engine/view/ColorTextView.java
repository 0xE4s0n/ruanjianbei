package com.android.zxb.engine.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;

import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class ColorTextView extends AppCompatTextView {

    class WordsColorMap {
        public String words;
        public int color;

        public WordsColorMap(String words, int color) {
            this.words = words;
            this.color = color;
        }
    }

    private List<WordsColorMap> mWordsColorMaps;
    private boolean mSpaces;

    public ColorTextView(Context context) {
        super(context);
    }

    public ColorTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setup(attrs);
    }

    public ColorTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setup(attrs);
    }

    public void setup(AttributeSet attrs) {
    }

    public void setSpaces(boolean spaces) {
        this.mSpaces = spaces;
    }

    public boolean isSpaces() {
        return mSpaces;
    }

    private List<WordsColorMap> getTxtColors() {
        if (mWordsColorMaps == null) mWordsColorMaps = new ArrayList<>();
        return mWordsColorMaps;
    }

    public void addTextColor(String text, int color) {
        if (text != null && text.trim().length() > 0 && color != 0)
            getTxtColors().add(new WordsColorMap((isSpaces() ? " " : "") + text, color));
    }

    public void addTextColorRes(int text, int color) {
        addTextColor(getContext().getString(text), ContextCompat.getColor(getContext(), color));
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void setDrawable(int id) {
        Drawable drawable = getContext().getDrawable(id);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), (int) (drawable.getMinimumHeight()));
        setCompoundDrawables(null, null, drawable, null);
    }

    public void apply() {
        setColorWords();
    }

    public void setColorWords() {
        String text = "";
        for (WordsColorMap wordsColorMap : mWordsColorMaps) {
            text += wordsColorMap.words;
        }
        Spannable s = new SpannableString(text);

        int indexStart = 0;
        for (WordsColorMap wordsColorMap : mWordsColorMaps) {
            String str = wordsColorMap.words;
            int color = wordsColorMap.color;

            if (!TextUtils.isEmpty(str)) {
                s.setSpan(new ForegroundColorSpan(color), indexStart, indexStart + str.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                indexStart += str.length();
            }

        }
        setText(s, BufferType.SPANNABLE);
    }
}

