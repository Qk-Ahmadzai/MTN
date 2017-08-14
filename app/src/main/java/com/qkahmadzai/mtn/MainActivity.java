package com.qkahmadzai.mtn;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.*;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.qkahmadzai.mtn.Adpaters.InternetBundles_Rv_Adapter;
import com.qkahmadzai.mtn.Database.DBHelper;
import com.qkahmadzai.mtn.Database.Packages;
import com.qkahmadzai.mtn.Fragments.MainFragment;
import com.qkahmadzai.mtn.Fragments.SMSBUndles;
import com.qkahmadzai.mtn.Fragments.SocialBundles;
import com.qkahmadzai.mtn.Fragments.VoiceAndSMSBundles;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.List;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private String mJSONURLString = "http://qkahmadzai.com/remort/api/index.php";
    TextView txtDisplay;
    DBHelper db;
    String _data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


       //reQuestData();
       //reQuestJsonData();
///////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content_frame, new MainFragment());
        ft.commit();
////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////


        db = new DBHelper(this);

     //   txtDisplay = (TextView) findViewById(R.id.textView2);


        //readJson();

      //  reQuestData();
      //  reQuestJsonData();
        List<Packages> pk = db.getAllPackages();
        Log.i("DB", "PK Size : "+pk.size());

        for (int i=0; i<pk.size(); i++){
            Packages _pk = pk.get(i);
            _data += "ID : "+_pk.getId()+" PT : "+_pk.getBundleType()+" V : "+_pk.getVolume()+" VD : "+_pk.getValidation()+"\n";
            Log.i("volly", "Data from DB : "+_data);
        }


       // txtDisplay.setText(""+_data);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        int id = item.getItemId();

        if (id == R.id.nav_Main_Fragment) {
            ft.replace(R.id.content_frame, new MainFragment());

        } else if (id == R.id.nav_Internet_Bundles) {
            ft.replace(R.id.content_frame, new VoiceAndSMSBundles());

        } else if (id == R.id.nav_Voice_And_SMS) {
            ft.replace(R.id.content_frame, new VoiceAndSMSBundles());

        } else if (id == R.id.nav_SMS_Bundles) {
            ft.replace(R.id.content_frame, new SMSBUndles());

        } else if (id == R.id.nav_Social_Bundles) {
            ft.replace(R.id.content_frame, new SocialBundles());

        } else if (id == R.id.nav_share) {
            reQuestJsonData();
        } else if (id == R.id.nav_send) {

        }

        ft.commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public boolean reQuestData(){


        RequestQueue mRequestQueue;
        // Instantiate the cache
        Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024); // 1MB cap
        Network network = new BasicNetwork(new HurlStack());
        // Instantiate the RequestQueue with the cache and network.
        mRequestQueue = new RequestQueue(cache, network);

        // Start the queue

        mRequestQueue.start();
        String url ="http://qkahmadzai.com/remort/api/index.php";
        // Formulate the request and handle the response.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("vollay", "" + response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("vollay", "" + error);
                    }
                });

        // Add the request to the RequestQueue.
        mRequestQueue.add(stringRequest);

        return true;
    }

    public boolean reQuestJsonData(){

        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);


            // Initialize a new JsonObjectRequest instance
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                mJSONURLString,
                null,
                new Response.Listener<JSONObject>() {
                    Packages pkg = new Packages();
                    @Override
                    public void onResponse(JSONObject response) {

                       int counter=0;
                        try {

                            JSONArray jsonArray = response.getJSONArray("internetbundles");

                            if(db.numberOfInternetPackages() < jsonArray.length()) {
                                int ii = (int)db.numberOfInternetPackages();
                                    for (int i=ii; i < jsonArray.length(); i++) {
                                        JSONObject jo_inside = jsonArray.getJSONObject(i);

                                        pkg.setId(jo_inside.getInt("id"));
                                        pkg.setBundleType(jo_inside.getString("bundletype"));
                                        pkg.setVolume(jo_inside.getString("volume"));
                                        pkg.setValidation(jo_inside.getString("validity"));
                                        pkg.setPrice(jo_inside.getString("bundlecharges"));
                                        pkg.setSubMethod(jo_inside.getString("subcription"));

                                         db.insertInternetBundle(pkg);
                                        Log.i("Vollay", "Data inserted"+ db.numberOfInternetPackages());
                                        counter++;
                                    }

                            }else {
                                Log.i("DB", " - " + "They are equal sixe");
                            }
                        }catch (Exception js){
                            Log.i("vollay", "Json Exception : " + js);
                        }finally {
                            Toast.makeText(MainActivity.this, "Add New Rec : "+counter, Toast.LENGTH_LONG).show();
                        }

                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        Log.i("vollay", "Json VolleyError : " + error);
                    }
                }
        );

        // Add JsonObjectRequest to the RequestQueue
        requestQueue.add(jsonObjectRequest);

        return true;
    }





    public void readJson(){

        String jsonString;
        InputStream jArray = getResources().openRawResource(R.raw.myjson);
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(jArray, "UTF-8"));
            String line = reader.readLine();
            while (line != null) {
                writer.write(line);
                line = reader.readLine();
            }
        } catch (Exception e) {
            Log.e("TAG", "Unhandled exception while using JSONResourceReader", e);
        } finally {
            try {
                jArray.close();
            } catch (Exception e) {
                Log.e("TAG", "Unhandled exception while using JSONResourceReader", e);
            }
        }

        jsonString = writer.toString();
        String _country="";// = jsonObject.getString("country");
        String _sunrise="";// = jsonObject.getString("sunrise");
        String _sunset="";// = jsonObject.getString("sunset");
        Packages pkg = new Packages();
        Log.i("DB", " - " + "db.numberOfInternetPackages() "+ db.numberOfInternetPackages());

        try{
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONArray jArr = jsonObject.getJSONArray("internetbundle");
            Log.i("DB", " - " + "jArr "+ jArr.length());
            /*Toast.makeText(MainActivity.this, "Cal : "+ "jArr : "+jArr.length()+" DB : "+ db.numberOfInternetPackages()+" jDB : "+
                            (jArr.length() - (int)db.numberOfInternetPackages()),
                    Toast.LENGTH_LONG).show();*/

            if(db.numberOfInternetPackages() < jArr.length()) {
                int ii = (int)db.numberOfInternetPackages();
                for (int i=ii; i <= jArr.length(); i++) {

                        JSONObject jo_inside = jArr.getJSONObject(i);
                        pkg.setId(jo_inside.getInt("id"));
                        pkg.setBundleType(jo_inside.getString("bundleType"));
                        pkg.setVolume(jo_inside.getString("volume"));
                        pkg.setValidation(jo_inside.getString("validation"));
                        pkg.setPrice(jo_inside.getString("price"));
                        pkg.setSubMethod(jo_inside.getString("subMethod"));

                        db.insertInternetBundle(pkg);
                        Log.i("DB", " - " + _sunrise);
                }
            }else {
                Log.i("DB", " - " + "They are equal sixe");
            }

        } catch (Exception e) {
            Log.i("DB", " - "+ e);
        }
    }











}
