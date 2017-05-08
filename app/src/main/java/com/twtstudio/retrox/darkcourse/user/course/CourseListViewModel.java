package com.twtstudio.retrox.darkcourse.user.course;

import android.content.Context;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableList;

import com.kelin.mvvmlight.base.ViewModel;
import com.kelin.mvvmlight.command.ReplyCommand;
import com.kelin.mvvmlight.messenger.Messenger;
import com.twtstudio.retrox.darkcourse.BR;
import com.twtstudio.retrox.darkcourse.R;
import com.twtstudio.retrox.darkcourse.model.RxErrorHandler;
import com.twtstudio.retrox.darkcourse.model.StudentApiClient;
import com.twtstudio.retrox.darkcourse.user.course.bean.MyCoursesBean;

import java.util.ArrayList;

import me.tatarka.bindingcollectionadapter2.ItemBinding;
import me.tatarka.bindingcollectionadapter2.collections.DiffObservableList;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by retrox on 06/05/2017.
 */

public class CourseListViewModel implements ViewModel {

    public static final String TOKEN_MY_COURSE_REFRESH = "MY_COURSE_REFRESH";
    private Context context;

    public final DiffObservableList<CourseItemViewModel> items = new DiffObservableList<>(new DiffObservableList.Callback<CourseItemViewModel>() {
        @Override
        public boolean areItemsTheSame(CourseItemViewModel oldItem, CourseItemViewModel newItem) {
            return oldItem.courseName.equals(newItem.courseName);
        }

        @Override
        public boolean areContentsTheSame(CourseItemViewModel oldItem, CourseItemViewModel newItem) {
            return oldItem.courseName.equals(newItem.courseName);
        }
    });

    public final ReplyCommand refreshCommand = new ReplyCommand(this::getData);

    public final ItemBinding<CourseItemViewModel> itemBinding = ItemBinding.of(BR.viewModel, R.layout.item_course_mine);

    public final ViewStyle viewStyle = new ViewStyle();

    public class ViewStyle {
        public final ObservableBoolean refreshState = new ObservableBoolean(false);
    }

    public CourseListViewModel(Context context) {
        this.context = context;
        Messenger.getDefault().register(context, TOKEN_MY_COURSE_REFRESH, this::getData);
        getData();
    }

    public void getData() {
        viewStyle.refreshState.set(true);
        StudentApiClient.studentApi.getMyCourses()
                .subscribeOn(Schedulers.io())
                .map(myCoursesBean -> myCoursesBean.data)
                .flatMap(Observable::from)
                .map(CourseItemViewModel::new)
                .collect(ArrayList<CourseItemViewModel>::new, ArrayList::add)
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate(() -> viewStyle.refreshState.set(false))
                .subscribe(items::update, new RxErrorHandler());
    }


}
