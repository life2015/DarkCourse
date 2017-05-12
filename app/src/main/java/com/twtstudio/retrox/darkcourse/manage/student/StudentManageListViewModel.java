package com.twtstudio.retrox.darkcourse.manage.student;

import android.databinding.ObservableBoolean;
import android.view.View;

import com.kelin.mvvmlight.base.ViewModel;
import com.kelin.mvvmlight.command.ReplyCommand;
import com.twtstudio.retrox.darkcourse.BR;
import com.twtstudio.retrox.darkcourse.R;
import com.twtstudio.retrox.darkcourse.model.RxErrorHandler;
import com.twtstudio.retrox.darkcourse.model.StudentApiClient;
import com.twtstudio.retrox.darkcourse.user.course.CourseListViewModel;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;
import me.tatarka.bindingcollectionadapter2.ItemBinding;
import me.tatarka.bindingcollectionadapter2.collections.DiffObservableList;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by retrox on 12/05/2017.
 */

public class StudentManageListViewModel implements ViewModel {

    public final DiffObservableList<StudentManageItemViewModel> items = new DiffObservableList<StudentManageItemViewModel>(new DiffObservableList.Callback<StudentManageItemViewModel>() {
        @Override
        public boolean areItemsTheSame(StudentManageItemViewModel oldItem, StudentManageItemViewModel newItem) {
            return oldItem.getStudent().sid.equals(newItem.getStudent().sid);
        }

        @Override
        public boolean areContentsTheSame(StudentManageItemViewModel oldItem, StudentManageItemViewModel newItem) {
            return oldItem.equals(newItem);
        }
    });

    public final ItemBinding<StudentManageItemViewModel> itemBinding = ItemBinding.of(BR.viewModel, R.layout.item_student_manage);

    public final ReplyCommand refreshCommand = new ReplyCommand(this::getData);

    public final ViewStyle viewStyle = new ViewStyle();

    public class ViewStyle {
        public final ObservableBoolean refreshState = new ObservableBoolean(false);
    }

    public final ReplyCommand<View> fabClick = new ReplyCommand<View>(view -> {
        Toasty.info(view.getContext(),"text click").show();
    });

    public StudentManageListViewModel() {
        getData();
    }

    public void getData() {
        viewStyle.refreshState.set(true);
        StudentApiClient.studentApi.getAllStudent()
                .subscribeOn(Schedulers.io())
                .map(listApiResponse -> listApiResponse.data)
                .flatMap(Observable::from)
                .map(studentBean -> new StudentManageItemViewModel(studentBean.sid))
                .collect(ArrayList<StudentManageItemViewModel>::new, ArrayList::add)
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate(() -> viewStyle.refreshState.set(false))
                .subscribe(items::update, new RxErrorHandler());
    }
}
