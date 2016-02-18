package me.zhang.lab.editor;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import me.zhang.lab.R;

/**
 * Created by Zhang on 2016/2/18 上午 11:26 .
 */
public class EditorActivity extends Activity {

    private static final String TAG = EditorActivity.class.getSimpleName();

    private static final HashMap<String, Integer> emoticons = new HashMap<>();

    static {
        emoticons.put(":*", R.drawable.emo_im_kiss);
        emoticons.put(":-D", R.drawable.emo_im_glad);
        emoticons.put(":)", R.drawable.emo_im_happy);
        emoticons.put(":-(", R.drawable.emo_im_sad);
    }

    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_editor);

        editText = (EditText) findViewById(R.id.et_content);
    }


    Random random = new Random(System.currentTimeMillis());
    Object[] emojis = emoticons.keySet().toArray();

    public void insertImage(View view) {
        editText.append(getSmiledText((String) emojis[random.nextInt(emoticons.size())]));

        Log.i(TAG, editText.getText().toString());
    }

    private Spannable getSmiledText(String text) {
        SpannableStringBuilder builder = new SpannableStringBuilder(text);
        int index;
        for (index = 0; index < builder.length(); index++) {
            for (Map.Entry<String, Integer> entry : emoticons.entrySet()) {
                int length = entry.getKey().length();
                if (index + length > builder.length())
                    continue;
                if (builder.subSequence(index, index + length).toString().equals(entry.getKey())) {
                    Drawable drawable = getResources().getDrawable(entry.getValue());
                    if (drawable != null) {
                        drawable.setBounds(0, 0, editText.getLineHeight(), editText.getLineHeight());
                        builder.setSpan(new ImageSpan(drawable), index, index + length,
                                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        index += length - 1;
                    }
                    break;
                }
            }
        }
        return builder;
    }
}