package com.altair.Rybari.payment.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.altair.Rybari.R;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import org.json.JSONException;

import java.math.BigDecimal;

public class PaymentAcivity extends AppCompatActivity {
    private static final int PAYPAL_REQUEST_CODE = 5151;
    PayPalConfiguration payPalConfiguration = new PayPalConfiguration().environment(PayPalConfiguration.ENVIRONMENT_SANDBOX).clientId(Config.CLIENT_ID);
    EditText amount_txt;
    String amount = "";
    Button pay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        pay = findViewById(R.id.pay);
        amount_txt = findViewById(R.id.amount);

        //start Paypal services;
        Intent intent = new Intent(this, PayPalService.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, payPalConfiguration);
        startService(intent);
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                processPayment();

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(new Intent(this, PayPalService.class));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PAYPAL_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                PaymentConfirmation paymentConfirmation = data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);
                if (paymentConfirmation != null) {

                    try {
                        String paymentDetails = paymentConfirmation.toJSONObject().toString(4);
                        startActivity(new Intent(this, PaymentDetails.class).
                                putExtra("paymentDetails", paymentDetails).
                                putExtra("paymentAmount", amount));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else if(requestCode== Activity.RESULT_CANCELED){
                    Toast.makeText(this, "Cancel", Toast.LENGTH_SHORT).show();
                }

            } else if (requestCode == PaymentActivity.RESULT_EXTRAS_INVALID) {
                Toast.makeText(this, "Invalid", Toast.LENGTH_SHORT).show();

            }
        }
    }

    private void processPayment() {
        amount = amount_txt.getText().toString();
        PayPalPayment payPalPayment = new PayPalPayment(new BigDecimal(String.valueOf(amount)), "USD", "Altair", PayPalPayment.PAYMENT_INTENT_SALE);
        Intent intent = new Intent(this, PaymentActivity.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, payPalConfiguration);
        intent.putExtra(PaymentActivity.EXTRA_PAYMENT, payPalPayment);
        startActivityForResult(intent, PAYPAL_REQUEST_CODE);
    }
}
