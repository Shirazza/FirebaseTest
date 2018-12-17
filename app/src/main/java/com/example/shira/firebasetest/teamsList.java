package com.example.shira.firebasetest;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class teamsList extends ArrayAdapter<teams> {
    private Activity context;
    private List<teams> Tlist;

    public teamsList (Activity context, List<teams> Tlist){
        super(context, R.layout.thelist, Tlist);
        this.context=context;
        this.Tlist=Tlist;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater= context.getLayoutInflater();
        View ListViewTeams = inflater.inflate(R.layout.thelist, null,true);
        TextView name= ListViewTeams.findViewById(R.id.name);
        TextView teamN= ListViewTeams.findViewById(R.id.teamN);
        teams Team= Tlist.get(position);
        name.setText(Team.getName());
        teamN.setText(""+Team.getTeamNum());

        return ListViewTeams;
    }
}