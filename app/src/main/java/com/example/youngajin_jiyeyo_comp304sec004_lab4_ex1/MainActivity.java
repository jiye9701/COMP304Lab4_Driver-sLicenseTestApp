package com.example.youngajin_jiyeyo_comp304sec004_lab4_ex1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static MyAppDB myAppDB;
    public static Button btnLogin, btnApplicant, btnTest, btnViewTest, btnUpdate, btnLogout;
    String loggedText;
//    public static int static_app_id;
//    public static int static_test_id;
//    public static int static_exm_id;

    //TextView tName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Shared Preference
        SharedPreferences popupPref = getSharedPreferences("popupPref", MODE_PRIVATE);
        String reloadFlag = popupPref.getString("loginFinishPoint", "Log In");


//        tName = findViewById(R.id.examinerId);
//        String examinerId = getIntent().getStringExtra("examinerId");
//        tName.setText(examinerId);

        myAppDB = Room.databaseBuilder(getApplicationContext(), MyAppDB.class, "myDB").allowMainThreadQueries().build();

        btnLogin = findViewById(R.id.btn_login);
        //store
        btnLogin.setText(reloadFlag);
        loggedText = btnLogin.getText().toString();

        btnLogout = findViewById(R.id.btn_logout);
//        if(reloadFlag.isEmpty()) {
//            btnLogout.setEnabled(false);
//            btnLogin.setEnabled(true);
//        }else
//        {
//            btnLogout.setEnabled(true);
//            btnLogin.setEnabled(false);
//        }
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_Logout(v);

            }
        });


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                OnClick_LoginActivity();
                btnLogin.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        //test
                        startActivity(new Intent(v.getContext(), LoginActivity.class));
                    }
                });
            }
        });


        btnApplicant = findViewById(R.id.btn_applicant);
        btnApplicant.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), ApplicantActivity.class));
            }
        });
        btnTest = findViewById(R.id.btn_test);
        btnTest.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), TestActivity.class));
            }
        });
        btnViewTest = findViewById(R.id.btn_view_test);
        btnViewTest.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), ViewTestInfoActivity.class));
            }
        });
        btnUpdate = findViewById(R.id.btn_update);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), UpdateInfoActivity.class));
            }
        });
        createExaminers();
    }//end onCreate

//    public void OnClick_LoginActivity()
//
//    {
//
//
//        }
        public void btn_Logout(View v) {

        new AlertDialog.Builder(this)
                .setTitle("Logout").setMessage("Are you sure?")
                .setPositiveButton("Logout", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        //Shared Preference

                        SharedPreferences popupPref = getSharedPreferences("popupPref", MODE_PRIVATE);
                        SharedPreferences.Editor editor = popupPref.edit();
                        editor.putString("loginFinishPoint", "Log In");
                        editor.commit();
                        String reloadFlag = popupPref.getString("loginFinishPoint", "Log In");
                        btnLogout.setText(reloadFlag);

                        Toast.makeText(getApplicationContext(), "You have successfully logged out!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);

                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {

                    }
                })
                .show();
    }



    public void createExaminers(){
        try {
            Examiner[] examiners = new Examiner[5];
            examiners[0] = new Examiner("100001", "Jay", "Jin", "North York", "100001");
            examiners[1] = new Examiner("100002", "Charles", "Pearson", "Toronto", "100002");
            examiners[2] = new Examiner("100003", "Miller", "Janet", "Markham", "100003");
            examiners[3] = new Examiner("100004", "John", "Allister", "Mississauga", "100004");
            examiners[4] = new Examiner("100005", "Jane", "Temple", "Brampton", "100005");
            //examiners[5] = new Examiner("100006", "Kylie", "Grant", "Scarborough", "100006");
            Test[] tests = new Test[5];
            tests[0] = new Test("200001", "100001", "300002", 90, "21-Mar-2021", "A");
            tests[1] = new Test("200002", "100001", "300003", 61, "24-Sep-2019", "B");
            tests[2] = new Test("200003", "100003", "300004", 78, "17-Dec-2020", "C");
            tests[3] = new Test("200004", "100005", "300002", 46, "15-Feb-2018", "A");
            tests[4] = new Test("200005", "100005", "300003", 87, "22-Apr-2018", "B");
            Applicant[] applicants = new Applicant[5];
            applicants[0] = new Applicant("100001", "Amanda", "Doent", "North York", "300001");
            applicants[1] = new Applicant("100002", "George", "Doe", "Toronto", "300004");
            applicants[2] = new Applicant("100003", "Nicol", "Green", "Scarborough", "300002");
            applicants[3] = new Applicant("100004", "Lena", "Smith", "Vaughan", "300003");
            applicants[4] = new Applicant("100005", "Lucy", "Taylor", "Mississauga", "300004");

            for (int i = 0; i <5; i++)
            {
                MainActivity.myAppDB.myDao().addExaminer(examiners[i]);
                MainActivity.myAppDB.myDao().addTest(tests[i]);
                MainActivity.myAppDB.myDao().addApplicant(applicants[i]);
            }
        }catch (Exception e)
        {
            //Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}