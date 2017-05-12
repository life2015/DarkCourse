package com.twtstudio.retrox.darkcourse.manage.course.bean;

/**
 * Created by retrox on 30/04/2017.
 */
public class CourseBean {

    /**
     * teacher : 耿直刚
     * cancelYear : 2018
     * minGrade : 2
     * cname : 物理实验
     * credit : 1.0
     * cid : 2100347
     */

    public String teacher = "";
    public String cancelYear = "";
    public String minGrade = "";
    public String cname = "";
    public String credit = "";
    public String cid = "";

    @Override
    public String toString() {
        return "CourseBean{" +
                "teacher='" + teacher + '\'' +
                ", cancelYear='" + cancelYear + '\'' +
                ", minGrade='" + minGrade + '\'' +
                ", cname='" + cname + '\'' +
                ", credit='" + credit + '\'' +
                ", cid='" + cid + '\'' +
                '}';
    }
}
