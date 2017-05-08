package com.twtstudio.retrox.darkcourse.user.course;

import android.content.Context;
import android.content.DialogInterface;
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
import com.twtstudio.retrox.darkcourse.user.course.bean.MyCoursesBean;

import es.dmoral.toasty.Toasty;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by retrox on 06/05/2017.
 */

public class CourseItemViewModel implements ViewModel {

    public String courseName = " ";
    public String teacherName = " ";
    public String credit = " ";
    public String cid;

    public static int postion = 0;

    public int color = ContextCompat.getColor(APP.getContext(), ColorPalette.getColor(postion));

    public final ReplyCommand<View> clickCommand = new ReplyCommand<View>(view -> {
        AlertDialog dialog = new AlertDialog.Builder(view.getContext())
                .setTitle(courseName)
                .setMessage("You want to exit this course?")
                .setPositiveButton("Yes", (dialog1, which) -> {
                    StudentApiClient.studentApi.exitCourse(cid)
                            .subscribeOn(Schedulers.io())
                            .map(booleanApiResponse -> booleanApiResponse.data)
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(aBoolean -> {
                                if (aBoolean){
                                    Toasty.success(view.getContext(),courseName+"\nExit Success").show();
                                    Messenger.getDefault().sendNoMsg(CourseListViewModel.TOKEN_MY_COURSE_REFRESH);
                                }
                            },new RxErrorHandler());
                }).setNegativeButton("No",(dialog1, which) -> {}).create();
        dialog.show();
    });

    private CourseItemViewModel(String courseName, String teacherName, Object credit) {
        this.courseName = courseName;
        this.teacherName = teacherName;
        this.credit = String.valueOf(credit);
        postion++;
    }

    public CourseItemViewModel(MyCoursesBean.DataBean dataBean) {
        this(dataBean.cname, dataBean.teacher, dataBean.credit);
        cid = dataBean.cid;
    }



}
