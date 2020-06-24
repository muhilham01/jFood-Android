package MuhammadIlhamAkbar.jfood_android;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * <h1>JFood-Android Program based on Object Oriented Programming<h1>
 * This PesananFetchRequest Class is used to connect to customer server
 * PesananFetchRequest is subclass of an StringRequest class
 * <p>
 * @author Muhammad Ilham Akbar
 * @version 2020-06-06
 */
public class PesananFetchRequest extends StringRequest {
    private static final String URL = "http://192.168.1.7:8080/invoice/customer/";
    private Map<String, String> params;

    /**
     * This is constructor for object of class BuatPesananRequest
     * <p>
     * @param id_customer - first parameter value of constructor, that is customer id
     * @param listener - second parameter value of constructor, that is response listener
     */
    public PesananFetchRequest(int id_customer, Response.Listener<String> listener) {
        super(Method.GET, URL+id_customer, listener, null);
        params = new HashMap<>();
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
