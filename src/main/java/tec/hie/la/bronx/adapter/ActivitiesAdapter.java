package tec.hie.la.bronx.adapter;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import tec.hie.la.bronx.R;
import tec.hie.la.bronx.databinding.ItemActivityBinding;

public class ActivitiesAdapter extends BaseAdapter {

    private ItemActivityBinding binding;

    private LayoutInflater layoutInflater;

    private List<ActivityInfo> activities = null;

    public ActivitiesAdapter(Context context, List<ActivityInfo> activities) {
        this.activities = activities;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return activities.size();
    }

    @Override
    public Object getItem(int position) {
        return activities.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            binding = DataBindingUtil.inflate(layoutInflater, R.layout.item_activity, parent, false);
            convertView = binding.getRoot();
            convertView.setTag(binding);
        } else {
            binding = (ItemActivityBinding) convertView.getTag();
        }
        ActivityInfo activityInfo = activities.get(position);
        binding.setActivityInfo(activityInfo);
        binding.setClickListener(new ClickListener());
        return convertView;
    }

    public class ClickListener {
        public void click(View view) {
            PackageInfo info = null;
            try {
                // TODO use application context
                PackageManager packageManager = layoutInflater.getContext().getPackageManager();
                info = packageManager.getPackageInfo(layoutInflater.getContext().getPackageName(), PackageManager.GET_ACTIVITIES);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
            Intent intent = new Intent();
            intent.setComponent(new ComponentName(info.applicationInfo.packageName, ((TextView) view).getText().toString()));
            layoutInflater.getContext().startActivity(intent);
        }
    }
}
