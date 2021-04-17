package com.example.youngajin_jiyeyo_comp304sec004_lab4_ex1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText examinerId, firstName, lastName, testCenter, password;
    Button login;
    String message ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        
        examinerId = findViewById(R.id.txtId2);
//        firstName = findViewById(R.id.txtFirstName);
//        lastName =findViewById(R.id. txtLastName);
//        testCenter = findViewById(R.id.txtCenter);
        password = findViewById(R.id.txtPassword2);
        login = findViewById(R.id.btnLogin);



        login.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                Examiner examiner = new Examiner();

                examiner.setExaminerId(examinerId.getText().toString());
                examiner.setPassword(password.getText().toString());
//                examiner.setFirstName(firstName.getText().toString());
//                examiner.setLastName(lastName.getText().toString());
//                examiner.setTestCenter(testCenter.getText().toString());

                if (validation(examiner)){


//deleted to use one room db from mainactivity
//                    MyAppDB myappdb = MyAppDB.getMyAppDB (getApplicationContext());
//                    MyDao mydao = myappdb.myDao();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {

                            //mydao.addExaminer(examiner);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(), "You have successfully logged in!", Toast.LENGTH_SHORT).show();

                                    //
                                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                        startActivity(intent);

                                    String examinerId = examiner.getExaminerId();
                                    SharedPreferences popupPref = getSharedPreferences("popupPref", MODE_PRIVATE);
                                    SharedPreferences.Editor editor = popupPref.edit();
                                    editor.putString("loginFinishPoint", examinerId);
                                    editor.commit();

                                }
                            });
                        }
                    }).start();
                } else
                {
                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private Boolean validation(Examiner examiner){

        if (examiner.getExaminerId().isEmpty() ||
                examiner.getPassword().isEmpty()){
            message = "Please enter Id and Password";
            return false;
        }
        String correctPassword;
        try {
            correctPassword = MainActivity.myAppDB.myDao().getPassword(examiner.getExaminerId());
            if(correctPassword.isEmpty())
                throw new Exception();
        }
        catch (Exception e){
            message = "Id does not exist.";
            return false;
        }
        if(!examiner.password.equals(correctPassword)){
            message = "Id and Password do not match";
            Toast.makeText(getApplicationContext(), correctPassword + "!= "+ examiner.password, Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}