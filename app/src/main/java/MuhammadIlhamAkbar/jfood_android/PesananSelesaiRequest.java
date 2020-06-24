package MuhammadIlhamAkbar.jfood_android;

import android.os.Build;
import androidx.annotation.RequiresApi;
import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * <h1>JFood-Android Program based on Object Oriented Programming<h1>
 * This PesananSelesaiRequest Class is used to connect to invoice server
 * PesananSelesaiRequest is subclass of an StringRequest class
 * <p>
 * @author Muhammad Ilham Akbar
 * @version 2020-06-06
 */
public class PesananSelesaiRequest extends StringRequest {

    private static final String URL = "http://192.168.1.7:8080/invoice/invoiceStatus/";
    private Map<String, String> params;

    /**
     * This is constructor for object of class PesananSelesaiRequest
     * <p>
     * @param id_invoice - first parameter value of constructor, that is invoice id
     * @param status - second parameter value of constructor, that is status invoice
     * @param listener - third parameter value of constructor, that is response listener
     */
    public PesananSelesaiRequest(int id_invoice, String status, Response.Listener<String> listener) {
        super(Method.PUT, URL+id_invoice, listener, null);
        params = new HashMap<>();
        params.put("status", status);
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
