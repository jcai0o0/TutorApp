package robertcurtis.jingnicai.sidiamadou.tutor.tutorapp;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import robertcurtis.jingnicai.sidiamadou.tutor.tutorapp.util.DBOperator;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import android.database.Cursor;
import robertcurtis.jingnicai.sidiamadou.tutor.tutorapp.util.idObject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TutorActivity extends AppCompatActivity {

    private static final String TAG ="LoginActivity";
    private static final int REQUEST_SIGNUP = 0;

    @BindView(R.id.LoginUsername) EditText _usernameText;
    @BindView(R.id.LoginPassword) EditText _passwordText;
    @BindView(R.id.LoginButton) Button _loginButton;
    @BindView(R.id.link_signup) TextView _signupLink;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor);
        ButterKnife.bind(this);

        _loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login();
            }
        });

        _signupLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //start the signup activity
                Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
                finish();
            }
        });
    }

    public void login(){
        Log.d(TAG,"Login");

        if (!validate()){
            onLoginFailed();
            return;
        }

        _loginButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(TutorActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        String usernameText = _usernameText.getText().toString();
        String password = _passwordText.getText().toString();

        //TODO: Implement authentication logic here

        try{
            DBOperator.copyDB(getBaseContext());
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */


    public void onClick(View v)
    {
        int id=v.getId();
        if(id==R.id.LoginButton) {
            String enteredUsername = Username.getText().toString();
            String enteredPassword = Password.getText().toString();
            String sql = "select loginID from Login where username = '" + enteredUsername + "' and password = '" + enteredPassword + "'";

            DBOperator  op = DBOperator.getInstance();



            Cursor cursor = op.execQuery(sql);

            String userID = "null";

            while (cursor.moveToNext()) {
                userID = cursor.getString(0);
            }



            if (userID == "null") {
                Toast.makeText(this, "error loggin in", Toast.LENGTH_SHORT).show();
            } else{
                idObject idobject = new idObject();
                idobject.idObject(userID);
                Bundle extras = new Bundle();
                extras.putString("StudentID", idobject.getStudentID());
                extras.putString("TutorID", idobject.getTutorID());

                //Toast.makeText(this, "StudentID = " + extras.getString("StudentID") + " TutorID = " + extras.getString("TutorID") + " loginID = " + idobject.UserID, Toast.LENGTH_SHORT).show();

                Intent i = new Intent(this, LandingPageActivity.class);
                i.putExtras(extras);
                this.startActivity(i);
                finish();

            }

        }
    }


}


