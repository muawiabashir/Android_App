package medilive.sudaapps.net.medilive.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.HashMap;

import medilive.sudaapps.net.medilive.FragmentOne;
import medilive.sudaapps.net.medilive.GridViewAdapter;
import medilive.sudaapps.net.medilive.R;
import medilive.sudaapps.net.medilive.helper.SQLiteHandler;
import medilive.sudaapps.net.medilive.helper.SessionManager;
import medilive.sudaapps.net.medilive.services.AlarmService;

public class MainActivity extends ActionBarActivity implements DrawerLayout.DrawerListener {
    DrawerLayout drawerLayout;
    ListView listView;

    private myAdapter myadaapter;
    public String[] drawer_item;
    private GridViewAdapter gridAdapter;
    private ActionBarDrawerToggle drawerListener;

    TextView username;

    private SQLiteHandler db;
    private SessionManager session;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Set a toolbar to replace the action bar.
        //getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#1b1b1b")));

        startService(new Intent(MainActivity.this, AlarmService.class));
        if (savedInstanceState == null) {
            FragmentManager manager = getFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.flContent, new FragmentOne());
            transaction.commit();
        }


        // thumbview = (ImageView) findViewById(R.id.thumbview);
        session = new SessionManager(getApplicationContext());

        if (!session.isLoggedIn()) {
            logoutUser();
        }
        TextView account_name = (TextView) findViewById(R.id.username);
        //    HashMap<String, String> user = db.getUserDetails();

        //   String name = user.get("email");
        // String email = user.get("email");

        // Displaying the user details on the screen
        //    account_name.setText(name);
        // txtEmail.setText(email);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        listView = (ListView) findViewById(R.id.drawerlist);
        myadaapter = new myAdapter(this);
        drawerListener = new ActionBarDrawerToggle(this, drawerLayout, R.string.drawer_open, R.string.drawer_close) {

            @Override
            public void onDrawerClosed(View drawerView) {
                //   Toast.makeText(MainActivity.this, "Drawer Closed", Toast.LENGTH_LONG).show();
                super.onDrawerClosed(drawerView);

            }

            public void onDrawerOpened(View drawerView) {

                //     Toast.makeText(MainActivity.this, "Drawer Opened", Toast.LENGTH_LONG).show();
                super.onDrawerOpened(drawerView);
            }
        };
        drawerLayout.setDrawerListener(drawerListener);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        drawer_item = getResources().getStringArray(R.array.drawer);
        listView.setAdapter(myadaapter);
        username = (TextView) findViewById(R.id.username);
        // Fetching user details from sqlite
        //  HashMap<String, String> user = db.getUserDetails();

        //  String name = user.get("gender");
        // String email = user.get("email");

        // Displaying the user details on the screen
        //  username.setText(name);
        // txtEmail.setText(email);
        Button btn1 = (Button) findViewById(R.id.btn01);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Extras.class);
                startActivity(i);


            }
        });
        View header = getLayoutInflater().inflate(R.layout.drawer_header, null);
        ImageView pro = (ImageView) header.findViewById(R.id.profile_image);

        //  listView.addHeaderView(header);
        drawerLayout.setDrawerListener(drawerListener);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {


                    // SqLite database handler
                    db = new SQLiteHandler(getApplicationContext());

                    // session manager
                    session = new SessionManager(getApplicationContext());

                    if (!session.isLoggedIn()) {
                        logoutUser();
                    }

                    // Fetching user details from sqlite
                    HashMap<String, String> user = db.getUserDetails();

                    String name = user.get("name");
                    String email = user.get("email");
                    logoutUser();


                }
                if (position == 1) {
                    Intent intent=new Intent(MainActivity.this,Doctor_App.class);
                    startActivity(intent);
                }

                selectedItem(position);
            }
        });


    }

    public void selectedItem(int position) {

        setTitle(drawer_item[position]);
    }

    public void setTitle(String title) {

        getSupportActionBar().setTitle(title);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        if (drawerListener.onOptionsItemSelected(item)) {
            return true;

        }

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerListener.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerListener.onConfigurationChanged(newConfig);
    }

    @Override
    public void onDrawerSlide(View drawerView, float slideOffset) {

    }

    @Override
    public void onDrawerOpened(View drawerView) {
        // username = (TextView) findViewById(R.id.username);

    }

    @Override
    public void onDrawerClosed(View drawerView) {

    }

    @Override
    public void onDrawerStateChanged(int newState) {

    }

    class myAdapter extends BaseAdapter {

        private Context context;
        String[] drawer_item;
        int[] images = {R.mipmap.ic_home, R.mipmap.ic_communities,
                R.mipmap.ic_pages, R.mipmap.ic_people
                , R.mipmap.ic_whats_hot, R.mipmap.ic_action_about, R.mipmap.ic_action_dial_pad
                , R.drawable.ic_account_circle_white_60pt};

        public myAdapter(Context context) {
            drawer_item = context.getResources().getStringArray(R.array.drawer);

        }

        @Override
        public int getCount() {
            return drawer_item.length;
        }

        @Override
        public Object getItem(int position) {
            return drawer_item[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View row = null;


            View itemView = convertView;
            if (itemView == null) {
                itemView = getLayoutInflater().inflate(R.layout.custom_row,
                        parent, false);
            }

            TextView titleTextView = (TextView) itemView.findViewById(R.id.textcus);
            ImageView titleImageView = (ImageView) itemView.findViewById(R.id.imagecus);
            titleTextView.setText(drawer_item[position]);
            titleImageView.setImageResource(images[position]);
            return itemView;
        }
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawers();
            return;
        }
        super.onBackPressed();
    }

    private void logoutUser() {
        session.setLogin(false);

        db.deleteUsers();

        // Launching the login activity
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

}
