package com.example.youngajin_jiyeyo_comp304sec004_lab4_ex1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ViewTestInfoActivity extends AppCompatActivity {

    Button btnSearch;
   // EditText txtId, txtExaminerId, txtTestResult, txtTestDate, txtTestRout;
    Spinner spnrApplicantId;
   // TextView txtTestInfo;
    List<String> applicantIds;
    LinearLayout header;
    public TestList testList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_test_info);

        getSupportActionBar().setTitle("Test List");
        ListView list_test = (ListView)findViewById(R.id.lst_tests);
        header = findViewById(R.id.lst_header);
        spnrApplicantId = findViewById(R.id.spnr_test_applicants);
        applicantIds = MainActivity.myAppDB.myDao().getAllApplicantIds();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(ViewTestInfoActivity.this, android.R.layout.simple_spinner_dropdown_item, applicantIds);
        spnrApplicantId.setAdapter(adapter);
        //item selected
        spnrApplicantId.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(ViewTestInfoActivity.this, applicantIds.get(i), Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btnSearch = findViewById(R.id.btn_search);
        btnSearch.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //
                String applicant_id = spnrApplicantId.getSelectedItem().toString();
                List<Test> li =  MainActivity.myAppDB.myDao().getTestsByApplicanId(applicant_id);

                Test[] tests = new Test[li.size()];
                li.toArray(tests);
                String[] names = new String[li.size()];
                for (int i = 0; i < names.length; i++)
                {
                    names[i] = tests[i].getTestId();
                }

                testList = new TestList(ViewTestInfoActivity.this, names, tests);
                list_test.setAdapter(testList);

                header.setVisibility(View.VISIBLE);
            }
        });
    }
}