package sam.mlog;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.speech.tts.Voice;
import android.support.annotation.BoolRes;
import android.support.annotation.StringDef;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static android.R.id.message;
import static android.provider.AlarmClock.EXTRA_MESSAGE;


public class AuthenticationActivity extends AppCompatActivity {
EditText  username, password ;

    ProgressDialog progressDialog;

    public static final String EXTRA_MESSAGE = "sam.mlog.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        String messege = intent.getStringExtra(SelectionActivity.Manager_MESSAGE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);

        username = (EditText) findViewById(R.id.username);
        password= (EditText)findViewById(R.id.password);
      Button  signInButton=(Button) findViewById(R.id.signInButton);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isformvalid()){
                    performSignIn();
                }
            }
        });

            progressDialog = new ProgressDialog(this);
            progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Please Wait");

    }
    public void Show_Messege(String Title, String Messege) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(Title);
        builder.setMessage(Messege);
        builder.show();

    }

    private  Boolean isformvalid(){
        if(username.getText().toString().trim().isEmpty()){
            Toast.makeText(this, "username cannot be left empty", Toast.LENGTH_SHORT).show();
            return false;

        }
        if(password.getText().toString().trim().isEmpty()){
                Toast.makeText(this,"Password cannot be left empty",Toast.LENGTH_LONG).show();
            return false;
        }
return true;
    }

    private void performSignIn(){

        new SignInTask().execute(username.getText().toString(),password.getText().toString());
    }

    private  void showprgressDialog(Boolean shouldshould){
        if (shouldshould){
            progressDialog.show();
        }else {
            progressDialog.dismiss();
        }
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

    class SignInTask extends AsyncTask<String,Void,Boolean>{

        String mockusername ="manager";
        String mockpassword="123";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showprgressDialog(true);
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            showprgressDialog(false);

            if(aBoolean){
                Show_Messege("Welcome","You have successfully signed in");
                startActivity(new Intent(AuthenticationActivity.this,Activity_2.class));

            }
            else{
                ShowAlert("Failed","username/password is incorrect");
            }
        }

        @Override
        protected Boolean doInBackground(String... strings) {
            String username = strings[0];
            String password = strings[1];

            try {
                Thread.sleep(750);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return username.contentEquals(mockusername) && password.contentEquals(mockpassword);

        }
    }

}
