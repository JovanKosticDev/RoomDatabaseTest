package com.example.roomdatabasetest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private UniversityDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

        db = UniversityDatabase.getInstance(this);

//        Student sara = new Student();
//        sara.setId(4);
//        db.studentDao().delteSingleStudent(sara);
        //db.studentDao().insertSingleStundent(new Student("Sara", "sara@gmail.com", "45214451" ));

//        Student felix = new Student();
//        felix.setId(2);
//        felix.setName("Felix Mustermann");
//        felix.setEmail("felix@mustermann.de");
//        felix.setPhoneNumber("123123131");
//        db.studentDao().updateSingleStudent(felix);
//
//        List<Student> students = db.studentDao().getStudentsByName("Max");

        LiveData<List<Student>> liveStudents = db.studentDao().getAllStudentsLiveData();
        liveStudents.observe(this, new Observer<List<Student>>() {
            @Override
            public void onChanged(List<Student> students) {
                String text = "";
                for (Student s : students) {
                    text += "ID: " + s.getId() + "\nName: " + s.getName() + "\nEmail: " + s.getEmail() +
                            "\nPhone: " + s.getPhoneNumber() + "\n***********\n";
                }

                textView.setText(text);
            }
        });
        new TestAsyncTask().execute();
    }

        private class TestAsyncTask extends AsyncTask<Void, Void, Void>{
            @Override
            protected Void doInBackground(Void... voids) {

                ArrayList<Student> students = new ArrayList<>();
                students.add(new Student("Peter", "peter@gmail.com", "874551"));
                students.add(new Student("Florian", "florian@gmail.com", "545721"));
                students.add(new Student("Melanie", "melanie@gmail.com", "45212"));

                for(Student s : students){
                    SystemClock.sleep(2000);
                    db.studentDao().insertSingleStundent(s);
                }

                return null;
            }
        }



}