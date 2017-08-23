/*
* Copyright 2013 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package tec.hie.la.bronx.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import tec.hie.la.bronx.fragment.ActivitiesFragment;
import tec.hie.la.bronx.fragment.DatabasesFragment;
import tec.hie.la.bronx.fragment.FilesFragment;
import tec.hie.la.bronx.fragment.SharedPreferencesFragment;

/**
 * A basic sample which shows how to use {@link SlidingTabLayout}
 * to display a custom {@link ViewPager} title strip which gives continuous feedback to the user
 * when scrolling.
 */
public class TabsFragmentAdapter extends FragmentPagerAdapter {

    private final int Num_Tab = 4;

    /**
     * コンストラクタ
     *
     * @param fm
     */
    public TabsFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    /**
     * ページャーに表示する画面を取得
     *
     * @param position
     * @return
     */
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return ActivitiesFragment.newInstance(position);
            case 1:
                return FilesFragment.newInstance(position);
            case 2:
                return DatabasesFragment.newInstance(position);
            case 3:
            default:
                return SharedPreferencesFragment.newInstance(position);
        }
    }

    /**
     * タブの数を取得
     *
     * @return
     */
    @Override
    public int getCount() {
        return Num_Tab;
    }

    /**
     * タブに表示する文字列を取得
     *
     * @param position
     * @return
     */
    @Override
    public CharSequence getPageTitle(int position) {
        // TODO enum
        switch (position) {
            case 0:
                return "activity";
            case 1:
                return "file";
            case 2:
                return "sqlite";
            case 3:
            default:
                return "sharedprefs";
        }
    }

//    /**
//     * タブのアイコンを取得
//     *
//     * @param position
//     * @return
//     */
//    public Drawable getTabIcon(int position) {
//        int icon = R.drawable.ic_notify;
//        switch (position) {
//            case TabConstant.HOME:
//                icon = R.drawable.icon_home_off;
//                break;
//            case TabConstant.CAMPAIGN_LIST:
//                icon = R.drawable.icon_point_off;
//                break;
//            case TabConstant.POINT_DETAIL:
//                icon = R.drawable.icon_points_off;
//        }
//        return KujiApplication.getContext().getResources().getDrawable(icon);
//    }
//
//    /**
//     * アクティブなタブのアイコンを取得
//     *
//     * @param position
//     * @return
//     */
//    public Drawable getActiveTabIcon(int position) {
//        int icon = R.drawable.ic_notify;
//        switch (position) {
//            case TabConstant.HOME:
//                icon = R.drawable.icon_home_on;
//                break;
//            case TabConstant.CAMPAIGN_LIST:
//                icon = R.drawable.icon_point_on;
//                break;
//            case TabConstant.POINT_DETAIL:
//                icon = R.drawable.icon_points_on;
//        }
//        return KujiApplication.getContext().getResources().getDrawable(icon);
//    }
}

