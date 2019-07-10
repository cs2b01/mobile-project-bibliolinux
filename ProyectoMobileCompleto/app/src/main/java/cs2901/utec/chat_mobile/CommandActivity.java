package cs2901.utec.chat_mobile;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class CommandActivity extends AppCompatActivity {

    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_command);
        TextView nameOfCommand = (TextView) findViewById(R.id.name);
        TextView descriptionOfCommand = (TextView) findViewById(R.id.description);
        nameOfCommand.setText(getIntent().getExtras().get("name").toString());
        descriptionOfCommand.setText(getIntent().getExtras().get("description").toString());
        setTitle("Comandos de Linux");
        id = getIntent().getExtras().get("id").toString();
    }

    public void onBtnDeleteClicked(View v) {
        String url = "http://10.0.0.2:8080/commands" + "/" + id;
        RequestQueue queue = Volley.newRequestQueue(this);

        Map<String, String> params = new HashMap();
        params.put("key", id);
        JSONObject parameters = new JSONObject(params);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.DELETE,
                url,
                parameters,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        finish();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO: Handle error
                error.printStackTrace();

            }
        });

        queue.add(jsonObjectRequest);
    }

}
