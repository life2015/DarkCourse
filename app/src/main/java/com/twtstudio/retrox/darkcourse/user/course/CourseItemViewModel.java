package com.twtstudio.retrox.darkcourse.user.course;

/**
 * Created by retrox on 06/05/2017.
 */

public class CourseItemViewModel {

    public String courseName = " ";
    public String teacherName = " ";
    public String credit = " ";

    public CourseItemViewModel(String courseName, String teacherName, Object credit) {
        this.courseName = courseName;
        this.teacherName = teacherName;
        this.credit = String.valueOf(credit);
    }

}
