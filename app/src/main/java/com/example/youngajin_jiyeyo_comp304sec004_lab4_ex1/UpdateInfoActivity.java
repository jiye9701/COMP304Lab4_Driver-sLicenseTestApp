package com.example.youngajin_jiyeyo_comp304sec004_lab4_ex1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Update;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class UpdateInfoActivity extends AppCompatActivity {

    Spinner spnrApplicantId, spnrExaminerId;
    EditText txtFName, txtLName, txtTCenter;
    TextView txtUpdateInfo;
    Button btnUpdate, btnClear;

    List<String> applicantIds, examinerIds;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_info);

        getSupportActionBar().setTitle("Update Applicant Information");

        txtFName = findViewById(R.id.txt_update_applicant_fname);
        txtLName = findViewById(R.id.txt_update_applicant_lname);
        txtTCenter = findViewById(R.id.txt_update_applicant_tcenter);

        spnrApplicantId = findViewById(R.id.spnr_search_applicant_id);
        applicantIds = MainActivity.myAppDB.myDao().getAllApplicantIds();
        ArrayAdapter<String> applicantsAdapter = new ArrayAdapter<>(UpdateInfoActivity.this, android.R.layout.simple_spinner_dropdown_item, applicantIds);
        spnrApplicantId.setAdapter(applicantsAdapter);
        //item selected
        spnrApplicantId.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(UpdateInfoActivity.this, applicantIds.get(i), Toast.LENGTH_SHORT).show();
                Applicant applicant = MainActivity.myAppDB.myDao().getAllApplicantById(spnrApplicantId.getSelectedItem().toString());

                txtFName.setText(applicant.getFirstName());
                txtLName.setText(applicant.getLastName());
                txtTCenter.setText(applicant.getTestCenter());
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spnrExaminerId = findViewById(R.id.spnr_update_examiner_id);
        examinerIds = MainActivity.myAppDB.myDao().getAllExaminerIds();
        ArrayAdapter<String> examinersAdapter = new ArrayAdapter<>(UpdateInfoActivity.this, android.R.layout.simple_spinner_dropdown_item, examinerIds);
        spnrExaminerId.setAdapter(examinersAdapter);
        //item selected
        spnrExaminerId.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(UpdateInfoActivity.this, examinerIds.get(i), Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        txtUpdateInfo = findViewById(R.id.txt_update_applicant_info);

        btnUpdate = findViewById(R.id.btn_update_applicant);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //update
                Applicant applicant = new Applicant();
                boolean isAdded = false;
                try {
                    //adding to the list
                    //int applicantId = Integer.parseInt(txtId.getText().toString());
                    String applicantId = spnrApplicantId.getSelectedItem().toString();
                    String fName = txtFName.getText().toString();
                    String lName = txtLName.getText().toString();
                    String tCenter = txtTCenter.getText().toString();
                    String examiner = spnrExaminerId.getSelectedItem().toString();

                    if (fName.isEmpty() || lName.isEmpty() || tCenter.isEmpty() || examiner.isEmpty()){
                        throw new Exception("All fields must be entered.");
                    }
                    MainActivity.myAppDB.myDao().updateApplicant(applicantId, fName, lName, tCenter, examiner);

                    isAdded = true;
                }
                catch(Exception e)
                {
                    //txtApplicantInfo.setText(e.getMessage());
                    Toast.makeText(getApplicationContext(), "Add Applicant failed. \nError: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
                if(isAdded) {
                    String applicantInfo = "Applicant Id: " + applicant.getApplicantId()
                            + "\nName : " + applicant.getFirstName() + applicant.getLastName()
                            + "\nTest Center: " + applicant.getTestCenter()
                            + "\nExaminer Id: " + applicant.getExaminerId();
                    //txtApplicantInfo.setText(applicantInfo);
                    Toast.makeText(getApplicationContext(), "Applicant added successfully!", Toast.LENGTH_SHORT).show();

                    spnrApplicantId.setSelection(0);
                    txtFName.setText("");
                    txtLName.setText("");
                    txtTCenter.setText("");
                    spnrExaminerId.setSelection(0);
                }
            }
        });
        btnClear = findViewById(R.id.btn_clear);
        btnClear.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                spnrApplicantId.setSelection(0);
                spnrExaminerId.setSelection(0);
                txtFName.setText("");
                txtLName.setText("");
                txtTCenter.setText("");
                txtUpdateInfo.setText("");
            }
        });
    }
}