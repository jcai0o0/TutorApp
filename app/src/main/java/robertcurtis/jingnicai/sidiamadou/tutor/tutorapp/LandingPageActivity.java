package robertcurtis.jingnicai.sidiamadou.tutor.tutorapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class LandingPageActivity extends AppCompatActivity implements OnClickListener {
    private Bundle extras;

    Button SkillPageBtn, TutorSearchBtn, StudentSearchBtn, MyStudentsBtn, MyTutorsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);
        SkillPageBtn = this.findViewById(R.id.SkillPageButton);
        TutorSearchBtn = this.findViewById(R.id.TutorSkillSearchButton);
        StudentSearchBtn = this.findViewById(R.id.StudentSearchButton);
        MyStudentsBtn = this.findViewById(R.id.MyStudentsButton);
        MyTutorsBtn = this.findViewById(R.id.MyTutorsButton);

        SkillPageBtn.setOnClickListener(this);
        TutorSearchBtn.setOnClickListener(this);
        StudentSearchBtn.setOnClickListener(this);
        MyStudentsBtn.setOnClickListener(this);
        MyTutorsBtn.setOnClickListener(this);


        Intent i = getIntent();
        extras = i.getExtras();

        // hide button based on user category
        if (i.hasExtra("StudentID") && !extras.getString("StudentID").equals("null") && i.hasExtra("TutorID") && extras.getString("TutorID").equals("null")) {
            StudentSearchBtn.setVisibility(View.GONE);
            MyStudentsBtn.setVisibility(View.GONE);
        } else if (i.hasExtra("TutorID") && !extras.getString("TutorID").equals("null") && i.hasExtra("StudentID") && extras.getString("StudentID").equals("null")) {
            TutorSearchBtn.setVisibility(View.GONE);
            MyTutorsBtn.setVisibility(View.GONE);
        }
    }

    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.SkillPageButton) {
            Intent i = new Intent(this, SkillPageActivity.class);
            i.putExtras(extras);
            this.startActivity(i);
        } else if (id == R.id.TutorSkillSearchButton) {
            Intent i = new Intent(this, TutorSearchActivity.class);
            i.putExtras(extras);
            this.startActivity(i);

        } else if (id == R.id.StudentSearchButton) {
            Intent i = new Intent(this, StudentSearchActivity.class);
            i.putExtras(extras);
            this.startActivity(i);

        } else if (id == R.id.MyStudentsButton) {
            Intent i = new Intent(this, MyStudentsActivity.class);
            i.putExtras(extras);
            this.startActivity(i);

        } else if (id == R.id.MyTutorsButton) {
            Intent i = new Intent(this, MyTutorsActivity.class);
            i.putExtras(extras);
            this.startActivity(i);

        }
    }
}
