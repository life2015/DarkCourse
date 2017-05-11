package com.twtstudio.retrox.darkcourse.manage.course;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableField;
import android.view.View;

import com.kelin.mvvmlight.command.ReplyCommand;
import com.orhanobut.logger.Logger;
import com.twtstudio.retrox.darkcourse.BR;
import com.twtstudio.retrox.darkcourse.manage.course.bean.CourseBean;
import com.twtstudio.retrox.darkcourse.model.ApiResponse;
import com.twtstudio.retrox.darkcourse.model.CourseApiClient;
import com.twtstudio.retrox.darkcourse.model.RxErrorHandler;

import es.dmoral.toasty.Toasty;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by retrox on 11/05/2017.
 */

public class CourseAddViewModel extends BaseObservable {

    private CourseBean courseBean = new CourseBean();

    private boolean update;

    private String cid;

    public CourseAddViewModel(boolean update,String cid) {
        this.update = update;
        this.cid = cid;
        if (update){
            getData();
        }
    }

    public CourseAddViewModel() {
        this(false,null);
    }

    public final ReplyCommand<View> fabClickCommand = new ReplyCommand<View>(view -> {

        if (update) {
            CourseApiClient.courseApi.updateCourse(courseBean)
                    .subscribeOn(Schedulers.io())
                    .map(booleanApiResponse -> booleanApiResponse.data)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(aBoolean -> {
                        if (aBoolean) {
                            Toasty.success(view.getContext(), "Course information update success!").show();
                        }
                    }, new RxErrorHandler());
        } else {
            CourseApiClient.courseApi.registerCourse(courseBean)
                    .subscribeOn(Schedulers.io())
                    .map(booleanApiResponse -> booleanApiResponse.data)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(aBoolean -> {
                        if (aBoolean) {
                            Toasty.success(view.getContext(), "Course register success!").show();
                        }
                    }, new RxErrorHandler());
        }
    });

    public final ReplyCommand<View> testClick = new ReplyCommand<View>(view -> {
        Logger.d(courseBean.toString());
    });

    @Bindable
    public CourseBean getCourseBean() {
        return courseBean;
    }

    public void setCourseBean(CourseBean courseBean) {
        this.courseBean = courseBean;
        notifyPropertyChanged(BR.courseBean);
        notifyPropertyChanged(BR._all);
    }

    public void getData() {
        CourseApiClient.courseApi.queryCourse(cid)
                .subscribeOn(Schedulers.io())
                .map(courseBeanApiResponse -> courseBeanApiResponse.data)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::setCourseBean,new RxErrorHandler());

    }
}
