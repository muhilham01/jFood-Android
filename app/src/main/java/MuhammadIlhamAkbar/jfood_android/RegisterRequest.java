package MuhammadIlhamAkbar.jfood_android;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * <h1>JFood-Android Program based on Object Oriented Programming<h1>
 * This RegisterRequest Class is used to connect to customer server
 * RegisterRequest is subclass of an StringRequest class
 * <p>
 * @author Muhammad Ilham Akbar
 * @version 2020-06-06
 */

public class RegisterRequest extends StringRequest {

    private static final String URL = "http://192.168.1.7:8080/customer/register";
    private Map<String, String> params;

    /**
     * This is constructor for object of class RegisterRequest
     * <p>
     * @param name - second parameter value of constructor, that is customer name
     * @param email - second parameter value of constructor, that is customer email
     * @param password - third parameter value of constructor, that is customer password
     * @param listener - fourth parameter value of constructor, that is response listener
     */
    public RegisterRequest(String name, String email, String password, Response.Listener<String> listener)
    {
        super(Method.POST, URL, listener, null);
        params = new HashMap<>();
        params.put("name", name);
        params.put("email", email);
        params.put("password", password);
    }

    /**
     * This is getParams method to return params
     * @return params
     */
    @Override
    protected Map<String, String> getParams() throws AuthFailureError
    {
        return params;
    }

}
