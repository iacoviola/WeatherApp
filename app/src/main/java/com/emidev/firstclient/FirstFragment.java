package com.emidev.firstclient;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.emidev.firstclient.databinding.FragmentFirstBinding;

import java.io.File;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                Parser xmlparser = new Parser();
                Locator location = new Locator();
                Thread thread = new Thread(new Runnable() {

                    @Override
                    public void run() {
                        try  {
                            String plc = binding.editTextTextPersonName.getText().toString();
                            location.requestLocation(plc, getContext());
                            Place city = (Place) xmlparser.parseDocument(getContext());
                            String text = city.getName() + " sunrise:" + city.getSunrise() + " sunset:" + city.getSunset();
                            binding.textviewFirst.setText(text);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

                thread.start();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}