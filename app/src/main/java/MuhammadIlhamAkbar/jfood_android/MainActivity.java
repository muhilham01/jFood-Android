package MuhammadIlhamAkbar.jfood_android;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * <h1>JFood-Android Program based on Object Oriented Programming<h1>
 * This MainActivity Class is used to process main activity.
 * MainActivity is subclass of an AppCompatActivity class.
 * <p>
 * @author Muhammad Ilham Akbar
 * @version 2020-06-06
 */

public class MainActivity extends AppCompatActivity {
    SessionManager session;
    private int currentUserId;
    private int currentInvoiceId;
    private ArrayList<Seller> listSeller = new ArrayList<>();
    private ArrayList<Food> foodIdList = new ArrayList<>();
    private HashMap<Seller, ArrayList<Food>> childMapping = new HashMap<>();
    MainListAdapter listAdapter;
    ExpandableListView expandableListView;
    EditText et_search;
    private String search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        session = new SessionManager(this);
        expandableListView = findViewById(R.id.lvExp);
        final Button pesanan = (Button) findViewById(R.id.pesanan);
        final Button btnLogout = findViewById(R.id.btnLogout);
        currentUserId = session.getUserId();
        if(getIntent().getExtras()!=null)
        {
            Intent intent = getIntent();
            currentUserId = intent.getIntExtra("currentUserId", 0);
            currentInvoiceId = intent.getIntExtra("currentInvoiceId", 0);
        }

        refreshList();

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener(){
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Food select = childMapping.get(listSeller.get(groupPosition)).get(childPosition);
                Intent buatPesananIntent = new Intent(MainActivity.this, BuatPesananActivity.class);
                buatPesananIntent.putExtra("currentUserId", session.getUserId());
                buatPesananIntent.putExtra("id_food", select.getId());
                buatPesananIntent.putExtra("foodName", select.getName());
                buatPesananIntent.putExtra("foodCategory", select.getCategory());
                buatPesananIntent.putExtra("foodPrice", select.getPrice());
                startActivity(buatPesananIntent);
                return false;
            }
        });

        pesanan.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonResponse = new JSONArray(response);
                            if (jsonResponse != null) {
                                Intent mainIntent = new Intent(MainActivity.this, SelesaiPesananActivity.class);
                                for (int i = 0; i < jsonResponse.length(); i++)
                                {
                                    JSONObject invoice = jsonResponse.getJSONObject(i);
                                    currentInvoiceId = invoice.getInt("id");
                                }
                                mainIntent.putExtra("currentUserId", session.getUserId());
                                mainIntent.putExtra("currentInvoiceId", currentInvoiceId);
                                startActivity(mainIntent);
                            }
                        } catch (JSONException e) {
                            Toast.makeText(MainActivity.this, "JSON FAILED", Toast.LENGTH_LONG).show();
                        }
                    }
                };
                PesananFetchRequest pesananFetchRequest = new PesananFetchRequest(currentUserId, responseListener);
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                queue.add(pesananFetchRequest);

            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                showSignoutDialog();
            }
        });

    }


    protected void refreshList() {
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonResponse = new JSONArray(response);
                    if (jsonResponse != null) {
                        for (int i = 0; i < jsonResponse.length(); i++) {
                            JSONObject food = jsonResponse.getJSONObject(i);
                            JSONObject seller = food.getJSONObject("seller");
                            JSONObject location = seller.getJSONObject("location");

                            String province = location.getString("province");
                            String description = location.getString("description");
                            String city = location.getString("city");
                            Location location1 = new Location(province, description, city);

                            int id = seller.getInt("id");
                            String name = seller.getString("name");
                            String email = seller.getString("email");
                            String phoneNumber = seller.getString("phoneNumber");
                            Seller seller1 = new Seller(id, name, email, phoneNumber, location1);
                            if (listSeller.size() > 0) {
                                boolean success = true;
                                for (Seller sel : listSeller)
                                    if (sel.getId() == seller1.getId())
                                        success = false;
                                if (success) {
                                    listSeller.add(seller1);
                                }
                            } else {
                                listSeller.add(seller1);
                            }
                            int idFood = food.getInt("id");
                            String nameFood = food.getString("name");
                            int price = food.getInt("price");
                            String category = food.getString("category");
                            Food food1 = new Food(idFood, nameFood, seller1, price, category);
                            foodIdList.add(food1);

                        }
                        for(Seller sel : listSeller) {
                            ArrayList<Food> temp = new ArrayList<>();
                            for(Food food2 : foodIdList){
                                if(food2.getSeller().getName().equals(sel.getName()) && food2.getSeller().getEmail().equals(sel.getEmail()) && food2.getSeller().getPhoneNumber().equals(sel.getPhoneNumber())){
                                    temp.add(food2);
                                }
                                childMapping.put(sel,temp);
                            }

                        }
                    }
                } catch (JSONException e) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setMessage("Main Failed").create().show();
                }
                listAdapter = new MainListAdapter(MainActivity.this, listSeller, childMapping);
                expandableListView.setAdapter(listAdapter);
            }
        };

        MenuRequest menuRequest = new MenuRequest(responseListener);
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        queue.add(menuRequest);
    }



    private void showSignoutDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Are you sure?");
        LinearLayout linearLayout = new LinearLayout(this);
        final TextView confirmationSignout =new TextView(this);
        linearLayout.addView(confirmationSignout);
        builder.setView(linearLayout);

        //OK Button
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                session.logoutUser();
                Intent loginActivity = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(loginActivity);
                finish();
            }
        });

        //Cancel Button
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
        super.onBackPressed();
    }

}
