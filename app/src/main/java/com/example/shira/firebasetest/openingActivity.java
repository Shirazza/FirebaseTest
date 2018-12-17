package com.example.shira.firebasetest;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class openingActivity extends AppCompatActivity {

    EditText thePass, Name, teamNum;
    String pass, theName, theTN, id;
    Intent getP;
    DatabaseReference ref;
    List<teams> TeamsList;
    ListView list;
    teams TEAM;

    @Override
    protected void onStart() {
        super.onStart();
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                TeamsList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    teams Team=  snapshot.getValue(teams.class);
                    TeamsList.add(Team);
                }

                teamsList adp=  new teamsList(openingActivity.this,TeamsList );
                list.setAdapter(adp);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opening);
        thePass= (EditText) findViewById(R.id.thePass);
        Name= (EditText) findViewById(R.id.Name);
        teamNum= (EditText) findViewById(R.id.teamNum);
        list= (ListView) findViewById(R.id.list);

        TeamsList= new ArrayList<>();

        getP= getIntent();
        pass= getP.getStringExtra("new password");
        thePass.setText(""+ pass);

        ref= FirebaseDatabase.getInstance().getReference("team");
        registerForContextMenu(list);

        finish();
    }

    public void next(View view) {
        getP.putExtra("back password", pass);
        setResult(RESULT_OK, getP);
        finish();

        theName= Name.getText().toString();
        theTN= teamNum.getText().toString();
        if (!theName.isEmpty()&& !theTN.isEmpty()){
            id= ref.push().getKey();
            teams team= new teams(id, Integer.parseInt(theTN), theName);
            ref.child(id).setValue(team);
        }

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        if(v.getId()== R.id.list){
            ListView lv= (ListView) v;
            AdapterView.AdapterContextMenuInfo acmi= (AdapterView.AdapterContextMenuInfo) menuInfo;
            TEAM= (teams) lv.getItemAtPosition(acmi.position);
            menu.add("Edit");
            menu.add("Remove");
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        String title= item.getTitle().toString();
        if(title.equals("Edit")){
            Intent t= new Intent (this, Edit.class);
            t.putExtra("id", this.TEAM.getId());
            t.putExtra("name", this.TEAM.getName());
            t.putExtra("team number", this.TEAM.getTeamNum());
            startActivity(t);
        }
        else if (title.equals("Remove")){
            ref.child(this.TEAM.getId()).removeValue();
        }
        return super.onContextItemSelected(item);
    }
}
