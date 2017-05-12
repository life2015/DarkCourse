package com.twtstudio.retrox.darkcourse.user.manage;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.kelin.mvvmlight.base.ViewModel;
import com.kelin.mvvmlight.command.ReplyCommand;
import com.kelin.mvvmlight.messenger.Messenger;
import com.twtstudio.retrox.darkcourse.APP;
import com.twtstudio.retrox.darkcourse.base.ColorPalette;
import com.twtstudio.retrox.darkcourse.model.RxErrorHandler;
import com.twtstudio.retrox.darkcourse.model.StudentApiClient;

import com.twtstudio.retrox.darkcourse.user.course.CourseListViewModel;
import com.twtstudio.retrox.darkcourse.user.manage.bean.AllCourseBean;

import es.dmoral.toasty.Toasty;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by retrox on 08/05/2017.
 */

public class CourseChooseItemViewModel implements ViewModel {

    public static int postion = 0;

    public int color = ContextCompat.getColor(APP.getContext(), ColorPalette.getColor(postion));

    public String courseName;
    public String teacherName;
    public String cid;
    public String isAvaiable;

    public String cancelYear;
    public String minGradle;
    public String credit;

    public final ReplyCommand<View> clickCommand = new ReplyCommand<View>(view -> {
        new AlertDialog.Builder(view.getContext())
                .setTitle(courseName)
                .setMessage("Do you want to choose this course ?")
                .setPositiveButton("Yes!",(dialog, which) -> {
                    StudentApiClient.studentApi.chooseCourse(cid)
                            .subscribeOn(Schedulers.io())
                            .map(booleanApiResponse -> booleanApiResponse.data)
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(aBoolean -> {
                                if (aBoolean) {
                                    Toasty.success(view.getContext(), courseName + "\nChoose success!").show();
                                    Messenger.getDefault().sendNoMsg(CourseListViewModel.TOKEN_MY_COURSE_REFRESH);
                                }
                            }, new RxErrorHandler());
                }).setNegativeButton("No",(dialog, which) -> {})
                .show();

    });


    public CourseChooseItemViewModel() {

    }

    public CourseChooseItemViewModel(String courseName, String teacherName, String cid, String isAvaiable, Object cancelYear, Object minGradle, Object credit) {
        this.courseName = courseName;
        this.teacherName = teacherName;
        this.cid = cid;
        this.isAvaiable = isAvaiable;
        this.cancelYear = String.valueOf(cancelYear);
        this.minGradle = String.valueOf(minGradle);
        this.credit = String.valueOf(credit);
        postion++;
    }

    public CourseChooseItemViewModel(AllCourseBean.DataBean dataBean) {
        this(dataBean.cname, dataBean.teacher, dataBean.cid, "Available to Choose", dataBean.cancelYear, dataBean.minGrade, dataBean.credit);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CourseChooseItemViewModel that = (CourseChooseItemViewModel) o;

        if (color != that.color) return false;
        if (courseName != null ? !courseName.equals(that.courseName) : that.courseName != null)
            return false;
        if (teacherName != null ? !teacherName.equals(that.teacherName) : that.teacherName != null)
            return false;
        if (cid != null ? !cid.equals(that.cid) : that.cid != null) return false;
        if (isAvaiable != null ? !isAvaiable.equals(that.isAvaiable) : that.isAvaiable != null)
            return false;
        if (cancelYear != null ? !cancelYear.equals(that.cancelYear) : that.cancelYear != null)
            return false;
        if (minGradle != null ? !minGradle.equals(that.minGradle) : that.minGradle != null)
            return false;
        return credit != null ? credit.equals(that.credit) : that.credit == null;

    }

    @Override
    public int hashCode() {
        int result = color;
        result = 31 * result + (courseName != null ? courseName.hashCode() : 0);
        result = 31 * result + (teacherName != null ? teacherName.hashCode() : 0);
        result = 31 * result + (cid != null ? cid.hashCode() : 0);
        result = 31 * result + (isAvaiable != null ? isAvaiable.hashCode() : 0);
        result = 31 * result + (cancelYear != null ? cancelYear.hashCode() : 0);
        result = 31 * result + (minGradle != null ? minGradle.hashCode() : 0);
        result = 31 * result + (credit != null ? credit.hashCode() : 0);
        return result;
    }
}
