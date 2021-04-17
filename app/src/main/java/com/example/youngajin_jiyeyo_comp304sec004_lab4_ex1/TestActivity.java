package com.example.youngajin_jiyeyo_comp304sec004_lab4_ex1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
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

public class TestActivity extends AppCompatActivity {

    Button btnAdd;
    EditText txtId, txtExaminerId, txtTestResult, txtTestDate, txtTestRout;
    Spinner spnrApplicantId, spnrTestDate, spnrTestMonth, spnrTestYear;
    TextView txtTestInfo;
    List<String> applicantIds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        getSupportActionBar().setTitle("Test Information");


        txtId = findViewById(R.id.txt_test_id);
        spnrApplicantId = findViewById(R.id.spnr_test_applicants);
        applicantIds = MainActivity.myAppDB.myDao().getAllApplicantIds();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(TestActivity.this, android.R.layout.simple_spinner_dropdown_item, applicantIds);
        spnrApplicantId.setAdapter(adapter);
        //item selected
        spnrApplicantId.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(TestActivity.this, applicantIds.get(i), Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spnrTestDate = findViewById(R.id.spnr_test_date);
        spnrTestMonth = findViewById(R.id.spnr_test_month);
        spnrTestYear = findViewById(R.id.spnr_test_year);
        txtExaminerId = findViewById(R.id.txt_test_examiner_id);
        SharedPreferences popupPref = getSharedPreferences("popupPref", MODE_PRIVATE);
        String reloadFlag = popupPref.getString("loginFinishPoint", "Log In");
        txtExaminerId.setText(reloadFlag);
        txtExaminerId.setEnabled(false);
        txtTestResult = findViewById(R.id.txt_test_result);
        //txtTestDate = findViewById(R.id.txt_test_date);
        txtTestRout = findViewById(R.id.txt_test_rout);
        txtTestInfo = findViewById(R.id.txt_test_info);

        btnAdd = findViewById(R.id.btn_add_test);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Test test = new Test();
                boolean isAdded = false;
                try {
                    //adding to the list
                    String testId = txtId.getText().toString();
                    String applicantId = spnrApplicantId.getSelectedItem().toString();
                    String examinerId = txtExaminerId.getText().toString();
                    float testResult = Float.parseFloat(txtTestResult.getText().toString());
                    String testDate = spnrTestDate.getSelectedItem().toString() + "-"
                            + spnrTestMonth.getSelectedItem().toString() + "-"
                            + spnrTestYear.getSelectedItem().toString();
                    String testRout = txtTestRout.getText().toString();

                    if (testId.isEmpty() || applicantId.isEmpty() || examinerId.isEmpty()
                            || testResult < 0 || testDate.isEmpty() || testRout.isEmpty()){
                        throw new Exception("All fields must be entered.");
                    }
                    test.setTestId(testId);
                    test.setApplicantId(applicantId);
                    test.setExaminerId(examinerId);
                    test.setTestResult(testResult);
                    test.setTestDate(testDate);
                    test.setTestRout(testRout);

                    MainActivity.myAppDB.myDao().addTest(test);

                    isAdded = true;
                }
                catch(Exception e)
                {
                    txtTestInfo.setText(e.getMessage());
                    Toast.makeText(getApplicationContext(), "Add Test failed. \nError: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
                if(isAdded) {
                    String testInfo = "Test Id " + test.getTestId()
                            + "\nApplicant Id: " + test.getApplicantId()
                            + "\nExaminer Id : " + test.getExaminerId()
                            + "\nTest Result: " + test.getTestResult()
                            + "\nTest Date: " + test.getTestDate()
                            + "\nTest Rout: " + test.getTestRout();
                    txtTestInfo.setText(testInfo);
                    Toast.makeText(getApplicationContext(), "Test added successfully!", Toast.LENGTH_SHORT).show();

                    txtId.setText("");
                    spnrApplicantId.setSelection(-1);
                    //txtExaminerId.setText("");
                    txtTestResult.setText("");
                    spnrTestDate.setSelection(0);
                    spnrTestMonth.setSelection(0);
                    spnrTestYear.setSelection(0);
                    txtTestRout.setText("");
                }
            }
        });
    }
}