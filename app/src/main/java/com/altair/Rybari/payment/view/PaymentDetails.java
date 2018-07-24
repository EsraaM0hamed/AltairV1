package com.altair.Rybari.payment.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.altair.Rybari.R;

import org.json.JSONException;
import org.json.JSONObject;

public class PaymentDetails extends AppCompatActivity {

    TextView id, status, amount;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_details);
        id = findViewById(R.id.id);
        status = findViewById(R.id.status);
        amount = findViewById(R.id.amount);
        Intent intent = getIntent();
        try {
            JSONObject jsonObject = new JSONObject(intent.getStringExtra("paymentDetails"));


            showDetails(jsonObject, intent.getStringExtra("paymentAmount"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void showDetails(JSONObject response, String paymentAcount) {
        try {
            id.setText(response.getString("id"));
            status.setText(response.getString("state"));
            amount.setText(response.getString(String.format("$%a", paymentAcount)));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
