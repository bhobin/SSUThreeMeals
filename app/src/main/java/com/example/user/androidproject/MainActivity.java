package com.example.user.androidproject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.example.user.androidproject.fragment.Fragment1;
import com.example.user.androidproject.fragment.Fragment2;
import com.example.user.androidproject.fragment.Fragment3;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;

//http://swalloow.tistory.com/48
public class MainActivity extends AppCompatActivity{

    AQuery aq = new AQuery(this);

    private ProgressBar progressBar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ImageView menuButton;
    boolean isPageOpen = true;
    private ArrayList<StoreData> array;

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    View dialogView;

    private long lastTimeBackPressed;

    private Bundle bundle;

    private int[] selectedDrawable = {
            R.drawable.icon_tab1_selected,
            R.drawable.icon_tab2_selected,
            R.drawable.icon_tab3_selected
    };

    private int[] unselectedDrawable = {
            R.drawable.icon_tab1_unselected,
            R.drawable.icon_tab2_unselected,
            R.drawable.icon_tab3_unselected
    };

    int i = 0;


    private static final String TAG = "MainActivity";
    @SuppressLint("LongLogTag")
    public static final String getKeyHash(Context context) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(),
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String keyHash = Base64.encodeToString(md.digest(), Base64.DEFAULT);
                Log.e(TAG + "KeyHash:%s", keyHash);
                return keyHash;
            }

        } catch (PackageManager.NameNotFoundException e) {
            Log.d(TAG + "getKeyHash Error:%s", e.getMessage());
        } catch (NoSuchAlgorithmException e) {
            Log.d(TAG + "getKeyHash Error:%s", e.getMessage());
        }
        return "";
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getKeyHash(getApplicationContext());
        initLayout();

        array = new ArrayList<StoreData>();
        bundle = new Bundle();

        for (i = 0; i < 40; i++) {

            HashMap<String, Object> map = new HashMap<>();
            map.put("index", i);
            aq.ajax("http://letsgo.woobi.co.kr/hobin/store.php", map, JSONObject.class, new AjaxCallback<JSONObject>() {
                @Override
                public void callback(String url, JSONObject object, AjaxStatus status) {
                    //Log.d("response=", object.toString());
                    if (object != null) {
                        try {
                            //Toast.makeText(MainActivity.this, "error1", Toast.LENGTH_SHORT).show();
                            if (object.getString("result").equals("success")) {
                                //Toast.makeText(MainActivity.this, "error2", Toast.LENGTH_SHORT).show();
                                String id = object.getString("id");
                                String category = object.getString("category");
                                String title = object.getString("title");
                                String time = object.getString("time");
                                String information = object.getString("information");
                                String number = object.getString("number");
                                String like_count = object.getString("like_count");
                                String location = object.getString("location");
                                String report = object.getString("report");
                                String like_id = object.getString("like_id");
                                String image = object.getString("image");


                                array.add(new StoreData(Integer.parseInt(id), category, title, time, information, number,
                                        Integer.parseInt(like_count), false, location, Integer.parseInt(report), image));

                                if (array.size() == 40){

                                    progressBar = (ProgressBar) findViewById(R.id.progressBar);
                                    progressBar.setVisibility(View.INVISIBLE);

                                    bundle.putParcelableArrayList("data",array);

                                    setTabIcon(tabLayout.getSelectedTabPosition());
                                    tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.icon_tab1_selected));
                                    tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.icon_tab2_unselected));
                                    tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.icon_tab3_unselected));
                                    tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);


                                    // Creating TabPagerAdapter adapter
                                    TabPagerAdapter pagerAdapter = new TabPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
                                    viewPager.setAdapter(pagerAdapter);
                                    viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

                                    // Set TabSelectedListener
                                    tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

                                        @Override
                                        public void onTabSelected(TabLayout.Tab tab) {
                                            viewPager.setCurrentItem(tab.getPosition());
                                            setTabIcon(tab.getPosition());
                                        }

                                        @Override
                                        public void onTabReselected(TabLayout.Tab tab) {

                                        }

                                        @Override
                                        public void onTabUnselected(TabLayout.Tab tab) {
                                        }
                                    });
                                }
                            }
                        } catch (JSONException e) {
                            Toast.makeText(MainActivity.this, "JSON 예외", Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "통신실패", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.view_pager);



        final FloatingActionsMenu menuMultipleActions = (FloatingActionsMenu) findViewById(R.id.multiple_actions);
        final FloatingActionButton actionA = (FloatingActionButton) findViewById(R.id.action_a);
        actionA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PayChoiceActivity.class);
                startActivity(intent);
            }
        });

        final FloatingActionButton actionB = (FloatingActionButton) findViewById(R.id.action_b);
        actionB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RandomChoiceActivity.class);
                startActivity(intent);
            }
        });


        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        menuButton = (ImageView) findViewById(R.id.button_main_menu);
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPageOpen) {
                    drawerLayout.openDrawer(GravityCompat.START);
                    isPageOpen = false;
                } else {
                    drawerLayout.closeDrawer(GravityCompat.START);
                    isPageOpen = true;
                }

            }

        });

    }


    private void setTabIcon(int selected) {
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            if (i == selected) {
                tabLayout.getTabAt(i).setIcon(selectedDrawable[i]);
            } else {
                tabLayout.getTabAt(i).setIcon(unselectedDrawable[i]);
            }
        }
    }


    class TabPagerAdapter extends FragmentStatePagerAdapter {
        private int tabCount;

        public TabPagerAdapter(FragmentManager fm, int tabCount) {
            super(fm);
            this.tabCount = tabCount;
        }

        @Override
        public Fragment getItem(int position) {

            // Returning the current tabs
            switch (position) {
                case 0:
                    Fragment1 fragment1 = new Fragment1();
                    fragment1.setArguments(bundle);
                    return fragment1;
                case 1:
                    Fragment2 fragment2 = new Fragment2();
                    fragment2.setArguments(bundle);
                    return fragment2;
                case 2:
                    Fragment3 fragment3 = new Fragment3();
                    fragment3.setArguments(bundle);
                    return fragment3;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return tabCount;
        }
    }


    private void initLayout(){

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        navigationView = (NavigationView) findViewById(R.id.navigationView);

        navigationView.setNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private NavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new NavigationView.OnNavigationItemSelectedListener(){

        @Override
        public boolean onNavigationItemSelected(MenuItem item) {


            int id = item.getItemId();

            if (id== R.id.menu1){
                Intent intent = new Intent(getApplicationContext(), NoticeActivity.class);
                startActivity(intent);
            }
            else if (id == R.id.menu2){
                dialogView = getLayoutInflater().inflate(R.layout.dialog_make, null);
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setView(dialogView);
                AlertDialog dialog = builder.create();
                dialog.show();
            }
            else if (id == R.id.menu3){
                Intent intent = new Intent(getApplicationContext(), SettingActivity.class);
                startActivity(intent);

            }

            drawerLayout.closeDrawer(GravityCompat.START);
            return true;

        }
    };


    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - lastTimeBackPressed < 1500) {
            finish();
            return;
        }
        Toast.makeText(this, "'뒤로' 버튼을 한번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show();
        lastTimeBackPressed = System.currentTimeMillis();
    }
}