package com.twtstudio.retrox.darkcourse.manage.course;

import android.content.Intent;
import android.databinding.ObservableBoolean;
import android.view.View;

import com.kelin.mvvmlight.base.ViewModel;
import com.kelin.mvvmlight.command.ReplyCommand;
import com.twtstudio.retrox.darkcourse.BR;
import com.twtstudio.retrox.darkcourse.R;
import com.twtstudio.retrox.darkcourse.model.CourseApiClient;
import com.twtstudio.retrox.darkcourse.model.RxErrorHandler;
import com.twtstudio.retrox.darkcourse.user.manage.CourseChooseItemViewModel;
import com.twtstudio.retrox.darkcourse.user.manage.CourseListViewModel;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;
import me.tatarka.bindingcollectionadapter2.ItemBinding;
import me.tatarka.bindingcollectionadapter2.collections.DiffObservableList;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by retrox on 11/05/2017.
 */

public class CourseItemListViewModel implements ViewModel {

    public final DiffObservableList<CourseItemViewModel> items = new DiffObservableList<>(new DiffObservableList.Callback<CourseItemViewModel>() {
        @Override
        public boolean areItemsTheSame(CourseItemViewModel oldItem, CourseItemViewModel newItem) {
            return oldItem.cid.equals(newItem.cid);
        }

        @Override
        public boolean areContentsTheSame(CourseItemViewModel oldItem, CourseItemViewModel newItem) {
            return oldItem.cid.equals(newItem.cid);
        }
    });

    public final ItemBinding<CourseItemViewModel> itemBinding = ItemBinding.of(BR.viewModel, R.layout.item_course_manage);

    public final ReplyCommand refreshCommand = new ReplyCommand(this::getData);

    public final ViewStyle viewStyle = new ViewStyle();

    public class ViewStyle {
        public final ObservableBoolean refreshState = new ObservableBoolean(false);
    }

    public CourseItemListViewModel() {
        getData();
    }

    public void getData() {
        viewStyle.refreshState.set(true);

        CourseApiClient.courseApi.getAllCourses()
                .subscribeOn(Schedulers.io())
                .map(allCourseBean -> allCourseBean.data)
                .flatMap(Observable::from)
                .map(CourseItemViewModel::new)
                .collect(ArrayList<CourseItemViewModel>::new, ArrayList::add)
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate(() -> viewStyle.refreshState.set(false))
                .subscribe(items::update, new RxErrorHandler());

    }

    public final ReplyCommand<View> fabClick = new ReplyCommand<View>(view -> {
        Intent intent = new Intent(view.getContext(),CourseAddActivity.class);
        view.getContext().startActivity(intent);

    });
}
