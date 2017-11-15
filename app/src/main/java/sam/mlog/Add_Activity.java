package sam.mlog;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Add_Activity extends AppCompatActivity {
    Database_Helper mydb;
    EditText Emp_id,Emp_name,Emp_pos,Emp_sal;
    Button Emp_add;
    public static final String ADD_BCK_MESSAGE = "sam.mlog.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_);
        Intent intent = getIntent();
        String messege = intent.getStringExtra(Activity_2.ADD_MESSAGE);
         mydb = new Database_Helper(this);
         Emp_id = (EditText) findViewById(R.id.Emp_id);
         Emp_name = (EditText) findViewById(R.id.Emp_name);
        Emp_pos = (EditText) findViewById(R.id.Emp_pos);
         Emp_sal = (EditText) findViewById(R.id.Emp_sal);
        Emp_add = (Button) findViewById(R.id.Emp_add);
        AddData();


    }


public  void AddData(){

    Emp_add.setOnClickListener(
            new View.OnClickListener() {

                public void onClick(View v) {
                    if(isformvalid()) {
                        boolean isInserted = mydb.addEmployee(Emp_id.getText().toString(), Emp_name.getText().toString(), Emp_pos.getText().toString(), Emp_sal.getText().toString());
                        if (isInserted == true) {
                            Toast.makeText(Add_Activity.this, "Data Inserted", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(Add_Activity.this, Activity_2.class));

                        } else {
                            Toast.makeText(Add_Activity.this, "Data not inserted", Toast.LENGTH_LONG).show();

                        }
                    }
                    }
                } );
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

}






