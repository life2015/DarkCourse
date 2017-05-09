package com.twtstudio.retrox.darkcourse.user.manage;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableList;

import com.kelin.mvvmlight.base.ViewModel;
import com.kelin.mvvmlight.command.ReplyCommand;
import com.twtstudio.retrox.darkcourse.APP;
import com.twtstudio.retrox.darkcourse.BR;
import com.twtstudio.retrox.darkcourse.R;
import com.twtstudio.retrox.darkcourse.model.CourseApiClient;
import com.twtstudio.retrox.darkcourse.model.RxErrorHandler;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;
import me.tatarka.bindingcollectionadapter2.ItemBinding;
import me.tatarka.bindingcollectionadapter2.collections.DiffObservableList;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by retrox on 08/05/2017.
 */

public class CourseListViewModel implements ViewModel {

    public final DiffObservableList<CourseChooseItemViewModel> items = new DiffObservableList<CourseChooseItemViewModel>(new DiffObservableList.Callback<CourseChooseItemViewModel>() {
        @Override
        public boolean areItemsTheSame(CourseChooseItemViewModel oldItem, CourseChooseItemViewModel newItem) {
            return oldItem.cid.equals(newItem.cid);
        }

        @Override
        public boolean areContentsTheSame(CourseChooseItemViewModel oldItem, CourseChooseItemViewModel newItem) {
            return oldItem.cid.equals(newItem.cid);
        }
    });

    public final ItemBinding<CourseChooseItemViewModel> itemBinding = ItemBinding.of(BR.viewModel, R.layout.item_course_tochoose);

    public final ReplyCommand refreshCommand = new ReplyCommand(this::getData);

    public final ViewStyle viewStyle = new ViewStyle();

    public class ViewStyle {
        public final ObservableBoolean refreshState = new ObservableBoolean(false);
    }

    public CourseListViewModel() {
        getData();
    }

    public void getData() {
        viewStyle.refreshState.set(true);

        CourseApiClient.courseApi.getAllCourses()
                .subscribeOn(Schedulers.io())
                .map(allCourseBean -> allCourseBean.data)
                .flatMap(Observable::from)
                .map(CourseChooseItemViewModel::new)
                .collect(ArrayList<CourseChooseItemViewModel>::new, ArrayList::add)
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate(() -> viewStyle.refreshState.set(false))
                .subscribe(items::update, new RxErrorHandler());

    }
}
