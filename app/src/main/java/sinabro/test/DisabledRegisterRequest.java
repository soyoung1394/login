package sinabro.test;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by SY on 2017-12-10.
 */

public class DisabledRegisterRequest extends StringRequest {

    final static private String URL ="http://syoung1394.cafe24.com/Register.php";
    private Map<String, String> parameters;

    public DisabledRegisterRequest(String userID,String userPassword, String userName, String userPhone, String userPhoto, Response.Listener<String> listener){
        super(Method.POST, URL, listener, null);
        parameters=new HashMap<>();
        parameters.put("userID", userID);
        parameters.put("userPassword", userPassword);
        parameters.put("userName", userName);
        parameters.put("userPhone", userPhone);
        parameters.put("userPhoto", userPhoto);
    }
    @Override
    public Map<String, String> getParams(){
        return parameters;
    }
}
