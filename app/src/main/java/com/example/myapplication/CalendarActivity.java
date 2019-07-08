package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class CalendarActivity extends Fragment {

    private CalendarView mCalendarView;
    private TextView Date;
    private EditText schedule;
    private Button btn_add;
    private Context mContext;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View add_schedule = inflater.inflate(R.layout.add_schedule,container,false);//        mCalendarView = (CalendarView)((AppCompatActivity)getActivity()).findViewById(R.id.calender);

        mCalendarView = (CalendarView)add_schedule.findViewById(R.id.calender);
        Date = (TextView)add_schedule.findViewById(R.id.date);
        schedule = (EditText)add_schedule.findViewById(R.id.schedule);
        btn_add = (Button)add_schedule.findViewById(R.id.btn_add);

        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int i, int i1, int i2) {
                String date = i + "/" + (i1 + 1) + "/" + i2;
                Date.setText(date);
            }
        });
        btn_add.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), NavigationThree.class);
                startActivity(intent);

                if(schedule.getText().toString().length() ==0){
                    Toast.makeText(mContext.getApplicationContext(),"Add schedule",Toast.LENGTH_SHORT).show();
                }
                else{
                    String schedule_content = schedule.getText().toString();
                }
            }
        });

        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
