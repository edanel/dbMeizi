package me.edanel.fulishe.Adapter;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

/**
 * Created by Eggplant on 15-3-26.
 */
public class FragmentAdapter extends Activity {

        private List<Fragment> fragments;
        private FragmentActivity fragmentActivity;
        private int fragmentContentId;
        private int currentTab;
        private ListView listView;
        private openCallBack mCallBack;

        public FragmentAdapter(FragmentActivity fragmentActivity, List<Fragment> fragments, int fragmentContentId,ListView listView) {
        this.fragmentActivity = fragmentActivity;
        this.fragments = fragments;
        this.fragmentContentId = fragmentContentId;
        this.listView = listView;

        listView.setOnItemClickListener(new mOnItemClick());

        FragmentTransaction ft = fragmentActivity.getSupportFragmentManager().beginTransaction();
        ft.add(fragmentContentId, fragments.get(0));
        ft.commit();

    }
        private class mOnItemClick implements AdapterView.OnItemClickListener {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Fragment fragment = fragments.get(position);
                FragmentTransaction ft = fragmentActivity.getSupportFragmentManager().beginTransaction();

                getCurrentFragment().onPause();

                if (fragment.isAdded()) {
                    fragment.onResume();
                } else {
                    ft.add(fragmentContentId, fragment);
                }
                showTab(position);
                ft.commit();
                mCallBack.onListen();
            }
        }
        /**
         * 切换tab
         * @param idx 要显示的tab的id值
         */
    private void showTab(int idx) {
        for (int i = 0; i < fragments.size(); i++) {
            Fragment fragment = fragments.get(i);
            FragmentTransaction ft = fragmentActivity.getSupportFragmentManager().beginTransaction();

            if (idx == i) {
                ft.show(fragment);
            } else {
                ft.hide(fragment);
            }
            ft.commit();
        }
        currentTab = idx; // 更新目标tab为当前tab
    }
    public Fragment getCurrentFragment() {
        return fragments.get(currentTab);
    }

    public static interface openCallBack{
        public void onListen();
    }

    public void setmCallBack(openCallBack mCallBack) {
        this.mCallBack = mCallBack;
    }
}
