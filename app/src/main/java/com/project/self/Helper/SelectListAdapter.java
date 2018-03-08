package com.project.self.Helper;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.project.self.R;


/**
 * Created by MMondal on 2/21/2018.
 */

public class SelectListAdapter extends ArrayAdapter<FieldCheck> {
    private FieldCheck[] fieldPreset;
    private Context context = null;
    private SharedPreferences sharedPreferences;

    public SelectListAdapter(Context context, FieldCheck[] resource, SharedPreferences sharedPreferences) {
        super(context,R.layout.select_list_entry,resource);
        // TODO Auto-generated constructor stub
        this.context = context;
        this.fieldPreset = resource;
        this.sharedPreferences = sharedPreferences;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        convertView = inflater.inflate(R.layout.select_list_entry, parent, false);
        TextView name = (TextView) convertView.findViewById(R.id.fieldName);
        CheckBox cb = (CheckBox) convertView.findViewById(R.id.checkBox);
        name.setText(fieldPreset[position].getName());
        if( fieldPreset[position].getEnabled())
            cb.setChecked(true);
        else
            cb.setChecked(false);
        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("PERSONA-"+fieldPreset[position].getName(),isChecked);
                editor.commit();
            }
        });
        return convertView;
    }
}


