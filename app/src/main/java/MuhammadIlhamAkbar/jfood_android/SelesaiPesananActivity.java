package MuhammadIlhamAkbar.jfood_android;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * <h1>JFood-Android Program based on Object Oriented Programming<h1>
 * This SelesaiPesananActivity Class is used to process invoice.
 * SelesaiPesananActivity is subclass of an AppCompatActivity class.
 * <p>
 * @author Muhammad Ilham Akbar
 * @version 2020-06-06
 */

public class SelesaiPesananActivity extends AppCompatActivity {
    private int currentInvoiceId;
    private int currentUserId;
    private String pesanBerhasil = "FINISHED";
    private String pesanDibatalkan = "CANCELLED";

    TextView staticIdInvoice;
    TextView staticNamaCustomer;
    TextView staticNamaFood;
    TextView staticHargaFood;
    TextView staticNamaSeller;
    TextView staticTotalBiaya;
    TextView staticStatusInvoice;
    TextView staticTipePembayaran;
    TextView staticPromoCode;
    TextView staticBiayaPengiriman;
    TextView staticTanggalPesan;

    TextView idInvoice;
    TextView namaCustomer;
    TextView namaFood;
    TextView hargaFood;
    TextView namaSeller;
    TextView totalBiaya;
    TextView statusInvoice;
    TextView tipePembayaran;
    TextView promoCode;
    TextView biayaPengiriman;
    TextView tanggalPesan;

