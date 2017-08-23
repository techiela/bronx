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
import tec.hie.la.bronx.databinding.ItemFileBinding;

public class FilesAdapter extends BaseAdapter {

    private ItemFileBinding binding;

    private LayoutInflater layoutInflater;

    private List<File> files = null;

    public FilesAdapter(Context context, List<File> files) {
        this.files = files;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return files.size();
    }

    @Override
    public Object getItem(int position) {
        return files.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            binding = DataBindingUtil.inflate(layoutInflater, R.layout.item_file, parent, false);
            convertView = binding.getRoot();
            convertView.setTag(binding);
        } else {
            binding = (ItemFileBinding) convertView.getTag();
        }
        File file = files.get(position);
        binding.setFile(file);
        binding.setClickListener(new ClickListener());
        return convertView;
    }

    public class ClickListener {
        public void click(View view) {
        }
    }
}
