package com.twtstudio.retrox.darkcourse.user.course;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import com.kelin.mvvmlight.base.ViewModel;
import com.twtstudio.retrox.darkcourse.BR;
import com.twtstudio.retrox.darkcourse.R;
import com.twtstudio.retrox.darkcourse.model.StudentApiClient;
import com.twtstudio.retrox.darkcourse.user.course.bean.MyCoursesBean;

import me.tatarka.bindingcollectionadapter2.ItemBinding;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by retrox on 06/05/2017.
 */

public class CourseListViewModel implements ViewModel{

    public final ObservableList<CourseItemViewModel> items = new ObservableArrayList<>();

    public final ItemBinding<CourseItemViewModel> itemBinding = ItemBinding.of(BR.viewModel, R.layout.item_course_mine);

    public CourseListViewModel() {
        getData();
    }

    public void getData(){
        StudentApiClient.studentApi.getMyCourses()
                .subscribeOn(Schedulers.io())
                .map(myCoursesBean -> myCoursesBean.data)
                .flatMap(Observable::from)
                .map(dataBean -> new CourseItemViewModel(dataBean))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(items::add,Throwable::printStackTrace);
    }
}
