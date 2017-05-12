package com.twtstudio.retrox.darkcourse.manage.course.detail;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.v4.content.ContextCompat;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;

import com.kelin.mvvmlight.base.ViewModel;
import com.kelin.mvvmlight.command.ReplyCommand;
import com.twtstudio.retrox.darkcourse.APP;
import com.twtstudio.retrox.darkcourse.BR;
import com.twtstudio.retrox.darkcourse.base.ColorPalette;
import com.twtstudio.retrox.darkcourse.manage.course.bean.CourseScoreBean;
import com.twtstudio.retrox.darkcourse.model.CourseApiClient;
import com.twtstudio.retrox.darkcourse.model.RxErrorHandler;
import com.twtstudio.retrox.darkcourse.model.UrlProvider;

import es.dmoral.toasty.Toasty;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by retrox on 13/05/2017.
 */

public class CourseScoreItemViewModel extends BaseObservable implements ViewModel {

    private CourseScoreBean courseScoreBean = new CourseScoreBean();

    public static int postion = 0;

    public int color = ContextCompat.getColor(APP.getContext(), ColorPalette.getColor(postion));

    public final ReplyCommand<View> clickCommand = new ReplyCommand<View>(view -> {
        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        builder.setTitle("Put down the Score");

        final EditText input = new EditText(view.getContext());
        input.setText(courseScoreBean.score);
        input.setInputType(InputType.TYPE_CLASS_PHONE);
        input.setHint("Score");

        builder.setView(input)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String text = input.getText().toString();
                        double ss = Double.valueOf(text);
                        if (ss > 100 || ss < 0) {
                            Toasty.error(view.getContext(), "Please write down the right score!").show();
                        } else {
                            CourseApiClient.courseApi.setScore(courseScoreBean.sid, courseScoreBean.cid)
                                    .subscribeOn(Schedulers.io())
                                    .map(booleanApiResponse -> booleanApiResponse.data)
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(aBoolean -> {
                                        if (aBoolean) {
                                            Toasty.success(view.getContext(), "Score set Success").show();
                                        } else {
                                            Toasty.error(view.getContext(), "Score set Failed").show();
                                        }
                                    }, new RxErrorHandler());
                        }
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    });

    public CourseScoreItemViewModel(CourseScoreBean courseScoreBean) {
        setCourseScoreBean(courseScoreBean);
    }

    @Bindable
    public CourseScoreBean getCourseScoreBean() {
        return courseScoreBean;
    }

    public void setCourseScoreBean(CourseScoreBean courseScoreBean) {
        this.courseScoreBean = courseScoreBean;
        notifyPropertyChanged(BR.courseBean);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CourseScoreItemViewModel that = (CourseScoreItemViewModel) o;

        if (color != that.color) return false;
        return courseScoreBean != null ? courseScoreBean.equals(that.courseScoreBean) : that.courseScoreBean == null;

    }

    @Override
    public int hashCode() {
        int result = courseScoreBean != null ? courseScoreBean.hashCode() : 0;
        result = 31 * result + color;
        return result;
    }
}