    Button batal;
    Button selesai;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selesai_pesanan);

        staticIdInvoice = (TextView) findViewById(R.id.staticIdInvoice);
        staticNamaCustomer = (TextView) findViewById(R.id.staticNamaCustomer);
        staticNamaFood = (TextView) findViewById(R.id.staticNamaFood);
        staticHargaFood = (TextView) findViewById(R.id.staticHargaFood);
        staticNamaSeller = (TextView) findViewById(R.id.staticNamaSeller);
        staticTotalBiaya = (TextView) findViewById(R.id.staticTotalBiaya);
        staticStatusInvoice = (TextView) findViewById(R.id.staticStatusInvoice);
        staticTipePembayaran = (TextView) findViewById(R.id.staticTipePembayaran);
        staticPromoCode = (TextView) findViewById(R.id.staticPromoCode);
        staticBiayaPengiriman = (TextView) findViewById(R.id.staticBiayaPengiriman);
        staticTanggalPesan = (TextView) findViewById(R.id.staticTanggalPesan);

        idInvoice = (TextView) findViewById(R.id.idInvoice);
        namaCustomer = (TextView) findViewById(R.id.namaCustomer);
        namaFood = (TextView) findViewById(R.id.namaFood);
        hargaFood = (TextView) findViewById(R.id.hargaFood);
        namaSeller = (TextView) findViewById(R.id.namaSeller);
        totalBiaya = (TextView) findViewById(R.id.totalBiaya);
        statusInvoice = (TextView) findViewById(R.id.statusInvoice);
        tipePembayaran = (TextView) findViewById(R.id.tipePembayaran);
        promoCode = (TextView) findViewById(R.id.promoCode);
        biayaPengiriman = (TextView) findViewById(R.id.biayaPengiriman);
        tanggalPesan = (TextView) findViewById(R.id.tanggalPesan);

        staticIdInvoice.setVisibility(View.GONE);
        staticNamaCustomer.setVisibility(View.GONE);
        staticNamaFood.setVisibility(View.GONE);
        staticHargaFood.setVisibility(View.GONE);
        staticNamaSeller.setVisibility(View.GONE);
        staticTotalBiaya.setVisibility(View.GONE);
        staticStatusInvoice.setVisibility(View.GONE);
        staticTipePembayaran.setVisibility(View.GONE);
        staticPromoCode.setVisibility(View.GONE);
        staticBiayaPengiriman.setVisibility(View.GONE);
        staticTanggalPesan.setVisibility(View.GONE);

        idInvoice.setVisibility(View.GONE);
        namaCustomer.setVisibility(View.GONE);
        namaFood.setVisibility(View.GONE);
        hargaFood.setVisibility(View.GONE);
        namaSeller.setVisibility(View.GONE);
        totalBiaya.setVisibility(View.GONE);
        statusInvoice.setVisibility(View.GONE);
        tipePembayaran.setVisibility(View.GONE);
        promoCode.setVisibility(View.GONE);
        biayaPengiriman.setVisibility(View.GONE);
        tanggalPesan.setVisibility(View.GONE);

        batal = (Button) findViewById(R.id.batal);
        selesai = (Button) findViewById(R.id.selesai);

        batal.setVisibility(View.GONE);
        selesai.setVisibility(View.GONE);

        if(getIntent().getExtras()!=null)
        {
            Intent intent = getIntent();
            currentUserId = intent.getIntExtra("currentUserId", 0);
            currentInvoiceId = intent.getIntExtra("currentInvoiceId", 0);
        }
        fetchPesanan();

        selesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            if (jsonResponse != null) {
                                Toast.makeText(SelesaiPesananActivity.this, "Pesanan Selesai", Toast.LENGTH_LONG).show();
                                Intent mainIntent = new Intent(SelesaiPesananActivity.this, MainActivity.class);
                                mainIntent.putExtra("currentUserId", currentUserId);
                                startActivity(mainIntent);
                            }
                        } catch (JSONException e) {
                            Toast.makeText(SelesaiPesananActivity.this, "JSON FAILED", Toast.LENGTH_LONG).show();
                        }
                    }
                };

                PesananSelesaiRequest pesananSelesaiRequest = new PesananSelesaiRequest(currentInvoiceId, pesanBerhasil, responseListener);
                RequestQueue queue = Volley.newRequestQueue(SelesaiPesananActivity.this);
                queue.add(pesananSelesaiRequest);
            }
        });

        batal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            if (jsonResponse != null) {
                                Toast.makeText(SelesaiPesananActivity.this, "Pesan Batal", Toast.LENGTH_LONG).show();
                                Intent mainIntent = new Intent(SelesaiPesananActivity.this, MainActivity.class);
                                mainIntent.putExtra("currentUserId", currentUserId);
                                startActivity(mainIntent);
                            }
                        } catch (JSONException e) {
                            Toast.makeText(SelesaiPesananActivity.this, "JSON FAILED", Toast.LENGTH_LONG).show();
                        }
                    }
                };

                PesananBatalRequest pesananBatalRequest = new PesananBatalRequest(currentInvoiceId, pesanDibatalkan, responseListener);
                RequestQueue queue = Volley.newRequestQueue(SelesaiPesananActivity.this);
                queue.add(pesananBatalRequest);
            }
        });

    }

    public void fetchPesanan() {
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (currentInvoiceId==0){
                    Toast.makeText(SelesaiPesananActivity.this, "Pesanan tidak ada", Toast.LENGTH_LONG).show();
                    Intent mainIntent = new Intent(SelesaiPesananActivity.this, MainActivity.class);
                    mainIntent.putExtra("currentUserId", currentUserId);
                    startActivity(mainIntent);
                }
                try{
                    JSONArray jsonResponse = new JSONArray(response);
                    if (jsonResponse != null) {
                        for (int i = 0; i < jsonResponse.length(); i++)
                        {
                            JSONObject invoice = jsonResponse.getJSONObject(i);
                            JSONObject customer = invoice.getJSONObject("customer");
                            JSONArray foods = invoice.getJSONArray("foods");
                            String status_invoice = invoice.getString("invoiceStatus");
                                staticIdInvoice.setVisibility(View.VISIBLE);
                                staticNamaCustomer.setVisibility(View.VISIBLE);
                                staticNamaFood.setVisibility(View.VISIBLE);
                                staticHargaFood.setVisibility(View.VISIBLE);
                                staticNamaSeller.setVisibility(View.VISIBLE);
                                staticTotalBiaya.setVisibility(View.VISIBLE);
                                staticStatusInvoice.setVisibility(View.VISIBLE);
                                staticTipePembayaran.setVisibility(View.VISIBLE);
                                staticTanggalPesan.setVisibility(View.VISIBLE);

                                idInvoice.setVisibility(View.VISIBLE);
                                namaCustomer.setVisibility(View.VISIBLE);
                                namaFood.setVisibility(View.VISIBLE);
                                hargaFood.setVisibility(View.VISIBLE);
                                namaSeller.setVisibility(View.VISIBLE);
                                totalBiaya.setVisibility(View.VISIBLE);
                                statusInvoice.setVisibility(View.VISIBLE);
                                tipePembayaran.setVisibility(View.VISIBLE);
                                tanggalPesan.setVisibility(View.VISIBLE);
                                if(status_invoice.equals("ONGOING")){
                                    batal.setVisibility(View.VISIBLE);
                                    selesai.setVisibility(View.VISIBLE);
                                }

                                for (int j = 0; j < foods.length(); j++)
                                {
                                    JSONObject food = foods.getJSONObject(j);
                                    String nama_food = food.getString("name");
                                    namaFood.setText(nama_food);
                                    String harga_food = food.getString("price");
                                    hargaFood.setText("Rp " + harga_food);
                                    JSONObject seller = food.getJSONObject("seller");
                                    String nama_seller = seller.getString("name");
                                    namaSeller.setText(nama_seller);
                                }
                                int id_invoice = invoice.getInt("id");
                                String nama_customer = customer.getString("name");
                                int total_biaya = invoice.getInt("totalPrice");
                                String tipe_pembayaran = invoice.getString("paymentType");
                                String str_date = invoice.getString("dateInsert");
                                DateFormat formatterToDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX", Locale.US);
                                Date date = formatterToDate.parse(str_date);
                                Locale locale = new Locale("in", "ID");
                                SimpleDateFormat formatterToString = new SimpleDateFormat("dd-MM-yyyy HH:mm", locale);
                                String tanggal_pesan = formatterToString.format(date);
                                if (tipe_pembayaran.equals("CASH")){
                                    staticBiayaPengiriman.setVisibility(View.VISIBLE);
                                    biayaPengiriman.setVisibility(View.VISIBLE);
                                    staticPromoCode.setVisibility(View.GONE);
                                    promoCode.setVisibility(View.GONE);
                                    int biaya_pengiriman = invoice.getInt("deliveryFee");
                                    biayaPengiriman.setText("Rp " + Integer.toString(biaya_pengiriman));
                                }else if(tipe_pembayaran.equals("CASHLESS")){
                                    if(!invoice.isNull("promo")){
                                        staticPromoCode.setVisibility(View.VISIBLE);
                                        promoCode.setVisibility(View.VISIBLE);
                                        staticBiayaPengiriman.setVisibility(View.GONE);
                                        biayaPengiriman.setVisibility(View.GONE);
                                        JSONObject promo = invoice.getJSONObject("promo");
                                        String promo_code = promo.getString("code");
                                        promoCode.setText(promo_code);
                                    }
                                }
                                idInvoice.setText(Integer.toString(id_invoice));
                                namaCustomer.setText(nama_customer);
                                totalBiaya.setText("Rp " + Integer.toString(total_biaya));
                                statusInvoice.setText(status_invoice);
                                tipePembayaran.setText(tipe_pembayaran);
                                tanggalPesan.setText(tanggal_pesan);
                        }

                    }
                }catch(JSONException | ParseException e){
                    Toast.makeText(SelesaiPesananActivity.this, "JSON Failed", Toast.LENGTH_LONG).show();
                    Intent mainIntent = new Intent(SelesaiPesananActivity.this, MainActivity.class);
                    mainIntent.putExtra("currentUserId", currentUserId);
                    startActivity(mainIntent);
                }
            }
        };
        PesananFetchRequest pesananFetchRequest = new PesananFetchRequest(currentUserId, responseListener);
        RequestQueue queue = Volley.newRequestQueue(SelesaiPesananActivity.this);
        queue.add(pesananFetchRequest);

    }
}
