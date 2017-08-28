package tec.hie.la.bronx.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import tec.hie.la.bronx.R;
import tec.hie.la.bronx.constant.IntentExtraTypeEnum;
import tec.hie.la.bronx.databinding.ItemIntentExtraBinding;
import tec.hie.la.bronx.dto.IntentExtraDto;

public class IntentExtrasAdapter extends BaseAdapter {

    private ItemIntentExtraBinding binding;

    private LayoutInflater layoutInflater;

    private List<IntentExtraDto> extras = null;

    public IntentExtrasAdapter(Context context, List<IntentExtraDto> intentExtraDtos) {
        this.extras = intentExtraDtos;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setExtras(List<IntentExtraDto> extras) {
        this.extras = extras;
    }

    @Override
    public int getCount() {
        return extras.size();
    }

    @Override
    public Object getItem(int position) {
        return extras.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            binding = DataBindingUtil.inflate(layoutInflater, R.layout.item_intent_extra, parent, false);
            convertView = binding.getRoot();
            convertView.setTag(binding);
        } else {
            binding = (ItemIntentExtraBinding) convertView.getTag();
        }
        binding.setExtra(extras.get(position));
        binding.type.setAdapter(new ArrayAdapter<>(layoutInflater.getContext(), R.layout.item_spinner, IntentExtraTypeEnum.values()));
        binding.type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                extras.get(position).type = IntentExtraTypeEnum.of(((TextView) view).getText().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        return convertView;
    }
}
