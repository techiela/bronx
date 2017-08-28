package tec.hie.la.bronx.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.TextView;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import tec.hie.la.bronx.R;
import tec.hie.la.bronx.adapter.IntentExtrasAdapter;
import tec.hie.la.bronx.databinding.ActivitySendIntentBinding;
import tec.hie.la.bronx.dto.IntentExtraDto;

public class SendIntentActivity extends FragmentActivity {

    private ActivitySendIntentBinding binding;

    private IntentExtrasAdapter intentExtrasAdapter;

    private List<IntentExtraDto> intentExtraDtos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_send_intent);

        binding.setActivity(getIntent().getStringExtra("activity"));
        binding.setClickListener(new ClickListener());

        showExtra();
    }

    private void showExtra() {
        intentExtrasAdapter = new IntentExtrasAdapter(this, intentExtraDtos);
        binding.intents.setAdapter(intentExtrasAdapter);
    }

    private Intent putExtra(Intent intent, IntentExtraDto intentExtraDto) {
        switch (intentExtraDto.type) {
            case STRING:
                intent.putExtra(intentExtraDto.key, intentExtraDto.value);
                break;
            case INT:
                intent.putExtra(intentExtraDto.key, Integer.parseInt(intentExtraDto.value));
                break;
            case BOOLEAN:
                intent.putExtra(intentExtraDto.key, Boolean.valueOf(intentExtraDto.value));
                break;
            case STRING_ARRAY:
                intent.putExtra(intentExtraDto.key, new ArrayList<>(Arrays.asList(intentExtraDto.value.split(","))));
                break;
            case INT_ARRAY:
                intent.putExtra(intentExtraDto.key, new ArrayList<>(Stream.of(intentExtraDto.value.split(",")).map(Integer::valueOf).toList()));
                break;
            case BOOLEAN_ARRAY:
                intent.putExtra(intentExtraDto.key, new ArrayList<>(Stream.of(intentExtraDto.value.split(",")).map(Boolean::valueOf).toList()));
                break;
            default:
        }
        return intent;
    }

    public class ClickListener {

        public void addExtra(View view) {
            intentExtraDtos.add(new IntentExtraDto());
            intentExtrasAdapter.setExtras(intentExtraDtos);
            intentExtrasAdapter.notifyDataSetChanged();
        }

        public void start(String activity) {
            PackageInfo info = null;
            try {
                PackageManager packageManager = SendIntentActivity.this.getPackageManager();
                info = packageManager.getPackageInfo(SendIntentActivity.this.getPackageName(), PackageManager.GET_ACTIVITIES);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }

            Intent intent = new Intent();
            intent.setComponent(new ComponentName(info.applicationInfo.packageName, activity));
            Stream.of(intentExtraDtos).forEach((intentExtraDto) -> putExtra(intent, intentExtraDto));
            SendIntentActivity.this.startActivity(intent);
        }
    }
}
