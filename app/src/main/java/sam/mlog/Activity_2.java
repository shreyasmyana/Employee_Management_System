package sam.mlog;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Activity_2 extends AppCompatActivity {

    Database_Helper mydb;
    public static final String ADD_MESSAGE = "sam.mlog.MESSAGE";
    public static final String DEL_MESSAGE = "sam.mlog.MESSAGE";
    public static final String Updte_MESSAGE = "sam.mlog.MESSAGE";
    public static final String Auth_Bck_MESSAGE = "sam.mlog.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        mydb = new Database_Helper(this);
        Intent intent2 = getIntent();
        String messege2 = intent2.getStringExtra(Add_Activity.ADD_BCK_MESSAGE);
        Intent intent3 = getIntent();
        String messege3 = intent3.getStringExtra(Delete_Activity.DEL_Back_MESSAGE);
        Intent intent4 = getIntent();
        String messege4 = intent4.getStringExtra(Update.Update_Bck_MESSAGE);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Intent intent = getIntent();
        String messege = intent.getStringExtra(AuthenticationActivity.EXTRA_MESSAGE);

        Button logout = (Button) findViewById(R.id.btn_logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Activity_2.this, SelectionActivity.class));

            }
        });


        Button addbtn = (Button) findViewById(R.id.addbtn);
        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Activity_2.this, Add_Activity.class));

            }
        });
        Button deletebtn = (Button) findViewById(R.id.deletebtn);
        deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                  startActivity(new Intent(Activity_2.this, Delete_Activity.class));



            }
        });
        Button updtebtn = (Button) findViewById(R.id.updatebtn1);
        updtebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Activity_2.this, Update.class));


            }
        });
        Button displaybtn = (Button) findViewById(R.id.displaybtn);
        displaybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ViewAll();
            }
        });


    }

    public void ViewAll() {
        Cursor res = mydb.getAllData();
        if (res.getCount() == 0) {
            ShowAlert("ERROR", "No Data Found");
            return;
        } else {

            StringBuffer buffer = new StringBuffer();
            while (res.moveToNext()) {
                buffer.append("ID :" + res.getString(0) + "\n");
                buffer.append("NAME :" + res.getString(1) + "\n");
                buffer.append("POSITION :" + res.getString(2) + "\n");
                buffer.append("SALARY :" + res.getString(3) + "\n");
                buffer.append("TODO:"+ res.getString(4)+"\n\n");
            }
            ShowAlert("DATA", buffer.toString());

        }

    }

    public void Show_Messege(String Title, String Messege) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(Title);
        builder.setMessage(Messege);
        builder.show();

    }
    private void  ShowAlert(String title,String messege){
        AlertDialog.Builder builder =new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(messege);
        builder.setNeutralButton("Dismiss", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }
}
