package com.example.youngajin_jiyeyo_comp304sec004_lab4_ex1;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "Tests")
public class Test {
    //testId
    //applicantId
    //examinerId
    //testresult
    //testdate
    //testrout

    // no private for threading
    @PrimaryKey
    @ColumnInfo(name = "Test_Id")
    @NonNull
    String testId;
    @ColumnInfo(name = "Applicant_Id")
    String applicantId;
    @ColumnInfo(name = "Examiner_Id")
    String examinerId;
    @ColumnInfo(name = "Test_Result")
    float testResult;
    @ColumnInfo(name = "Test_Date")
    String testDate;
    @ColumnInfo(name = "Test_Rout")
    String testRout;

    public Test(String testId, String applicantId, String examinerId, float testResult, String testDate, String testRout)
    {
        this.testId = testId;
        this.applicantId = applicantId;
        this.examinerId = examinerId;
        this.testResult = testResult;
        this.testDate = testDate;
        this.testRout = testRout;
    }

    public Test()
    {

    }

    public String getTestId() {
        return testId;
    }

    public void setTestId(String testId) {
        this.testId = testId;
    }

    public String getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(String applicantId) {
        this.applicantId = applicantId;
    }

    public String getExaminerId() {
        return examinerId;
    }

    public void setExaminerId(String examinerId) {
        this.examinerId = examinerId;
    }

    public float getTestResult() {
        return testResult;
    }

    public void setTestResult(float testResult) {
        this.testResult = testResult;
    }

    public String getTestDate() {
        return testDate;
    }

    public void setTestDate(String testDate) {
        this.testDate = testDate;
    }

    public String getTestRout() {
        return testRout;
    }

    public void setTestRout(String testRout) {
        this.testRout = testRout;
    }
}
