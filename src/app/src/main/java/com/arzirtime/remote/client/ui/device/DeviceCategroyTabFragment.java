package com.arzirtime.remote.client.ui.device;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arzirtime.remote.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DeviceCategroyTabFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DeviceCategroyTabFragment extends Fragment {

    private View view;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DeviceCategroyTabFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DeviceCategroyTabFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DeviceCategroyTabFragment newInstance(String param1, String param2) {
        DeviceCategroyTabFragment fragment = new DeviceCategroyTabFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return view = inflater.inflate(R.layout.fragment_device_categroy_tab, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onStart() {
        super.onStart();

/*        Thread myThree =  new Thread(new Runnable() {
            @Override
            public void run() {
                //文字信息
                TextView textView = view.findViewById(R.id.test_text);
                if(textView != null) {
                    textView.setText(copyText(textView.getText().toString()));
                }
            }
        });

        myThree.start();*/
    }

    private String copyText(String text) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 129; i++) {
            stringBuilder.append(text);
        }
        return stringBuilder.toString();
    }
}
