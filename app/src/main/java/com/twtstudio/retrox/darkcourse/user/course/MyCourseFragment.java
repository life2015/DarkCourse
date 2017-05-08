package com.twtstudio.retrox.darkcourse.user.course;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kelin.mvvmlight.messenger.Messenger;
import com.twtstudio.retrox.darkcourse.BR;
import com.twtstudio.retrox.darkcourse.R;
import com.twtstudio.retrox.darkcourse.base.ColorPalette;

/**
 * Created by retrox on 06/05/2017.
 */

public class MyCourseFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_my_course,container,false);
        ViewDataBinding viewDataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_my_course, container, false);
        CourseListViewModel courseListViewModel = new CourseListViewModel(this.getContext());
        viewDataBinding.setVariable(BR.viewModel, courseListViewModel);
        View view = viewDataBinding.getRoot();
        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.srl_course);
        swipeRefreshLayout.setColorSchemeResources(ColorPalette.getColors());
        return viewDataBinding.getRoot();
    }

    @Override
    public void onDestroy() {
        Messenger.getDefault().unregister(this.getContext());
        super.onDestroy();
    }
}
