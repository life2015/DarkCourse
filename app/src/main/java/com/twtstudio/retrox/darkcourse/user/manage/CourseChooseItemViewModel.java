package com.twtstudio.retrox.darkcourse.user.manage;

import android.support.v4.content.ContextCompat;
import android.view.View;

import com.kelin.mvvmlight.base.ViewModel;
import com.kelin.mvvmlight.command.ReplyCommand;
import com.twtstudio.retrox.darkcourse.APP;
import com.twtstudio.retrox.darkcourse.base.ColorPalette;
import com.twtstudio.retrox.darkcourse.user.manage.bean.AllCourseBean;

import es.dmoral.toasty.Toasty;

/**
 * Created by retrox on 08/05/2017.
 */

public class CourseChooseItemViewModel implements ViewModel {

    public static int postion = 0;

    public int color = ContextCompat.getColor(APP.getContext(),ColorPalette.getColor(postion));

    public String courseName;
    public String teacherName;
    public String cid;
    public String isAvaiable;

    public String cancelYear;
    public String minGradle;
    public String credit;

    public final ReplyCommand<View> clickCommand = new ReplyCommand<View>(view -> {
        Toasty.success(view.getContext(),"Click!").show();
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
