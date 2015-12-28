package medilive.sudaapps.net.medilive.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import medilive.sudaapps.net.medilive.R;

/**
 * Created by Adil on 28/12/2015.
 */
public class HomeScreen extends AppCompatBaseActivity {

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    TextView userNameView,loginLogoutView;
    ActionBarDrawerToggle drawerToggle;

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
        super.initViews();
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.navigationView);
        userNameView=(TextView)findViewById(R.id.user_profile_name);
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

        toolbar.setTitle("Medical Information");
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
    }

    private void setFragmentMedicalService(MenuItem menuItem){
        changeToolbarTitle(menuItem);
    }
    private void setFragmentExtras(MenuItem menuItem){
        changeToolbarTitle(menuItem);
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
}
