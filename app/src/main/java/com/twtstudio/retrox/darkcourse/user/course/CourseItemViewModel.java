package com.twtstudio.retrox.darkcourse.user.course;

import android.content.Context;

import com.kelin.mvvmlight.base.ViewModel;
import com.twtstudio.retrox.darkcourse.user.course.bean.MyCoursesBean;

/**
 * Created by retrox on 06/05/2017.
 */

public class CourseItemViewModel implements ViewModel {

    public String courseName = " ";
    public String teacherName = " ";
    public String credit = " ";

    public CourseItemViewModel(String courseName, String teacherName, Object credit) {
        this.courseName = courseName;
        this.teacherName = teacherName;
        this.credit = String.valueOf(credit);
    }

    public CourseItemViewModel(MyCoursesBean.DataBean dataBean) {
        this(dataBean.cname,dataBean.teacher,dataBean.credit);
    }
}
