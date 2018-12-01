package robertcurtis.jingnicai.sidiamadou.tutor.tutorapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

import robertcurtis.jingnicai.sidiamadou.tutor.tutorapp.util.DBOperator;
import robertcurtis.jingnicai.sidiamadou.tutor.tutorapp.util.TutorSearchAdapter;
import robertcurtis.jingnicai.sidiamadou.tutor.tutorapp.util.idObject;

public class TutorSearchActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView TutorSearchRecyclerView;
    private RecyclerView.Adapter TutorSearchAdapter;
    private RecyclerView.LayoutManager TutorSearchLayoutManager;

    private String StudentID;
    private String TutorID;

    Button searchBtn;
    EditText searchEditText;
    LinearLayout recyclerViewHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_search);
        searchBtn = findViewById(R.id.TutorSkillSearchButton);
        searchBtn.setOnClickListener(this);
        searchEditText = findViewById(R.id.TutorSearchEditText);
        recyclerViewHolder = findViewById(R.id.TutorSearchRecyclerViewHolder);


        Intent i = getIntent();
        Bundle extras = i.getExtras();

        StudentID = extras.getString("StudentID");
        TutorID = extras.getString("TutorID");

    }


    public void onClick(View v)
    {
        int id=v.getId();
        if(id==R.id.TutorSkillSearchButton) {
            ArrayList<TutorSearchItem> TutorSearchList = new ArrayList<>();
            String enteredSearch = searchEditText.getText().toString();

            String sql = "select Tutor.tutor_FName, Tutor.tutor_LName, Major.major_Name from Tutor inner join HasSkills on Tutor.tutorID=HasSkills.tutorID inner join SkillSet on HasSkills.skillID=SkillSet.skillID left join Major on Tutor.majorID=Major.majorID where SkillSet.skill_Name=" + "'" + enteredSearch + "'";

            DBOperator op = DBOperator.getInstance();



            Cursor cursor = op.execQuery(sql);


            while(cursor.moveToNext()) {
                TutorSearchList.add(new TutorSearchItem(cursor.getString(0) + " " +cursor.getString(1), cursor.getString(2)));
            }


            TutorSearchRecyclerView = findViewById(R.id.TutorSearchRecyclerView);
            TutorSearchRecyclerView.setHasFixedSize(true);
            TutorSearchLayoutManager = new LinearLayoutManager(this);
            TutorSearchRecyclerView.setLayoutManager(TutorSearchLayoutManager);



            TutorSearchAdapter = new TutorSearchAdapter(TutorSearchList);


            TutorSearchRecyclerView.setAdapter(TutorSearchAdapter);







        }
    }

}
