package com.hryniuk.dev.ui.myticket;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.hryniuk.dev.R;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyticketFragment extends Fragment {

    private int timerCount;
    public TextView validToTEXT, zoneTEXT, timerTEXT, adultTEXT, kron1TEXT, kron2TEXT, dataTEXT, ticketTEXT;
    public ImageView pictureTEXT;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_myticket, container, false);

        validToTEXT = root.findViewById(R.id.validToTEXT);
        zoneTEXT = root.findViewById(R.id.zoneTEXT);
        timerTEXT = root.findViewById(R.id.timerTEXT);
        adultTEXT = root.findViewById(R.id.adultTEXT);
        kron1TEXT = root.findViewById(R.id.kron1TEXT);
        kron2TEXT = root.findViewById(R.id.kron2TEXT);
        dataTEXT = root.findViewById(R.id.dataTEXT);
        ticketTEXT = root.findViewById(R.id.ticketTEXT);
        pictureTEXT = root.findViewById(R.id.pictureTEXT);


        if (getArguments() != null) {
            validToTEXT.setText(getArguments().getString("validTEXT"));
            zoneTEXT.setText(getArguments().getString("zoneTEXT"));
            timerTEXT.setText(getArguments().getString("timerSTR"));
            timerCount = getArguments().getInt("timerDATA");
            adultTEXT.setText(getArguments().getString("adultTEXT"));
            kron1TEXT.setText(getArguments().getString("kronTEXT"));
            kron2TEXT.setText(getArguments().getString("kronTEXT"));
            dataTEXT.setText(getArguments().getString("dataTEXT"));
            ticketTEXT.setText(getArguments().getString("ticketDATA"));

            if (getArguments().getString("ticketDATA").equals("Single ticket")){
                pictureTEXT.setBackgroundResource(R.drawable.ic_bus);
            }
            else {
                pictureTEXT.setBackgroundResource(R.drawable.ic_boat);
            }


        } else {
            validToTEXT.setText("Valid to 2/26/18 10:08");
            zoneTEXT.setText("Zone TROMSØ - Zone Vikran");
            timerTEXT.setText("02:20:00");
            timerCount = 8400000;
            adultTEXT.setText("1 Adult");
            kron1TEXT.setText("kr72.00");
            kron2TEXT.setText("kr72.00");
            dataTEXT.setText("12 % vat. kr7.71\nVat.base kr64.29\nPaid by: Travel account\nPurchused: 2/26/18, 07:53");
            ticketTEXT.setText("Single ticket");
            pictureTEXT.setBackgroundResource(R.drawable.ic_bus);


        }


        return root;
    }

    @Override
    public void onStart() {


        new CountDownTimer(timerCount, 1000) {

            public void onTick(long millisUntilFinished) {
                // timerTEXT.setText(String.valueOf(millisUntilFinished/ 1000));
                long millis = millisUntilFinished % 1000;
                long second = (millisUntilFinished / 1000) % 60;
                long minute = (millisUntilFinished / (1000 * 60)) % 60;
                long hour = (millisUntilFinished / (1000 * 60 * 60)) % 24;
                timerTEXT.setText(String.format("%02d:%02d:%02d", hour, minute, second, millis));
            }

            public void onFinish() {

            }

        }.start();



        super.onStart();
    }
}
