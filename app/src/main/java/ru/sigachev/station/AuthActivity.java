package ru.sigachev.station;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AuthActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnAuth;
    private Button btnReg;
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        btnAuth = (Button)findViewById(R.id.auth);
        btnAuth.setOnClickListener(this);

        btnReg = (Button)findViewById(R.id.reg);
        btnReg.setOnClickListener(this);

        dialog = new Dialog(AuthActivity.this);
        // Передайте ссылку на разметку
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.dialog_load);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.auth:
                dialog.show();
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);

                break;
            case R.id.reg:
                Intent intent2 = new Intent(this, RegActivity.class);
                startActivity(intent2); break;
        }

    }
}
