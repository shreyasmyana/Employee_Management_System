package sam.mlog;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Update extends AppCompatActivity {
    Database_Helper mydb;
    EditText Emp_id,Emp_name,Emp_pos,Emp_sal,Emp_TODO;
    Button Emp_update;
    public static final String Update_Bck_MESSAGE = "sam.mlog.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        Intent intent= getIntent();
        String messege= intent.getStringExtra(Activity_2.Updte_MESSAGE);
        mydb = new Database_Helper(this);
        Emp_TODO = (EditText) findViewById(R.id.Emp_TODO);
        Emp_id = (EditText) findViewById(R.id.Emp_id);
        Emp_name = (EditText) findViewById(R.id.Emp_name);
        Emp_pos = (EditText) findViewById(R.id.Emp_pos);
        Emp_sal = (EditText) findViewById(R.id.Emp_sal);
        Emp_update = (Button) findViewById(R.id.Emp_update);
      updateData();
    }

    private  Boolean isformvalid(){
        if(Emp_id.getText().toString().trim().isEmpty()){
            Toast.makeText(this, "Employee ID Field cannot be left empty", Toast.LENGTH_SHORT).show();
            return false;

        }
        if(Emp_name.getText().toString().trim().isEmpty()){
            Toast.makeText(this,"Employee Nsme Field cannot be left empty",Toast.LENGTH_LONG).show();
            return false;
        }
        if(Emp_pos.getText().toString().trim().isEmpty()){
            Toast.makeText(this,"designation  Field cannot be left empty",Toast.LENGTH_LONG).show();
            return false;
        }

        if(Emp_sal.getText().toString().trim().isEmpty()){
            Toast.makeText(this,"Salary Field cannot be left empty",Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    public void updateData(){
        Emp_update.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (isformvalid()) {
                            boolean isUpdate = mydb.UpdateData(Emp_id.getText().toString(), Emp_name.getText().toString(), Emp_pos.getText().toString(), Emp_sal.getText().toString(),Emp_TODO.getText().toString());
                            if (isUpdate == true) {
                                Toast.makeText(Update.this, "Data Updated", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(Update.this, Activity_2.class));
                            } else {
                                Toast.makeText(Update.this, "Data not Updated", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                });

    }
}
