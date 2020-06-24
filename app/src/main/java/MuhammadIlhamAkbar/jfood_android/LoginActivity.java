package MuhammadIlhamAkbar.jfood_android;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * <h1>JFood-Android Program based on Object Oriented Programming<h1>
 * This LoginActivity Class is used to precessing login activity.
 * BuatPesananActivity is subclass of an AppCompatActivity class.
 * <p>
 * @author Muhammad Ilham Akbar
 * @version 2020-06-06
 */
public class LoginActivity extends AppCompatActivity {
    SessionManager session;
    private int id = 0;
    Intent mainIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        session = new SessionManager(this);
        final EditText etEmail = findViewById(R.id.etEmail);
        final EditText etPassword = findViewById(R.id.etPassword);
        Button btnLogin = findViewById(R.id.btnLogin);
        TextView tvRegister = findViewById(R.id.tvRegister);
        mainIntent = new Intent(LoginActivity.this, MainActivity.class);
        if(session.getUserId() != 0){
            startActivity(mainIntent);
            finish();
            System.exit(0);
        }

        btnLogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();
                Response.Listener<String> responseListener = new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            if (jsonResponse != null) {
                                Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_LONG).show();
                                id = jsonResponse.getInt("id");
                                mainIntent.putExtra("currentUserId", id);
                                session.createLoginSession(id, jsonResponse.getString("name"),  jsonResponse.getString("email"));
                                startActivity(mainIntent);
                            }
                        } catch (JSONException e) {
                            Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_LONG).show();
                        }
                    }
                };
                    LoginRequest loginRequest = new LoginRequest(email, password, responseListener);
                    RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                    queue.add(loginRequest);
            }
        });

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

    }
}
