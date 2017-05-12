package com.twtstudio.retrox.darkcourse.model;

import com.twtstudio.retrox.darkcourse.manage.course.bean.CourseBean;
import com.twtstudio.retrox.darkcourse.manage.course.bean.CourseScoreBean;
import com.twtstudio.retrox.darkcourse.user.manage.bean.AllCourseBean;

import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
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

    @POST("register")
    Observable<ApiResponse<Boolean>> registerCourse(@Body CourseBean courseBean);

    @POST("update")
    Observable<ApiResponse<Boolean>> updateCourse(@Body CourseBean courseBean);

    @GET("query")
    Observable<ApiResponse<CourseBean>> queryCourse(@Query("cid") String cid);

    @GET("getTotalScoreInfo")
    Observable<ApiResponse<List<CourseScoreBean>>> getTotalScoreInfo(@Query("cid") String cid);

    @GET("getAverageScore")
    Observable<ApiResponse<String>> getAverageScore(@Query("cid") String cid);
}
