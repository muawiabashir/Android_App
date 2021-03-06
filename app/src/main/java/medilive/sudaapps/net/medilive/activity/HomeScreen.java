package medilive.sudaapps.net.medilive.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import medilive.sudaapps.net.medilive.R;
import medilive.sudaapps.net.medilive.fragment.FragmentExtras;
import medilive.sudaapps.net.medilive.fragment.FragmentHome;
import medilive.sudaapps.net.medilive.fragment.FragmentMedicalInformation;
import medilive.sudaapps.net.medilive.helper.SQLiteHandler;
import medilive.sudaapps.net.medilive.helper.SessionManager;

/**
 * Created by Adil on 28/12/2015.
 */
public class HomeScreen extends AppCompatBaseActivity {

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    TextView userNameView,loginLogoutView;
    ActionBarDrawerToggle drawerToggle;
    SQLiteHandler db;
    FrameLayout navigationViewHeader;
    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homescreen);
    }
    @Override
    public void setAppBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public void initViews() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.navigationView);
        navigationViewHeader=(FrameLayout)navigationView.getHeaderView(0);
        userNameView=(TextView)navigationViewHeader.findViewById(R.id.user_profile_name);
        loginLogoutView=(TextView)navigationViewHeader.findViewById(R.id.user_login_logout);
    }

    @Override
    public void initValues() {
        super.initValues();
        db=new SQLiteHandler(this);
        session = new SessionManager(getApplicationContext());
    }

    @Override
    public void initValuesInViews() {

        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, 0, 0);
        drawerLayout.setDrawerListener(drawerToggle);

        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        int id = menuItem.getItemId();
                        new SetAdapterTask(menuItem, id).execute();
                        drawerLayout.closeDrawers();
                        return true;
                    }
                });

        toolbar.setTitle(getResources().getString(R.string.medi_services));
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new FragmentHome(),"FragmentHome").commit();
    }

    private class SetAdapterTask extends AsyncTask<Void, Void, Void> {
        MenuItem menuItem = null;
        int id = 0;

        public SetAdapterTask(MenuItem menuItem, int id) {
            this.menuItem = menuItem;
            this.id = id;
        }

        protected Void doInBackground(Void... params) {
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            switch (id) {
                case R.id.medical_information:
                    setFragmentMedicalInformation(menuItem);
                    break;
                case R.id.medical_service:
                    setFragmentMedicalService(menuItem);
                    break;
                case R.id.extras:
                    setFragmentExtras(menuItem);
                    break;
                default:
                    break;
            }

        }
    }

    private void changeToolbarTitle(MenuItem menuItem){
        String fragmentName = menuItem.getTitle().toString();
        toolbar.setTitle(fragmentName);
    }
    private void setFragmentMedicalInformation(MenuItem menuItem){
        changeToolbarTitle(menuItem);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new FragmentMedicalInformation(),"FragmentMedicalInformation").commit();
    }

    private void setFragmentMedicalService(MenuItem menuItem){
        changeToolbarTitle(menuItem);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new FragmentHome(),"FragmentHome").commit();
    }
    private void setFragmentExtras(MenuItem menuItem){
        changeToolbarTitle(menuItem);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new FragmentExtras(),"FragmentExtras").commit();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return drawerToggle.onOptionsItemSelected(item) ||
                super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            if(session.isLoggedIn()) {
                loginLogoutView.setText(getResources().getString(R.string.log_out_drawer));
                userNameView.setText(session.getUserName());
            }else{
                userNameView.setText("");
            }
        }catch (Exception e){
            userNameView.setText("");
        }
        overridePendingTransition(R.anim.left_in, R.anim.right_out);
    }

    @Override
    public void setOnViewClickListener() {
        super.setOnViewClickListener();
        loginLogoutView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (session.isLoggedIn()) {
                    session.setLogin(false);
                    loginLogoutView.setText(getResources().getString(R.string.btn_login));
                    startActivity(new Intent(HomeScreen.this, LoginActivity.class));
                    userNameView.setText("");
                    drawerLayout.closeDrawers();
                }else {
                    drawerLayout.closeDrawers();
                    startActivity(new Intent(HomeScreen.this, LoginActivity.class));
                }
            }
        });
    }
}
