package sam.mlog;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Delete_Activity extends AppCompatActivity {
    Database_Helper mydb;
    public static final String DEL_Back_MESSAGE = "sam.mlog.MESSAGE";
    TextView textview1;
    EditText edittext1;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_delete_);
        Intent intent= getIntent();
        String messege= intent.getStringExtra(Activity_2.DEL_MESSAGE);
        mydb = new Database_Helper(this);
        textview1 = (TextView) findViewById(R.id.textview1);
        edittext1 = (EditText) findViewById(R.id.edittext1);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (isformvalid()) {
                            Integer deleteRows = mydb.deleteData(edittext1.getText().toString());
                            if (deleteRows > 0) {
                                Toast.makeText(Delete_Activity.this, "Data Deleted Sucessfully", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(Delete_Activity.this, Activity_2.class));
                            } else {
                                Toast.makeText(Delete_Activity.this, "Data Not Deleted ", Toast.LENGTH_LONG).show();

                            }
                        }
                    }
                });
    }
    private  Boolean isformvalid(){
        if(edittext1.getText().toString().trim().isEmpty()){
            Toast.makeText(this, "Employee ID Field cannot be left empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
