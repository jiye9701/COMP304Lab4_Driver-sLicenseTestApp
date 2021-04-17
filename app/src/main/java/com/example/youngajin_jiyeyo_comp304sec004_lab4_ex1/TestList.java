package com.example.youngajin_jiyeyo_comp304sec004_lab4_ex1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.app.Activity;

public class TestList extends ArrayAdapter {
    private Test[] testList;
    private Activity context;

    public TestList(Activity context, String[] names, Test[] testList){
        super(context, R.layout.test_row, names);
//            public myTestsListAdapter(Activity context, String[] names, Test[] testList){
//            super(context, R.layout.test_row, names);
        this.context = context;
        this.testList = testList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View row = convertView;

        LayoutInflater inflater = context.getLayoutInflater();
        if(convertView == null){
            row = inflater.inflate(R.layout.test_row, null, true);
        }
        TextView txtId = (TextView)row.findViewById(R.id.item_test_id);
        TextView txtResult = (TextView)row.findViewById(R.id.item_test_result);
        TextView txtDate = (TextView)row.findViewById(R.id.item_test_date);
        TextView txtRout = (TextView)row.findViewById(R.id.item_test_rout);
//        String homeDescription = "No. " + homeList[position].getHomeNo()
//                + "\nHome type: " + homeList[position].getStringHomeType()
//                + "\nAddress: " + homeList[position].getAddress();
        Test test = testList[position];
        txtId.setText(test.getTestId());
        try {
            txtResult.setText(Float.toString(test.getTestResult()));
        }catch(Exception e)
        {
            txtResult.setText("N/A");
        }
        txtDate.setText(test.getTestDate());
        txtRout.setText(test.getTestRout());
        return row;
    }
}
