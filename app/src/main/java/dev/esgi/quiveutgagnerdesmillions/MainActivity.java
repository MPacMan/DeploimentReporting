package dev.esgi.quiveutgagnerdesmillions;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button btn_start, btn_class,btn_about,btn_quit,ButtonPanier,ButtonCom;

    public static int score = 0;
    public static ArrayList<Question> questions = new ArrayList<>();
    public static Question q1 = new Question("What is the capital of France","Italy","Lyon",
            "Nantes","Paris");
    public static Question q2 = new Question("ESGI means","Ecole Superieure Gravité Informatique",
            "Ecole Suberbe Genie Informatique","Ecole Superieure Genie Intelectuel",
            "Ecole Superieure Genie Informatique");
    public static Question q3 = new Question("What is the name of this app creator ?","Luis","Pierre",
            "Miyuki","Ivan");
    private FirebaseAnalytics mFirebaseAnalytics;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);


        btn_start = findViewById(R.id.btn_start);
        btn_class = findViewById(R.id.btn_class);
        btn_about = findViewById(R.id.btn_about);
        btn_quit = findViewById(R.id.btn_quit);
        ButtonPanier=findViewById(R.id.ButtonPanier);
        ButtonCom=findViewById(R.id.ButtonCom);


        Button crashButton = new Button(this);
        crashButton.setText("Crash!");
        crashButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                throw new RuntimeException("Test Crash");

            }
        });
        addContentView(crashButton, new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        ));
        ButtonPanier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "ButtonPanier");
                bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "Panier-activity");
                mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
                //Intent i = new Intent(MainActivity.this, About.class);
                //startActivity(i);
                //finish();
            }
        });
        ButtonCom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "ButtonCom");
                bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "Commander-activity");
                mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
                //Intent i = new Intent(MainActivity.this, About.class);
                //startActivity(i);
                //finish();
            }
        });
        btn_quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Bundle bundle = new Bundle();
                bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "btn-exit");
                bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "StopGame_Activity");
                mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle(getResources().getString(R.string.pop_title))

                        .setPositiveButton(getResources().getString(R.string.pop_yes), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                mFirebaseAnalytics.setUserProperty("Retour_sur_application","Oui");
                                finish();
                            }
                        })
                        .setNegativeButton(getResources().getString(R.string.pop_no), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .setCancelable(true)
                        .show();
            }

        });

        btn_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "btn-Apropos");
                bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "About-activity");
                mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
                Intent i = new Intent(MainActivity.this, About.class);
                startActivity(i);
                finish();
            }
        });


        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = new Bundle();
                bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "btn-start");
                bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "StartGame-activity");
                mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

                questions.clear();
                questions.add(q1);
                questions.add(q2);
                questions.add(q3);
                Intent i = new Intent(MainActivity.this, Game.class);
                startActivity(i);
                finish();

            }
        });
    }

}
