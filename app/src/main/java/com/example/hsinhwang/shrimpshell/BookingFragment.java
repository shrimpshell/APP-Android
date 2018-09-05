package com.example.hsinhwang.shrimpshell;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.hsinhwang.shrimpshell.Classes.ReservationDate;
import java.util.Calendar;

public class BookingFragment extends Fragment {
    private TextView tvFirstYearSelected, tvFirstDaySelected, tvFirstMonSelected, tvFirstWeekSelected,
            tvLastYearSelected, tvLastDaySelected, tvLastMonSelected, tvLastWeekSelected,
            tvAdultQuantity, tvChildQuantity;
    private ImageButton ibtAdultMinus, ibtAdultplus, ibtChildMinus, ibtChildplus;
    private String weekName;
    private Calendar calendar = Calendar.getInstance();
    private static final String TAG = "Debug";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_booking, container, false);
        handleViews(view);
        return view;
    }

    private void handleViews(View view) {
        FloatingActionButton fabBooking = view.findViewById(R.id.fabBooking);
        fabBooking.setOnClickListener(BookingFragmentChange_Listener);
        tvFirstYearSelected = view.findViewById(R.id.tvFirstYearSelected);
        tvFirstDaySelected = view.findViewById(R.id.tvFirstDaySelected);
        tvFirstMonSelected = view.findViewById(R.id.tvFirstMonSelected);
        tvFirstWeekSelected = view.findViewById(R.id.tvFirstWeekSelected);
        tvLastYearSelected = view.findViewById(R.id.tvLastYearSelected);
        tvLastDaySelected = view.findViewById(R.id.tvLastDaySelected);
        tvLastMonSelected = view.findViewById(R.id.tvLastMonSelected);
        tvLastWeekSelected = view.findViewById(R.id.tvLastWeekSelected);
        tvAdultQuantity = view.findViewById(R.id.tvAdultQuantity);
        tvChildQuantity = view.findViewById(R.id.tvChildQuantity);
        ibtAdultMinus = view.findViewById(R.id.ibtAdultMinus);
        ibtAdultplus = view.findViewById(R.id.ibtAdultPlus);
        ibtChildMinus = view.findViewById(R.id.ibtChildMinus);
        ibtChildplus = view.findViewById(R.id.ibtChildPlus);

        showFirstDate();
        showLastDate();

        ibtAdultMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int adultQuantity = Integer.parseInt(tvAdultQuantity.getText().toString());
                if (adultQuantity != 0) {
                    tvAdultQuantity.setText(String.valueOf(adultQuantity - 1));
                }
            }
        });

        ibtAdultplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int adultQuantity = Integer.parseInt(tvAdultQuantity.getText().toString());
                tvAdultQuantity.setText(String.valueOf(adultQuantity + 1));
            }
        });

        ibtChildMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int childQuantity = Integer.parseInt(tvChildQuantity.getText().toString());
                if (childQuantity > 0) {
                    tvChildQuantity.setText(String.valueOf(childQuantity - 1));
                }
            }
        });

        ibtChildplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int childQuantity = Integer.parseInt(tvChildQuantity.getText().toString());
                tvChildQuantity.setText(String.valueOf(childQuantity + 1));
            }
        });
    }

    private void showFirstDate() {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int week = calendar.get(Calendar.DAY_OF_WEEK);

        tvFirstYearSelected.setText(String.valueOf(year) + " 年");
        tvFirstDaySelected.setText(String.valueOf(day));
        tvFirstMonSelected.setText(" " + String.valueOf(month) + " 月");
        tvFirstWeekSelected.setText(changeWeekName(week));
    }

    private void showLastDate() {
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        int lastYear = calendar.get(Calendar.YEAR);
        int lastMonth = calendar.get(Calendar.MONTH) + 1;
        int lastDay = calendar.get(Calendar.DAY_OF_MONTH);
        int lastWeek = calendar.get(Calendar.DAY_OF_WEEK);

        tvLastYearSelected.setText(String.valueOf(lastYear) + " 年");
        tvLastDaySelected.setText(String.valueOf(lastDay));
        tvLastMonSelected.setText(" " + String.valueOf(lastMonth) + " 月");
        tvLastWeekSelected.setText(changeWeekName(lastWeek));
    }

    public String changeWeekName(int w) {
        switch (w) {
            case Calendar.SUNDAY:
                weekName = "週日";
                break;
            case Calendar.MONDAY:
                weekName = "週一";
                break;
            case Calendar.TUESDAY:
                weekName = "週二";
                break;
            case Calendar.WEDNESDAY:
                weekName = "週三";
                break;
            case Calendar.THURSDAY:
                weekName = "週四";
                break;
            case Calendar.FRIDAY:
                weekName = "週五";
                break;
            case Calendar.SATURDAY:
                weekName = "週六";
        }
        return weekName;
    }

    FloatingActionButton.OnClickListener BookingFragmentChange_Listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getActivity(), CalendarActivity.class);
            ReservationDate date = new ReservationDate(tvFirstYearSelected, tvFirstDaySelected,
                    tvFirstMonSelected, tvFirstWeekSelected, tvLastYearSelected, tvLastDaySelected,
                    tvLastMonSelected, tvLastWeekSelected);
//            Bundle bundle = new Bundle();
//            bundle.putSerializable("reservationDate", date);
//            intent.putExtras(bundle);
            startActivity(intent);
        }
    };
}