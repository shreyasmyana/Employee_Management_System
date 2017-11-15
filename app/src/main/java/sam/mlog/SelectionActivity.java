package sam.mlog;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class SelectionActivity extends AppCompatActivity {
    Database_Helper mydb;
    public static final String Manager_MESSAGE = "sam.mlog.MESSAGE";
    public static final String Employee_MESSAGE = "sam.mlog.MESSAGE";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);
        mydb = new Database_Helper(this);

        Intent intent = getIntent();
        String messege2 = intent.getStringExtra(Activity_2.Auth_Bck_MESSAGE);

        ImageButton manager = (ImageButton) findViewById(R.id.Manager);
        manager.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View view) {
                                           startActivity(new Intent(SelectionActivity.this, AuthenticationActivity.class));
                                       }
                                   }
        );
     /*ImageButton employee = (ImageButton) findViewById(R.id.Employee);
        employee.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View view) {
                                           startActivity(new Intent(SelectionActivity.this, EmpMainActivity.class));
                                       }
                                   }
        );*/
    }
}