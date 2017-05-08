package com.twtstudio.retrox.darkcourse.user.manage.bean;

import java.util.List;

/**
 * Created by retrox on 08/05/2017.
 */

public class AllCourseBean {


    /**
     * data : [{"teacher":"耿直刚","cancelYear":2018,"minGrade":2,"cname":"物理实验","credit":1,"cid":"2100347"}]
     * err_code : -1
     * message : success
     */

    public int err_code;
    public String message;
    public List<DataBean> data;

    public static class DataBean {
        /**
         * teacher : 耿直刚
         * cancelYear : 2018
         * minGrade : 2
         * cname : 物理实验
         * credit : 1.0
         * cid : 2100347
         */

        public String teacher;
        public int cancelYear;
        public int minGrade;
        public String cname;
        public double credit;
        public String cid;
    }
}
