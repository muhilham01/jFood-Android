package MuhammadIlhamAkbar.jfood_android;

import com.android.volley.toolbox.StringRequest;
import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import java.util.HashMap;
import java.util.Map;

/**
 * <h1>JFood-Android Program based on Object Oriented Programming<h1>
 * This MenuRequest Class is used to connect to food server
 * MenuRequest is subclass of an StringRequest class
 * <p>
 * @author Muhammad Ilham Akbar
 * @version 2020-06-06
 */

public class MenuRequest extends StringRequest {
    private static final String URL = "http://192.168.1.7:8080/food";
    private Map<String, String> params;

    /**
     * This is constructor for object of class MenuRequest
     * <p>
     * @param listener - third parameter value of constructor, that is response listener
     */
    public MenuRequest(Response.Listener<String> listener) {
        super(Method.GET, URL, listener, null);
        params = new HashMap<>();
    }

    /**
     * This is getParams method to return params
     * @return params
     */
    @Override
    protected Map<String, String> getParams(){
        return params;
    }

}
