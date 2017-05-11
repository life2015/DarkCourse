package com.twtstudio.retrox.darkcourse.manage;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.twtstudio.retrox.darkcourse.MainActivity;
import com.twtstudio.retrox.darkcourse.R;
import com.twtstudio.retrox.darkcourse.base.BaseActivity;
import com.twtstudio.retrox.darkcourse.manage.course.CourseManageFragment;
import com.twtstudio.retrox.darkcourse.model.UrlProvider;
import com.twtstudio.retrox.darkcourse.user.course.MyCourseFragment;
import com.twtstudio.retrox.darkcourse.user.info.StudentInfoFragment;
import com.twtstudio.retrox.darkcourse.user.manage.AllCourseFragment;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;

/**
 * Created by retrox on 11/05/2017.
 */

public class ManageActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setDarkStatusIcon(true);
        setContentView(R.layout.activity_manage);
        setTitle("");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tablayout);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        MyAdapter adapter = new MyAdapter(getSupportFragmentManager());

        adapter.addFragment(new CourseManageFragment(), "My Course");
        adapter.addFragment(new CourseManageFragment(), "Manage");

        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(3);
        tabLayout.setupWithViewPager(viewPager);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.main_menu_manage:
                // TODO: 09/05/2017 jump to manage page
                break;
            case R.id.main_menu_account:
                // TODO: 09/05/2017 jump to account page
                break;
            case R.id.main_menu_server:
                Toasty.info(this,"Please Change server address in main page").show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    public void setDarkStatusIcon(boolean bDark) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            View decorView = getWindow().getDecorView();
            if (decorView != null) {
                int vis = decorView.getSystemUiVisibility();
                if (bDark) {
                    vis |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
                } else {
                    vis &= ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
                }
                decorView.setSystemUiVisibility(vis);
            }
        }
    }

    private class MyAdapter extends FragmentPagerAdapter {

        List<Fragment> fragments = new ArrayList<>();
        List<String> names = new ArrayList<>();

        public void addFragment(Fragment fragment, String name) {
            fragments.add(fragment);
            names.add(name);
        }

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return names.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }


}
