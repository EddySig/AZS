package ru.sigachev.station;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

public class LCActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnCam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lc);

        btnCam = (Button)findViewById(R.id.camera);
        btnCam.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.camera:
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_CAMERA_BUTTON);
                intent.putExtra(Intent.EXTRA_KEY_EVENT, new KeyEvent(KeyEvent.ACTION_DOWN,
                        KeyEvent.KEYCODE_CAMERA));
                sendOrderedBroadcast(intent, null);
                break;
        }
    }
}
