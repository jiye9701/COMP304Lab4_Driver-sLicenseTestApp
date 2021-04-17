package com.example.youngajin_jiyeyo_comp304sec004_lab4_ex1;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface MyDao {

    //Applicants
    @Insert
    void addApplicant(Applicant applicant);

    //Register overlap
//    @Insert
//    void registerUser (Test test);

    @Query("select * from Applicants")
    public List<Applicant> getAllApplicants();

    @Query("select Applicant_Id from Applicants")
    public List<String> getAllApplicantIds();

    @Query("select * from Applicants where Applicant_Id = :applicant_id")
    public Applicant getAllApplicantById(String applicant_id);

    @Query("UPDATE Applicants " +
            "SET First_Name = :firstName, Last_Name= :lastName, Test_Center =:testCenter, Examiner_Id =:examinerId " +
            "WHERE Applicant_Id =:applicantId")
    void updateApplicant(String applicantId, String firstName, String lastName, String testCenter, String examinerId);

    //Tests
    @Insert
    public void addTest(Test test);

    @Query("select * from Tests")
    public List<Test> getAllTests();

    @Query("select * from Tests where Applicant_Id = :applicant_id")
    public List<Test> getTestsByApplicanId(String applicant_id);

    //Examiners > typo modified
    @Insert
    public void addExaminer(Examiner examiner);

    @Query("select * from Examiners")
    public List<Examiner> getAllExaminers();

    @Query("select Examiner_Id from Examiners")
    public List<String> getAllExaminerIds();

    @Query("select Password from Examiners where Examiner_Id = :examiner_id")
    public String getPassword(String examiner_id);

    //@Query("select test")

}
