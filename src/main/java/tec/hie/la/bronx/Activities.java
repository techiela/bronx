package tec.hie.la.bronx;

import android.content.Context;
import android.content.Intent;

import tec.hie.la.bronx.activity.HomeActivity;

public class Activities {
    public static void show(Context context) {
        context.startActivity(new Intent(context, HomeActivity.class));
    }
}
