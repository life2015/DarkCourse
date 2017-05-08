package com.twtstudio.retrox.darkcourse.user.manage;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import com.kelin.mvvmlight.base.ViewModel;
import com.twtstudio.retrox.darkcourse.APP;
import com.twtstudio.retrox.darkcourse.BR;
import com.twtstudio.retrox.darkcourse.R;
import com.twtstudio.retrox.darkcourse.model.CourseApiClient;
import com.twtstudio.retrox.darkcourse.model.RxErrorHandler;

import es.dmoral.toasty.Toasty;
import me.tatarka.bindingcollectionadapter2.ItemBinding;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by retrox on 08/05/2017.
 */

public class CourseListViewModel implements ViewModel {

    public final ObservableList<CourseChooseItemViewModel> items = new ObservableArrayList<>();

    public final ItemBinding<CourseChooseItemViewModel> itemBinding = ItemBinding.of(BR.viewModel, R.layout.item_course_tochoose);

    public CourseListViewModel() {
        getData();
    }

    public void getData(){
        CourseApiClient.courseApi.getAllCourses()
                .subscribeOn(Schedulers.io())
                .map(allCourseBean -> allCourseBean.data)
                .flatMap(Observable::from)
                .map(CourseChooseItemViewModel::new)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(items::add,new RxErrorHandler());

    }
}
