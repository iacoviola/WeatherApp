package com.emidev.firstclient;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
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

        binding.searchButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                Thread thread = new Thread(new Runnable() {

                    @Override
                    public void run() {
                        try  {
                            Parser xmlparser = new Parser();
                            Locator location = new Locator();
                            String plc = binding.insertCityEditText.getText().toString();
                            location.requestLocation(plc, getContext());
                            Place city = (Place) xmlparser.parseDocument(getContext());
                            binding.cityTextview.setVisibility(View.VISIBLE);
                            binding.cityTextview.setText(city.getName());
                            binding.countryTextview.setVisibility(View.VISIBLE);
                            binding.countryTextview.setText(city.getCountry());
                            binding.latitudeTextview.setVisibility(View.VISIBLE);
                            binding.latitudeTextview.setText(String.format("%s", city.getLat()));
                            binding.longitudeTextview.setVisibility(View.VISIBLE);
                            binding.longitudeTextview.setText(String.format("%s", city.getLng()));
                            binding.sunriseTextview.setVisibility(View.VISIBLE);
                            binding.sunriseTextview.setText(city.getSunrise());
                            binding.sunsetTextview.setVisibility(View.VISIBLE);
                            binding.sunsetTextview.setText(city.getSunset());
                            binding.insertCityEditText.setText("");
                            InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

                thread.start();
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}