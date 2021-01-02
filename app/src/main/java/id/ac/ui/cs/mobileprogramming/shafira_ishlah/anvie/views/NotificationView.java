package id.ac.ui.cs.mobileprogramming.shafira_ishlah.anvie.views;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import id.ac.ui.cs.mobileprogramming.shafira_ishlah.anvie.R;
public class NotificationView extends AppCompatActivity {
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_view);
        textView = findViewById(R.id.textView);
        //getting the activity_notification_view message
        String message=getIntent().getStringExtra("message");
        textView.setText(message);
    }
}