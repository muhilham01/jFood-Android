package MuhammadIlhamAkbar.jfood_android;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * <h1>JFood-Android Program based on Object Oriented Programming<h1>
 * This BuatPesananActivity Class is used to precessing order activity.
 * BuatPesananActivity is subclass of an AppCompatActivity class.
 * <p>
 * @author Muhammad Ilham Akbar
 * @version 2020-06-06
 */

public class BuatPesananActivity extends AppCompatActivity {
    Intent intent = getIntent();
    private int currentUserId;
    private int id_food = 0;
    private String foodName = "0";
    private String foodCategory = "0";
    private double foodPrice = 0;
    private int deliveryFee = 4000;
    private String promoCode = "0";
    private String selectedPayment = "CASH";
    private int promoDiscount;
    private int promoMinPrice;
    private boolean promoActive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buat_pesanan);
        final TextView textCode = (TextView) findViewById(R.id.textCode);
        final TextView staticDeliveryFee = (TextView) findViewById(R.id.staticDeliveryFee);
        final TextView food_name = (TextView) findViewById(R.id.food_name);
        final TextView food_category = (TextView) findViewById(R.id.food_category);
        final TextView food_price = (TextView) findViewById(R.id.food_price);
        final TextView delivery_fee = (TextView) findViewById(R.id.delivery_fee);
        final EditText promo_code = (EditText) findViewById(R.id.promo_code);
        final TextView total_price = (TextView) findViewById(R.id.total_price);
        final RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        final Button hitung = (Button) findViewById(R.id.hitung);
        final Button pesan = (Button) findViewById(R.id.pesan);

        if(getIntent().getExtras()!=null)
        {
            Intent intent = getIntent();
            currentUserId = intent.getIntExtra("currentUserId", 0);
            id_food = intent.getIntExtra("id_food", 0);
            foodName = intent.getStringExtra("foodName");
            foodCategory = intent.getStringExtra("foodCategory");
            foodPrice = intent.getIntExtra("foodPrice", 0);
        }

        final LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params1.setMargins(0,335,0,0);
        delivery_fee.setLayoutParams(params1);

        final LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(0,50,0,0);
        total_price.setLayoutParams(params);

        pesan.setVisibility(View.GONE);
        textCode.setVisibility(View.GONE);
        promo_code.setVisibility(View.GONE);

        food_name.setText(foodName);
        food_category.setText(foodCategory);
        food_price.setText("Rp " + Double.toString(foodPrice));
        total_price.setText("0");
        delivery_fee.setText("Rp " + Integer.toString(deliveryFee));

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.cash)
                {
                    promo_code.setVisibility(View.GONE);
                    textCode.setVisibility(View.GONE);
                    staticDeliveryFee.setVisibility(View.VISIBLE);
                    delivery_fee.setVisibility(View.VISIBLE);
                    params1.setMargins(0,335,0,0);
                    delivery_fee.setLayoutParams(params1);
                    params.setMargins(0,50,0,0);
                    total_price.setLayoutParams(params);
                    selectedPayment = "cash";
                }
                else if(checkedId == R.id.cashless)
                {
                    promo_code.setVisibility(View.VISIBLE);
                    textCode.setVisibility(View.VISIBLE);
                    staticDeliveryFee.setVisibility(View.GONE);
                    delivery_fee.setVisibility(View.GONE);
                    params.setMargins(0,68,0,0);
                    total_price.setLayoutParams(params);
                    selectedPayment = "cashless";
                }
                hitung.setVisibility(View.VISIBLE);
                pesan.setVisibility(View.GONE);
            }
        });

        hitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedPayment.equals("cash"))
                {
                    total_price.setText("Rp " + Double.toString(foodPrice + deliveryFee));
                }
                else if(selectedPayment.equals("cashless"))
                {

                    promoCode = promo_code.getText().toString();
                    Response.Listener<String> responseListener = new Response.Listener<String>(){
                        @Override
                        public void onResponse(String response) {
                            try{
                                JSONObject jsonObject = new JSONObject(response);
                                if(jsonObject != null) {
                                    promoMinPrice = jsonObject.getInt("minPrice");
                                    promoDiscount = jsonObject.getInt("discount");
                                    promoActive = jsonObject.getBoolean("active");
                                    if (promoActive == true && foodPrice > promoMinPrice){
                                        total_price.setText("Rp " + Double.toString(foodPrice-promoDiscount));
                                    }else {
                                        total_price.setText("Rp " + Double.toString(foodPrice));
                                        Toast.makeText(BuatPesananActivity.this, "Promo Code inactive", Toast.LENGTH_LONG).show();
                                    }
                                }
                            } catch (JSONException e){
                                Toast.makeText(BuatPesananActivity.this, "Promo Code Not Found", Toast.LENGTH_LONG).show();
                                total_price.setText("Rp " + Double.toString(foodPrice));
                            }
                        }
                    };
                    PromoRequest promoRequest = new PromoRequest(promoCode, responseListener);
                    RequestQueue queue = Volley.newRequestQueue(BuatPesananActivity.this);
                    queue.add(promoRequest);
                }
                hitung.setVisibility(View.GONE);
                pesan.setVisibility(View.VISIBLE);
            }
        });

        pesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                BuatPesananRequest buatPesananRequest = null;

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            if (jsonResponse != null) {
                                Toast.makeText(BuatPesananActivity.this, "Order Successful", Toast.LENGTH_LONG).show();
                                Intent mainIntent = new Intent(BuatPesananActivity.this, MainActivity.class);
                                mainIntent.putExtra("currentUserId", currentUserId);
                                startActivity(mainIntent);
                            }
                        } catch (JSONException e) {
                            Toast.makeText(BuatPesananActivity.this, "Order failed", Toast.LENGTH_LONG).show();
                        }
                    }
                };

                if(selectedPayment.equals("cash"))
                {
                    buatPesananRequest = new BuatPesananRequest(Integer.toString(id_food), deliveryFee, Integer.toString(currentUserId), responseListener);
                }

                else if(selectedPayment.equals("cashless"))
                {
                    promoCode = promo_code.getText().toString();
                    buatPesananRequest = new BuatPesananRequest(Integer.toString(id_food), promoCode, Integer.toString(currentUserId), responseListener);
                }

                RequestQueue queue = Volley.newRequestQueue(BuatPesananActivity.this);
                queue.add(buatPesananRequest);
            }
        });
    }


}

