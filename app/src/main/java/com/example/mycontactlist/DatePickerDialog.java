package com.example.mycontactlist;

import android.os.Bundle;
import androidx.fragment.app.DialogFragment;
import java.util.Calendar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import androidx.annotation.NonNull;

public class DatePickerDialog extends DialogFragment {
        Calendar selectedDate;

        public interface SaveDateListener {
            void didFinishDatePickerDialog(Calendar selectedTime);
        }

        public DatePickerDialog () {
        }

        @Override
        public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            final View view = inflater.inflate(R.layout.select_date, container);

            getDialog().setTitle("Select Date");
            selectedDate = Calendar.getInstance();

            final CalendarView cv = view.findViewById(R.id.calendarView);
            cv.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                @Override
                public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                    selectedDate.set(year, month, dayOfMonth);
                }
            });

            Button saveButton = view.findViewById(R.id.buttonSelect);
            saveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    saveItem(selectedDate);
                }
            });
            Button cancelButton = view.findViewById(R.id.buttonCancel);
            cancelButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getDialog().dismiss();
                }
            });
            return view;
        }

        private void saveItem (Calendar selectedTime) {
            SaveDateListener activity = (SaveDateListener) getActivity();
            activity.didFinishDatePickerDialog(selectedTime);
            getDialog().dismiss();
        }
    }

