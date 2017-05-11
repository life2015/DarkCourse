package com.twtstudio.retrox.darkcourse.manage.course;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.twtstudio.retrox.darkcourse.R;
import com.twtstudio.retrox.darkcourse.base.ColorPalette;
import com.twtstudio.retrox.darkcourse.databinding.FragmentCourseManageBinding;

/**
 * Created by retrox on 11/05/2017.
 */

public class CourseManageFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentCourseManageBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_course_manage, container, false);
        binding.srlCourseManage.setColorSchemeResources(ColorPalette.getColors());
        binding.setViewModel(new CourseItemListViewModel());
        return binding.getRoot();

    }
}
