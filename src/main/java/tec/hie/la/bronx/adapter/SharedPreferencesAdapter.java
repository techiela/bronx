package tec.hie.la.bronx.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.io.File;
import java.util.List;

import tec.hie.la.bronx.R;
import tec.hie.la.bronx.databinding.ItemSharedPreferencesBinding;

public class SharedPreferencesAdapter extends BaseAdapter {

    private ItemSharedPreferencesBinding binding;

    private LayoutInflater layoutInflater;

    private List<File> sharedPreferences = null;

    public SharedPreferencesAdapter(Context context, List<File> sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return sharedPreferences.size();
    }

    @Override
    public Object getItem(int position) {
        return sharedPreferences.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            binding = DataBindingUtil.inflate(layoutInflater, R.layout.item_shared_preferences, parent, false);
            convertView = binding.getRoot();
            convertView.setTag(binding);
        } else {
            binding = (ItemSharedPreferencesBinding) convertView.getTag();
        }
        File preference = sharedPreferences.get(position);
        binding.setSharedPreference(preference);
        binding.setClickListener(new ClickListener());
        return convertView;
    }

    public class ClickListener {
        public void click(View view) {
        }
    }
}
