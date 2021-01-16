package com.cs.view;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    EditText test;
    private final String url ="http://192.168.1.102/get_data.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        test=findViewById(R.id.test);
    }

    public void OnClick(View view) {
//        Toast.makeText(MainActivity.this,"clicked",Toast.LENGTH_LONG).show();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {

                        try {
//                                Toast.makeText(MainActivity.this,"im in",Toast.LENGTH_LONG).show();
                            //converting the string to json array object
                            JSONArray array = new JSONArray(response);

                            //traversing through all the object
                            for (int i = 0; i < array.length(); i++) {

                                //getting product object from json array
                                JSONObject product = array.getJSONObject(i);
                                test.setText((String)product.get("name"));
                               // adding the product to product list
//                                    productList.add(new Product(
//                                           (int)product.get("id"),
//                                            (String)product.get("title"),
//                                            (String)product.get("shortdesc"),
//                                            (Double)product.get("rating"),
//                                            (Double)product.get("price"),
//                                            (String)product.get("image")
//                                    ))
                            }

                            //creating adapter object and setting it to recyclerview
//                                ProductsAdapter adapter = new ProductsAdapter(MainActivity.this, productList);
//                                recyclerView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this,error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });

        //adding our stringrequest to queue
        Volley.newRequestQueue(this).add(stringRequest);
    }
}