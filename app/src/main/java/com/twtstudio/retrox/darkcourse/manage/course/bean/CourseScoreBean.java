package com.twtstudio.retrox.darkcourse.manage.course.bean;

/**
 * Created by retrox on 13/05/2017.
 */

public class CourseScoreBean {


    /**
     * score : 76.00
     * classId : 151801
     * sname : 冀辰阳
     * className : 软件一班
     * cid : 2180101
     * sid : 3015204342
     */

    public String score;
    public String classId;
    public String sname;
    public String className;
    public String cid;
    public String sid;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CourseScoreBean that = (CourseScoreBean) o;

        if (score != null ? !score.equals(that.score) : that.score != null) return false;
        if (classId != null ? !classId.equals(that.classId) : that.classId != null) return false;
        if (sname != null ? !sname.equals(that.sname) : that.sname != null) return false;
        if (className != null ? !className.equals(that.className) : that.className != null)
            return false;
        if (cid != null ? !cid.equals(that.cid) : that.cid != null) return false;
        return sid != null ? sid.equals(that.sid) : that.sid == null;

    }

    @Override
    public int hashCode() {
        int result = score != null ? score.hashCode() : 0;
        result = 31 * result + (classId != null ? classId.hashCode() : 0);
        result = 31 * result + (sname != null ? sname.hashCode() : 0);
        result = 31 * result + (className != null ? className.hashCode() : 0);
        result = 31 * result + (cid != null ? cid.hashCode() : 0);
        result = 31 * result + (sid != null ? sid.hashCode() : 0);
        return result;
    }
}
