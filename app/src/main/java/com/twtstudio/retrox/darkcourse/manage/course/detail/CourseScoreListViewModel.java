package com.twtstudio.retrox.darkcourse.manage.course.detail;

import android.databinding.ObservableBoolean;

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
 * Created by retrox on 13/05/2017.
 */

public class CourseScoreListViewModel implements ViewModel {

    private String cid;

    public final DiffObservableList<CourseScoreItemViewModel> items = new DiffObservableList<CourseScoreItemViewModel>(new DiffObservableList.Callback<CourseScoreItemViewModel>() {
        @Override
        public boolean areItemsTheSame(CourseScoreItemViewModel oldItem, CourseScoreItemViewModel newItem) {
            return oldItem.getCourseScoreBean().cid.equals(newItem.getCourseScoreBean().cid);
        }

        @Override
        public boolean areContentsTheSame(CourseScoreItemViewModel oldItem, CourseScoreItemViewModel newItem) {
            return oldItem.equals(newItem);
        }
    });

    public final ItemBinding<CourseScoreItemViewModel> itemBinding = ItemBinding.of(BR.viewModel, R.layout.item_course_score);

    public final ReplyCommand refreshCommand = new ReplyCommand(this::getData);

    public final ViewStyle viewStyle = new ViewStyle();

    public class ViewStyle {
        public final ObservableBoolean refreshState = new ObservableBoolean(false);
    }

    public CourseScoreListViewModel(String cid) {
        this.cid = cid;
        getData();
    }

    public void getData() {
        viewStyle.refreshState.set(true);
        CourseApiClient.courseApi.getTotalScoreInfo(cid)
                .subscribeOn(Schedulers.io())
                .map(listApiResponse -> listApiResponse.data)
                .flatMap(Observable::from)
                .map(CourseScoreItemViewModel::new)
                .collect(ArrayList<CourseScoreItemViewModel>::new, ArrayList::add)
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate(() -> viewStyle.refreshState.set(false))
                .subscribe(courseScoreItemViewModels -> {
                    items.update(courseScoreItemViewModels);
                    if (items.size()==0){
                        Toasty.error(APP.getContext(),"No one has choosed this course!").show();
                    }
                }, new RxErrorHandler());

    }
}
