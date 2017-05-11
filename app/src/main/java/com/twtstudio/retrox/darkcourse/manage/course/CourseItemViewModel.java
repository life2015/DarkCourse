package com.twtstudio.retrox.darkcourse.manage.course;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.kelin.mvvmlight.base.ViewModel;
import com.kelin.mvvmlight.command.ReplyCommand;
import com.twtstudio.retrox.darkcourse.APP;
import com.twtstudio.retrox.darkcourse.base.ColorPalette;
import com.twtstudio.retrox.darkcourse.model.CourseApiClient;
import com.twtstudio.retrox.darkcourse.model.RxErrorHandler;
import com.twtstudio.retrox.darkcourse.user.manage.bean.AllCourseBean;

import es.dmoral.toasty.Toasty;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by retrox on 11/05/2017.
 */

public class CourseItemViewModel implements ViewModel {

    public static int postion = 0;

    public int color = ContextCompat.getColor(APP.getContext(), ColorPalette.getColor(postion));

    public String courseName;
    public String teacherName;
    public String cid;

    public String cancelYear;
    public String minGradle;
    public String credit;

    public final ReplyCommand<View> clickCommand = new ReplyCommand<View>(view -> {
        new AlertDialog.Builder(view.getContext())
                .setTitle(courseName)
                .setMessage("Do you want to delete this course ? \n or update the course")
                .setNeutralButton("Update Information",(dialog, which) -> {

                    // update
                    Intent intent = new Intent(view.getContext(),CourseAddActivity.class);
                    intent.putExtra("cid",cid);
                    intent.putExtra("update",true);
                    view.getContext().startActivity(intent);

                })
                .setPositiveButton("Yes!",(dialog, which) -> {
                    CourseApiClient.courseApi.deleteCourse(cid)
                            .subscribeOn(Schedulers.io())
                            .map(booleanApiResponse -> booleanApiResponse.data)
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(aBoolean -> {
                                if (aBoolean) {
                                    Toasty.success(view.getContext(), courseName + "\nDelete success!").show();
//                                    Messenger.getDefault().sendNoMsg(CourseListViewModel.TOKEN_MY_COURSE_REFRESH);
                                }
                            }, new RxErrorHandler());
                }).setNegativeButton("No",(dialog, which) -> {})
                .show();

    });


    public CourseItemViewModel() {

    }

    public CourseItemViewModel(String courseName, String teacherName, String cid, Object cancelYear, Object minGradle, Object credit) {
        this.courseName = courseName;
        this.teacherName = teacherName;
        this.cid = cid;
        this.cancelYear = String.valueOf(cancelYear);
        this.minGradle = String.valueOf(minGradle);
        this.credit = String.valueOf(credit);
        postion++;
    }

    public CourseItemViewModel(AllCourseBean.DataBean dataBean) {
        this(dataBean.cname, dataBean.teacher, dataBean.cid, dataBean.cancelYear, dataBean.minGrade, dataBean.credit);
    }



}
