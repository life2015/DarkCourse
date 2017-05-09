package com.twtstudio.retrox.darkcourse.user.manage;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.twtstudio.retrox.darkcourse.BR;
import com.twtstudio.retrox.darkcourse.R;
import com.twtstudio.retrox.darkcourse.base.ColorPalette;
import com.twtstudio.retrox.darkcourse.databinding.FragmentAllCourseTochooseBinding;

/**
 * Created by retrox on 08/05/2017.
 */

public class AllCourseFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentAllCourseTochooseBinding viewDataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_all_course_tochoose,container,false);
        CourseListViewModel viewModel = new CourseListViewModel();
//        viewModel.getData();
        viewDataBinding.setVariable(BR.viewModel,viewModel);
        viewDataBinding.srlCourseManage.setColorSchemeResources(ColorPalette.getColors());
        return viewDataBinding.getRoot();
    }

}
