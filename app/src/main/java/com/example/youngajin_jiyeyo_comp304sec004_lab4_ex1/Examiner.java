package com.example.youngajin_jiyeyo_comp304sec004_lab4_ex1;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Examiners")
public class Examiner {
    //examinerId
    //firstname
    //lastname
    //testcenter
    //password
    @PrimaryKey
    @ColumnInfo(name = "Examiner_Id")
    @NonNull
     String examinerId;
    @ColumnInfo(name = "First_Name")
    String firstName;
    @ColumnInfo(name = "Last_Name")
    String lastName;
    @ColumnInfo(name = "Test_Center")
    String testCenter;
    @ColumnInfo(name = "Password")
    String password;

    public Examiner(String examinerId, String firstName, String lastName, String testCenter, String password)
    {
        this.examinerId = examinerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.testCenter = testCenter;
        this.password = password;
    }
    public Examiner(){

    }

    public String getExaminerId() {
        return examinerId;
    }

    public void setExaminerId(String examinerId) {
        this.examinerId = examinerId;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
