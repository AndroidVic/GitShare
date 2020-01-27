package net.promasoft.trawellmate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class AlertMsg extends AppCompatActivity {
    ImageView img;
    TextView tv_title, tv_content;
    Button btn_alert;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_msg);
        img= findViewById(R.id.iv_alertImg);
        tv_title= findViewById(R.id.tv_alertTitle);
        tv_content= findViewById(R.id.alert_content);
        btn_alert= findViewById(R.id.alert_btn);
    }


}
