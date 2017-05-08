package com.twtstudio.retrox.darkcourse.base;

import android.databinding.BindingAdapter;
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
}
