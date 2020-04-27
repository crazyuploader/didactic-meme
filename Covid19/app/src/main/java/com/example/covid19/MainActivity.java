package com.example.covid19;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    Button buttonFetch;
    TextView tvFetched;
    String country;
    int cases, todayCases, deaths, todayDeaths, recovered;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonFetch = findViewById(R.id.buttonFetch);
        tvFetched = findViewById(R.id.tvFetched);

        String url = "https://corona.lmao.ninja/v2/countries/india";

        final RequestQueue requestQueue = Volley.newRequestQueue(this);

        final StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject reader = new JSONObject(response);
                            country = reader.getString("country");
                            cases = reader.getInt("cases");
                            todayCases = reader.getInt("todayCases");
                            deaths = reader.getInt("deaths");
                            todayDeaths = reader.getInt("todayDeaths");
                            recovered = reader.getInt("recovered");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        tvFetched.setText("Country: " + country + "\n"
                                + "Total Cases: " + cases + "\n"
                                + "Today Cases: " + todayCases + "\n"
                                + "Total Deaths: " + deaths + "\n"
                                + "Today Deaths: " + todayDeaths + "\n"
                                + "Recovered: " + recovered);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                tvFetched.setText("That didn't work!");
            }
        });

                buttonFetch.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        requestQueue.add(stringRequest);
                    }
                });

    }
}