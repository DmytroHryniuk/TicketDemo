package com.hryniuk.dev.ui.settings;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.hryniuk.dev.R;
import com.hryniuk.dev.ui.myticket.MyticketFragment;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class BlankFragment extends Fragment {
    private int timeData;
    private String timeStrng;
    private RadioGroup radioGroup1, radioGroupA, radioGroupB, radioGroupTicket;
    private RadioButton radioButton, radioButtonA, radioButtonB, radioButtonTicket;
    private View view;
    private EditText editText, editText2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_blank, container, false);


        Button btn = view.findViewById(R.id.buttonDONE);
        radioGroup1 = view.findViewById(R.id.radioGruop1);
        editText = view.findViewById(R.id.editText);
        editText2 = view.findViewById(R.id.editText2);
        radioGroupA = view.findViewById(R.id.radioGruopA);

        radioGroupB = view.findViewById(R.id.radioGruopB);

        radioGroupTicket = view.findViewById(R.id.radioTicket);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getActivity(), "CLICK", Toast.LENGTH_SHORT).show();

                //FIRST VAR
                int selectedId = radioGroup1.getCheckedRadioButtonId();
                radioButton = (RadioButton) view.findViewById(selectedId);

                if (radioButton.getText().equals("1:30")) {
                    timeData = 5400000;
                    timeStrng = "1:30";
                } else if (radioButton.getText().equals("2:00")) {
                    timeData = 7200000;
                    timeStrng = "2:00";
                } else if (radioButton.getText().equals("2:20")) {
                    timeData = 8400000;
                    timeStrng = "2:20";
                }

                //A point VAR
                int selectedIdA = radioGroupA.getCheckedRadioButtonId();
                radioButtonA = (RadioButton) view.findViewById(selectedIdA);

                //B point VAR
                int selectedIdB = radioGroupB.getCheckedRadioButtonId();
                radioButtonB = (RadioButton) view.findViewById(selectedIdB);


                String wayDATA = "";


                if (radioButtonA.getText().equals(radioButtonB.getText())) {

                    String temp =  radioButtonA.getText().toString().substring(5);
                    wayDATA = "Zone "+temp.toUpperCase();


                } else {

                    wayDATA = radioButtonA.getText() + " - " + radioButtonB.getText();
                }
                //Valid VAR
                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.MILLISECOND, timeData);

                DateFormat df = new SimpleDateFormat("dd.MM.yyyy kk:mm");
                String date = "Valid to " + df.format(cal.getTime());

                //THIRD VAR
                String peoples = "";
                String countPeople = editText.getText().toString();
                if (countPeople.equals("1")) {
                    peoples = "1 Adult";

                } else {
                    peoples = countPeople + " Adult";

                }


                //COST VAR
                String costSTR = editText2.getText().toString().replace(".", ",");

                float costFloat = Float.valueOf(editText2.getText().toString());
                //

                DecimalFormat dfor = new DecimalFormat(
                        "##,##0.00",
                        new DecimalFormatSymbols(new Locale("pt", "BR")));

                BigDecimal value = new BigDecimal(costFloat);


                String tmpData2 = dfor.format(value.floatValue());
                String tmpData3 = tmpData2.replace(".", " ");

                //
                //DATA VAR
                float percent12 = costFloat * 0.12f;
                float rent = costFloat - percent12;
                DateFormat df2 = new SimpleDateFormat("dd.MM.yyyy\nkk:mm");
                String dataTEXT = "12 % vat. kr" + String.format("%.2f", percent12) + "\n" + "Vat.base kr" + String.format("%.2f", rent) + "\n" + "Paid by: creditcard\n" + "Purchased: " + df2.format(Calendar.getInstance().getTime());

                //TICKET VAR
                int selectedIdTicket = radioGroupTicket.getCheckedRadioButtonId();
                radioButtonTicket = (RadioButton) view.findViewById(selectedIdTicket);


                String ticketDATA = radioButtonTicket.getText().toString();


                //SEND
                MyticketFragment fragment = new MyticketFragment();
                Bundle args = new Bundle();
                args.putString("validTEXT", date);
                args.putInt("timerDATA", timeData);
                args.putString("timerSTR", timeStrng);
                args.putString("adultTEXT", peoples);
                args.putString("zoneTEXT", wayDATA);
                args.putString("kronTEXT", "kr" + tmpData3); // costSTR
                args.putFloat("kronDATA", costFloat);
                args.putString("dataTEXT", dataTEXT);
                args.putString("ticketDATA", ticketDATA);
                fragment.setArguments(args);


                //Toast.makeText(getActivity(), tmpData3, Toast.LENGTH_SHORT).show();

                getFragmentManager().beginTransaction().add(R.id.nav_host_fragment, fragment).commit();
            }
        });

        return view;
    }
}
