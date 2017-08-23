package tec.hie.la.bronx.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import tec.hie.la.bronx.R;
import tec.hie.la.bronx.databinding.ItemDatabaseBinding;
import tec.hie.la.bronx.databinding.ItemFileBinding;

public class DatabasesAdapter extends BaseAdapter {

    private ItemDatabaseBinding binding;

    private LayoutInflater layoutInflater;

    private List<String> databases = null;

    public DatabasesAdapter(Context context, List<String> files) {
        this.databases = files;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return databases.size();
    }

    @Override
    public Object getItem(int position) {
        return databases.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            binding = DataBindingUtil.inflate(layoutInflater, R.layout.item_database, parent, false);
            convertView = binding.getRoot();
            convertView.setTag(binding);
        } else {
            binding = (ItemDatabaseBinding) convertView.getTag();
        }
        String database = databases.get(position);
        binding.setName(database);
        binding.setClickListener(new ClickListener());
        return convertView;
    }

    public class ClickListener {
        public void click(View view) {
        }
    }
}
