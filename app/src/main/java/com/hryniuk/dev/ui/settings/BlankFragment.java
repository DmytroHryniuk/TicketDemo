package com.hryniuk.dev.ui.settings;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.hryniuk.dev.R;
import com.hryniuk.dev.ui.myticket.MyticketFragment;

public class BlankFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_blank, container, false);


        Button btn = view.findViewById(R.id.buttonDONE);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getActivity(), "CLICK", Toast.LENGTH_SHORT).show();

                MyticketFragment fragment = new MyticketFragment();
                Bundle args = new Bundle();
                args.putString("adultTEXT", "2 Adults");
                fragment.setArguments(args);

                getFragmentManager().beginTransaction().add(R.id.nav_host_fragment, fragment).commit();
            }
        });

        return view;
    }
}
