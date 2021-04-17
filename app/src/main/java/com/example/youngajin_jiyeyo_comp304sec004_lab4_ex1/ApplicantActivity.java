package com.example.youngajin_jiyeyo_comp304sec004_lab4_ex1;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ApplicantActivity extends AppCompatActivity {

    Button btnAdd, btnView;
    EditText txtId, txtFname, txtLname, txtExainer;
    TextView txtApplicantInfo;
    Spinner spnrTcenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_applicant);

        getSupportActionBar().setTitle("Applicant Information");

        txtId = findViewById(R.id.txt_applicant_id);
        txtFname = findViewById(R.id.txt_applicant_fname);
        txtLname = findViewById(R.id.txt_applicant_lname);
        spnrTcenter = findViewById(R.id.spnr_applicent_tcenter);
        txtExainer = findViewById(R.id.txt_applicant_examiner);
        SharedPreferences popupPref = getSharedPreferences("popupPref", MODE_PRIVATE);
        String reloadFlag = popupPref.getString("loginFinishPoint", "Log In");
        txtExainer.setText(reloadFlag);
        txtExainer.setEnabled(false);
        txtApplicantInfo = findViewById(R.id.txt_applicant_info);

        btnAdd = findViewById(R.id.btn_add_applicant);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Applicant applicant = new Applicant();
                boolean isAdded = false;
                try {
                    //adding to the list
                    //int applicantId = Integer.parseInt(txtId.getText().toString());
                    String applicantId = txtId.getText().toString();
                    String fName = txtFname.getText().toString();
                    String lName = txtLname.getText().toString();
                    String tCenter = spnrTcenter.getSelectedItem().toString();
                    String examiner = txtExainer.getText().toString();

                    if (applicantId.isEmpty() || fName.isEmpty() || lName.isEmpty() || tCenter.isEmpty() || examiner.isEmpty()){
                        throw new Exception("All fields must be entered.");
                    }
                    applicant.setApplicantId(applicantId);
                    applicant.setFirstName(fName);
                    applicant.setLastName(lName);
                    applicant.setTestCenter(tCenter);
                    applicant.setExaminerId(examiner);

                    MainActivity.myAppDB.myDao().addApplicant(applicant);

                    isAdded = true;
                }
                catch(Exception e)
                {
                    txtApplicantInfo.setText(e.getMessage());
                    Toast.makeText(getApplicationContext(), "Add Applicant failed. \nError: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
                if(isAdded) {
                    String applicantInfo = "Applicant Id: " + applicant.getApplicantId()
                            + "\nName : " + applicant.getFirstName() + applicant.getLastName()
                            + "\nTest Center: " + applicant.getTestCenter()
                            + "\nExaminer Id: " + applicant.getExaminerId();
                    txtApplicantInfo.setText(applicantInfo);
                    Toast.makeText(getApplicationContext(), "Applicant added successfully!", Toast.LENGTH_SHORT).show();

                    txtId.setText("");
                    txtFname.setText("");
                    txtLname.setText("");
                    spnrTcenter.setSelection(0);
                    //txtExainer.setText("");

                    btnView.setVisibility(View.VISIBLE);
                }
            }
        });
        btnView = findViewById(R.id.btn_view_applicants);
        btnView.setVisibility(View.INVISIBLE);
        btnView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //view the lists
            }
        });
    }
}