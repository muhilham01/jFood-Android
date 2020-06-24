package MuhammadIlhamAkbar.jfood_android;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import java.util.HashMap;
import java.util.Map;

/**
 * <h1>JFood-Android Program based on Object Oriented Programming<h1>
 * This PromoRequest Class is used to connect to promo server
 * PromoRequest is subclass of an StringRequest class
 * <p>
 * @author Muhammad Ilham Akbar
 * @version 2020-06-06
 */

public class PromoRequest extends StringRequest {

    private static final String Promo_URL = "http://192.168.1.7:8080/promo/";
    private Map<String, String> params;

    /**
     * This is constructor for object of class PromoRequest
     * <p>
     * @param promoCode - first parameter value of constructor, that is promo code
     * @param listener - third parameter value of constructor, that is response listener
     */
    public PromoRequest(String promoCode, Response.Listener<String> listener) {
        super(Method.GET, Promo_URL+promoCode, listener, null);
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
