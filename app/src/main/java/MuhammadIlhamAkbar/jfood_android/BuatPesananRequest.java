package MuhammadIlhamAkbar.jfood_android;

import com.android.volley.toolbox.StringRequest;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * <h1>JFood-Android Program based on Object Oriented Programming<h1>
 * This BuatPesananRequest Class is used to connect to create cash server
 * BuatPesananRequest is subclass of an StringRequest class
 * <p>
 * @author Muhammad Ilham Akbar
 * @version 2020-06-06
 */

public class BuatPesananRequest extends StringRequest {
    public static final String URL_CASH = "http://192.168.1.7:8080/invoice/createCashInvoice";
    public static final String URL_CASHLESS = "http://192.168.1.7:8080/invoice/createCashlessInvoice";
    private Map<String, String> params;

    /**
     * This is constructor for object of class BuatPesananRequest
     * <p>
     * @param id_food - first parameter value of constructor, that is food id
     * @param deliveryFee - second parameter value of constructor, that is value of delivery fee
     * @param id_customer - third parameter value of constructor, that is customer id
     * @param listener - fourth parameter value of constructor, that is response listener
     */
    public BuatPesananRequest(String id_food, int deliveryFee, String id_customer, Response.Listener<String> listener) {
        super(Method.POST, URL_CASH, listener, null);
        String deliveryFee1 = Integer.toString(deliveryFee);
        params = new HashMap<>();
        this.params.put("foodIdList", id_food);
        this.params.put("customerId", id_customer);
        this.params.put("deliveryFee", deliveryFee1);
    }

    /**
     * This is constructor for object of class BuatPesananRequest
     * <p>
     * Updates or changes id, name, email, password, and joindate when create objects
     * @param id_food - first parameter value of constructor, that is food id
     * @param promo_code - second parameter value of constructor, that is value of promo code
     * @param id_customer - third parameter value of constructor, that is customer id
     * @param listener - fourth parameter value of constructor, that is response listener
     */
    public BuatPesananRequest(String id_food, String promo_code, String id_customer, Response.Listener<String> listener) {
        super(Method.POST, URL_CASHLESS, listener, null);
        params = new HashMap<>();
        params.put("foodIdList", id_food);
        params.put("promoCode", promo_code);
        params.put("customerId", id_customer);
    }

    /**
     * This is getParams method to return params
     * @return params
     */
    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
