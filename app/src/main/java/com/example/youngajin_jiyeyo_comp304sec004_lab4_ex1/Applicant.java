package com.example.youngajin_jiyeyo_comp304sec004_lab4_ex1;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

//
@Entity(tableName = "Applicants")
public class Applicant {
    //applicantId
    //firstname
    //lastname
    //testcenter
    //examinerId
    @PrimaryKey
    @ColumnInfo(name = "Applicant_Id")
    @NonNull
    private String applicantId;
    @ColumnInfo(name = "First_Name")
    private String firstName;
    @ColumnInfo(name = "Last_Name")
    private String lastName;
    @ColumnInfo(name = "Test_Center")
    private String testCenter;
    @ColumnInfo(name = "Examiner_Id")
    private String examinerId;

    public Applicant(String applicantId, String firstName, String lastName, String testCenter, String examinerId)
    {
        this.applicantId = applicantId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.testCenter = testCenter;
        this.examinerId = examinerId;
    }

    public Applicant(){
        applicantId = "0";
        //empty
    }

    public String getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(String applicantId) {
        this.applicantId = applicantId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTestCenter() {
        return testCenter;
    }

    public void setTestCenter(String testCenter) {
        this.testCenter = testCenter;
    }

    public String getExaminerId() {
        return examinerId;
    }

    public void setExaminerId(String examinerId) {
        this.examinerId = examinerId;
    }
}
