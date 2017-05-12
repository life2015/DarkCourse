package com.twtstudio.retrox.darkcourse.manage.course.detail;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.kelin.mvvmlight.base.ViewModel;
import com.kelin.mvvmlight.command.ReplyCommand;
import com.twtstudio.retrox.darkcourse.APP;
import com.twtstudio.retrox.darkcourse.BR;
import com.twtstudio.retrox.darkcourse.base.ColorPalette;
import com.twtstudio.retrox.darkcourse.manage.course.bean.CourseScoreBean;

import es.dmoral.toasty.Toasty;

/**
 * Created by retrox on 13/05/2017.
 */

public class CourseScoreItemViewModel extends BaseObservable implements ViewModel {

    private CourseScoreBean courseScoreBean = new CourseScoreBean();

    public static int postion = 0;

    public int color = ContextCompat.getColor(APP.getContext(), ColorPalette.getColor(postion));

    public final ReplyCommand<View> clickCommand = new ReplyCommand<View>(view -> {
        Toasty.info(view.getContext(),courseScoreBean.sname).show();
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
