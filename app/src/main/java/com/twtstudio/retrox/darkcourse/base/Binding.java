package com.twtstudio.retrox.darkcourse.base;

import android.databinding.BindingAdapter;
import android.databinding.InverseBindingAdapter;
import android.support.design.widget.TextInputEditText;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;

import com.kelin.mvvmlight.command.ReplyCommand;

/**
 * Created by retrox on 08/05/2017.
 */

public class Binding {

    @BindingAdapter({"clickCommand"})
    public static void clickCommand(View view, final ReplyCommand<View> clickCommand) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickCommand != null) {
                    clickCommand.execute(v);
                }
            }
        });
    }

    @BindingAdapter({"onRefreshCommand"})
    public static void onRefreshCommand(SwipeRefreshLayout swipeRefreshLayout, final ReplyCommand onRefreshCommand) {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (onRefreshCommand != null) {
                    onRefreshCommand.execute();
                }
            }
        });
    }

    @BindingAdapter("android:text")
    public static void bindIntegerInText(TextInputEditText tv, String value)
    {

        tv.setText(String.valueOf(value));
        // Set the cursor to the end of the text
        tv.setSelection(tv.getText().length());
    }

    @InverseBindingAdapter(attribute = "android:text")
    public static int getIntegerFromBinding(TextInputEditText view)
    {
        String string = view.getText().toString();

        return string.isEmpty() ? 0 : Integer.parseInt(string);
    }
}
