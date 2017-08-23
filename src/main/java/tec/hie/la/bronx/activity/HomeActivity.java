package tec.hie.la.bronx.activity;

import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import tec.hie.la.bronx.R;
import tec.hie.la.bronx.adapter.TabsFragmentAdapter;
import tec.hie.la.bronx.databinding.ActivityHomeBinding;
import tec.hie.la.bronx.view.SlidingTabLayout;

public class HomeActivity extends AppCompatActivity {

    private ActivityHomeBinding binding;

    private ViewPager viewPager;

    private SlidingTabLayout slidingTabLayout;

    private TabsFragmentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        setupTabs();
        list();
    }

    private void setupTabs() {
        viewPager = binding.viewpager;
        adapter = new TabsFragmentAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        slidingTabLayout = binding.slidingTabs;
        slidingTabLayout.setDistributeEvenly(true);
        slidingTabLayout.setCustomTabView(R.layout.custom_tab, R.id.customText);
        slidingTabLayout.setViewPager(viewPager);
    }

    public void list() {
        PackageManager packageManager = getPackageManager();
        PackageInfo info = null;
        try {
            info = packageManager.getPackageInfo(getApplicationContext().getPackageName(), PackageManager.GET_ACTIVITIES);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        ApplicationInfo test = info.applicationInfo;
        ActivityInfo[] list = info.activities;
//        ActivitiesAdapter activitiesAdapter = new ActivitiesAdapter(this, Arrays.asList(list));
//        binding.activities.setAdapter(activitiesAdapter);
    }
}
