package com.twtstudio.retrox.darkcourse.model;

import com.twtstudio.retrox.darkcourse.user.manage.bean.AllCourseBean;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by retrox on 06/05/2017.
 */

public interface CourseApi {

    @GET("getAll")
    Observable<AllCourseBean> getAllCourses();

    @GET("delete")
    Observable<ApiResponse<Boolean>> deleteCourse(@Query("cid") String cid);


}
