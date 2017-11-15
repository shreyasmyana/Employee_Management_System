package sam.mlog;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class EmpMainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        String messege = intent.getStringExtra(SelectionActivity.Employee_MESSAGE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emp_main);
    }
}
