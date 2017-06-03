package com.example.android.myrestaurant;

import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private UserTABLE objUserTABLE;
    private OrderTABLE objOrderTABLE;
    private EditText editUser,editPwd;
    private String strUserchoose,strPwdchoose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //bindWidget
        bindWidget();

        objUserTABLE = new UserTABLE(this);
        objOrderTABLE = new OrderTABLE(this);

        //Tester
        //testAddValue();

        //deleteAlldata
        deleteAllData();

        //synJSONToSQLite
        synJSONToSQLite();
    }

    private void deleteAllData() {
        SQLiteDatabase objSQLite = openOrCreateDatabase("Restaurant.db",MODE_PRIVATE,null);
        objSQLite.delete("userTABLE",null,null);
    }

    public void clickLogin(View view) {

        strUserchoose = editUser.getText().toString().trim();
        strPwdchoose = editPwd.getText().toString().trim();

        if (strUserchoose.equals("")||strPwdchoose.equals("")) {

            MyAlertDialog objMyAlert = new MyAlertDialog();
            objMyAlert.errorDialog(MainActivity.this,"Have space","Please fill All blank");

        } else {

        } // if
    }

    private void bindWidget() {
        editUser = (EditText) findViewById(R.id.editUser);
        editPwd = (EditText) findViewById(R.id.editPwd);
    }//binding widget


    private void synJSONToSQLite() {
        //setUp policy
        if (Build.VERSION.SDK_INT>9) {
            StrictMode.ThreadPolicy myPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(myPolicy);
        }

        InputStream objInputstream = null;
        String strJSON = "";

        URL url;
        HttpURLConnection urlConnection = null;

        //1.Create objInputstream
        try {

            url = new URL("http://192.168.83.1:81/rest/php_get_data.php");
            urlConnection = (HttpURLConnection) url.openConnection();
            objInputstream = urlConnection.getInputStream();

        } catch (Exception e) {
            Log.d("Restaurant", "Error from inputStream ==>" + e.toString());
        }

        //2.Change InputStream to String
        try {

            BufferedReader objBufferedReader = new BufferedReader(new InputStreamReader(objInputstream, "UTF-8"));
            StringBuilder objStringBuilder = new StringBuilder();
            String strLine = null;

            while ((strLine = objBufferedReader.readLine()) != null) {
                objStringBuilder.append(strLine);
            }

            objInputstream.close();
            strJSON = objStringBuilder.toString();

        } catch (Exception e) {
            Log.d("Restaurant", "Error from Create String ==>" + e.toString());
        }

        //3.Up Value to SQLite
        try {

            final JSONArray objJSONArray = new JSONArray(strJSON);
            for (int i = 0; i < objJSONArray.length(); i++) {
                JSONObject objJSONObject = objJSONArray.getJSONObject(i);
                String strUser = objJSONObject.getString("User");
                String strPassword = objJSONObject.getString("Password");
                String strOfficer = objJSONObject.getString("Officer");

                long inserValue = objUserTABLE.addValueToUser(strUser,strPassword,strOfficer);
            }

        } catch (Exception e) {
            Log.d("Restaurant", "Error from Up value ==>"+ e.toString());
        }

    }//synJsonToSQLite

    private void testAddValue() {
        objOrderTABLE.addValueToOrder("Officer","Date","Food",4);
        objUserTABLE.addValueToUser("User","Password","Officer");
    }
}
