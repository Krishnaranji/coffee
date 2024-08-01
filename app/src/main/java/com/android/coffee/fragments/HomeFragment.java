package com.android.coffee.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.android.coffee.R;
import com.android.coffee.database.AmericanaDatabase;
import com.android.coffee.database.AmericanaEntity;

public class HomeFragment extends Fragment implements View.OnClickListener {

    public HomeFragment() {
    }

    TextView txt_drink_name;
    Button btn_demo, btn_cancel, btn_update;
    EditText edt_powder, edt_decoction;
    ImageView img_edit;
    LinearLayout llout_buttons;
    AmericanaDatabase db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String strDrink = getArguments().getString("drinkName");
        View view = inflater.inflate(R.layout.home_fragment, container, false);
        txt_drink_name = view.findViewById(R.id.txt_drink_name);
        img_edit = view.findViewById(R.id.img_edit);
        btn_cancel = view.findViewById(R.id.btn_cancel);
        btn_demo = view.findViewById(R.id.btn_demo);
        btn_update = view.findViewById(R.id.btn_update);
        llout_buttons = view.findViewById(R.id.llout_buttons);
        edt_decoction = view.findViewById(R.id.edt_decoction);
        edt_powder = view.findViewById(R.id.edt_powder);

        db = AmericanaDatabase.getInstance(getActivity());

        edt_decoction.setEnabled(false);
        edt_powder.setEnabled(false);
        llout_buttons.setVisibility(View.GONE);
        txt_drink_name.setText(strDrink);

        edt_decoction.setBackgroundColor(Color.TRANSPARENT);
        edt_powder.setBackgroundColor(Color.TRANSPARENT);

        btn_update.setOnClickListener(this);
        btn_demo.setOnClickListener(this);
        btn_cancel.setOnClickListener(this);
        img_edit.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_edit:
                int paddingPixel = 20;
                float density = getResources().getDisplayMetrics().density;
                int paddingDp = (int)(paddingPixel * density);
                int paddingTop = paddingDp/3;

                Log.e("paddings",paddingDp+"--"+paddingTop);

                edt_decoction.setEnabled(true);
                edt_powder.setEnabled(true);
                edt_powder.setBackground(getResources().getDrawable(R.drawable.rectangle));
                edt_powder.setPadding(paddingDp, paddingTop, paddingDp, paddingTop);
                edt_decoction.setBackground(getResources().getDrawable(R.drawable.rectangle));
                edt_decoction.setPadding(paddingDp, paddingTop, paddingDp, paddingTop);
                llout_buttons.setVisibility(View.VISIBLE);
                btn_demo.setVisibility(View.GONE);
                break;

            case R.id.btn_update:
                validateData();

                break;

            case R.id.btn_cancel:
                edt_decoction.setEnabled(false);
                edt_powder.setEnabled(false);
                llout_buttons.setVisibility(View.GONE);
                btn_demo.setVisibility(View.VISIBLE);
                edt_decoction.setBackgroundColor(Color.TRANSPARENT);
                edt_powder.setBackgroundColor(Color.TRANSPARENT);
                break;
        }
    }

    private void validateData() {
        if (edt_powder.getText().toString().isEmpty()) {
            Toast.makeText(getActivity(), "Please Enter Powder", Toast.LENGTH_SHORT).show();
        } else if (edt_decoction.getText().toString().isEmpty()) {
            Toast.makeText(getActivity(), "Please Enter Decoction", Toast.LENGTH_SHORT).show();
        } else {

            insertTheDetailsToDb();
            edt_decoction.setEnabled(false);
            edt_powder.setEnabled(false);
            llout_buttons.setVisibility(View.GONE);
            btn_demo.setVisibility(View.VISIBLE);
            edt_decoction.setBackgroundColor(Color.TRANSPARENT);
            edt_powder.setBackgroundColor(Color.TRANSPARENT);
        }
    }

    private void insertTheDetailsToDb() {

        AmericanaEntity entity = new AmericanaEntity(edt_powder.getText().toString(), edt_decoction.getText().toString());
        db.americanoDAO().insertAmericana(entity);
    }
}
