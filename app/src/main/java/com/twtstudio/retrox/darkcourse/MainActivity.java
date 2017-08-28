package com.twtstudio.retrox.darkcourse;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.twtstudio.retrox.darkcourse.base.BaseActivity;
import com.twtstudio.retrox.darkcourse.login.LoginActivity;
import com.twtstudio.retrox.darkcourse.manage.ManageActivity;
import com.twtstudio.retrox.darkcourse.model.UrlProvider;
import com.twtstudio.retrox.darkcourse.user.course.MyCourseFragment;
import com.twtstudio.retrox.darkcourse.user.info.StudentInfoFragment;
import com.twtstudio.retrox.darkcourse.user.manage.AllCourseFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setDarkStatusIcon(true);
        setContentView(R.layout.activity_main);
        setTitle("");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tablayout);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        MyAdapter adapter = new MyAdapter(getSupportFragmentManager());

        adapter.addFragment(new MyCourseFragment(),"My Course");
        adapter.addFragment(new AllCourseFragment(),"Manage");
        adapter.addFragment(new StudentInfoFragment(),"Info");

        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(3);
        tabLayout.setupWithViewPager(viewPager);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.main_menu_manage:
                Intent intent = new Intent(this, ManageActivity.class);
                startActivity(intent);
                // TODO: 09/05/2017 jump to manage page
                break;
            case R.id.main_menu_account:
                // TODO: 09/05/2017 jump to account page
                Intent intent1 = new Intent(this, LoginActivity.class);
                startActivity(intent1);
                finish();
                break;
            case R.id.main_menu_server:
                setIPAddr();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    public void setIPAddr() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Title");

        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT );
        input.setHint("172.23.99.207:8080");
        builder.setView(input);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String text = input.getText().toString();
                UrlProvider.setUrl(text);
            }
        });
        builder.setTitle("Set your server Address");
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
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
