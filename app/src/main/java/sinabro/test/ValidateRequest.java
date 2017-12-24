package sinabro.test;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by SY on 2017-12-24.
 */

public class ValidateRequest extends StringRequest {

    final static private String URL="http://syoung1394.cafe24.com/HelperValidate.php";
    private Map<String,String>parameters;


    public ValidateRequest(String userID,Response.Listener<String> listener){
        super(Method.POST, URL, listener, null);
        parameters=new HashMap<>();
        parameters.put("userID", userID);

    }
    @Override
    public Map<String, String> getParams(){
        return parameters;
    }
}
