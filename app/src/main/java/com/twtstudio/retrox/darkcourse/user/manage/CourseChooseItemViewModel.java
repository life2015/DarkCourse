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


}
