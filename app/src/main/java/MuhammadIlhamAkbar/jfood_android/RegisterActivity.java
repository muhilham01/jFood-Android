package MuhammadIlhamAkbar.jfood_android;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <h1>JFood-Android Program based on Object Oriented Programming<h1>
 * This RegisterActivity Class is used to register customer.
 * RegisterActivity is subclass of an AppCompatActivity class.
 * <p>
 * @author Muhammad Ilham Akbar
 * @version 2020-06-06
 */

public class RegisterActivity extends AppCompatActivity {
    Button btnRegister;
    EditText etName;
    EditText etEmail;
    EditText etPassword;

    String name = null;
    String email = null;
    String password = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etName.getText().toString().isEmpty() || etEmail.getText().toString().isEmpty() || etPassword.getText().toString().isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Please fill the field", Toast.LENGTH_SHORT).show();
                } else {
                    name = etName.getText().toString();
                    if (checkValidEmail(etEmail.getText().toString())){
                        email = etEmail.getText().toString();
                    }else{
                        Toast.makeText(RegisterActivity.this, "email invalid", Toast.LENGTH_SHORT).show();
                    }
                    if (checkValidPassword(etPassword.getText().toString())){
                        password = etPassword.getText().toString();
                    }else{
                        Toast.makeText(RegisterActivity.this, "password invalid", Toast.LENGTH_SHORT).show();
                    }
                }

                Response.Listener<String> responseListener = new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        try 	{
                            JSONObject jsonObject = new JSONObject(response);
                            if (jsonObject != null) {
                                Toast.makeText(RegisterActivity.this, "Register Successful", Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            Toast.makeText(RegisterActivity.this, "Register Failed", Toast.LENGTH_LONG).show();
                        }
                    }
                };
                if( email!=null && password != null){
                    RegisterRequest registerRequest = new RegisterRequest(name, email, password, responseListener);
                    RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                    queue.add(registerRequest);
                }
            }
        });
    }

    public boolean checkValidPassword(final String password_chek) {
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,}$";
        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        Matcher matcher = pattern.matcher(password_chek);
        return matcher.matches();
    }

    public boolean checkValidEmail(final String email_check) {
        final String PASSWORD_PATTERN = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";
        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        Matcher matcher = pattern.matcher(email_check);
        return matcher.matches();
    }


}
