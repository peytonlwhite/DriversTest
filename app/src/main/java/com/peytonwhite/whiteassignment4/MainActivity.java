//Peyton White
//Assignment 4
//10 MC questions 3 acts


package com.peytonwhite.whiteassignment4;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements
                                    View.OnClickListener, RadioGroup.OnCheckedChangeListener
{

    //keeps track of current question (array of strings below)
    int currentQuestion = 0;
    //can only get to 10 bc 10 questions

    //use this to see what question user is on to see what the correct answer is with 10 if states
    int currentQuestionCount = 1;
    int currentImage = 0;
    //keeps track of questions answered correctly
    int currentRight = 0;
    int currentWrong = 0;
    boolean q1 = false;

    //10 bools to keep track of questions
    boolean[] questions = {false,false,false,false,false,false,false,false,false,false};




    String[] choices = {"Stop sign", "Red sign", "Stop later", "Never stop",
            "Yield sign", "Stop Sign", "slow down", "go faster",
            "stop sign", "bad weather", "traffic hazard", "roll down window",
            "Police ahead", "stop light ahead", "slow down", "electric out",
            "No U turn", "No drifting", "No turning", "No left turn",
            "fire hazard", "no stop signs", "bad traffic", "no smoking",
            "no people", "no parking", "animals around", "no dogs",
            "only left turns", "no right turn", "No left turn", "no turns",
            "no houses ahead", "no roads head", "zombies ahead", "stop here",
            "Only straight ahead", "Curvy road ahead", "slippery road ahead", "Use brakes ahead"};


    int [] pictureId = {R.drawable.stop,R.drawable.yield,R.drawable.traffichazard,
                        R.drawable.stoplight,R.drawable.nouturn,R.drawable.nosmoking,
                        R.drawable.noparking,R.drawable.noleftturn,R.drawable.deadend,
                        R.drawable.curveyroad};


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findViewById(R.id.button_start).setOnClickListener(this);



    }




    @Override
    public void onClick(View view) {





        if(view.getId() == R.id.button_start) {

            //sets the view to quiz view to start quiz
            setContentView(R.layout.activity_quiz);
            //sets the submit listener on bc button is new
            findViewById(R.id.button_submit).setOnClickListener(this);



            //gets radiobuttons to change the text
            RadioButton a = findViewById(R.id.radioButtonA);
            RadioButton b = findViewById(R.id.radioButtonB);
            RadioButton c = findViewById(R.id.radioButtonC);
            RadioButton d = findViewById(R.id.radioButtonD);

            //sets the onclick listener for the RadioGroup in quiz
            RadioGroup rg = findViewById(R.id.radiogroupanswers);
            rg.setOnCheckedChangeListener(this);

            //makes current question 4 because the radio buttons are automatically set to the first question
            //the very first submit hit will be the second question not the first
            currentQuestion = 4;




        }
        if(view.getId() == R.id.button_submit) {

            //gets radiogroup so we can clear the selection
            RadioGroup rg = findViewById(R.id.radiogroupanswers);
            
            //rg.clearCheck();


            //gets radiobuttons to change the text
            RadioButton a = findViewById(R.id.radioButtonA);
            RadioButton b = findViewById(R.id.radioButtonB);
            RadioButton c = findViewById(R.id.radioButtonC);
            RadioButton d = findViewById(R.id.radioButtonD);

            //passes through the image id array above and sets each image based on the question numbers
            ImageView signs = findViewById(R.id.imageViewSigns);
            if(currentImage != 9) {
                signs.setImageResource(pictureId[currentImage + 1]);
                currentImage++;
            }




            //changes the text if it less than the length of the array this makes it where it shows
            //the last question too
            if(currentQuestion < choices.length) {

                a.setText(choices[currentQuestion++]);
                b.setText(choices[currentQuestion++]);
                c.setText(choices[currentQuestion++]);
                d.setText(choices[currentQuestion++]);

                //clears the selection and makes the next question + 1

                currentQuestionCount++;



            }
            else {

                //and open the results view and display the results
                setContentView(R.layout.activity_results);
                //set the onclick listener for the new button on screen to take test again
                findViewById(R.id.button_take_test_again).setOnClickListener(this);
                //gets textviews of the final results
                TextView passedOr = findViewById(R.id.textViewPassed);
                TextView questionsRight = findViewById(R.id.textViewQuestionsRight);
                TextView questionsWrong = findViewById(R.id.textViewQuestionsWrong);


                for(int i = 0; i < 10; i++)
                {
                    if(questions[i] == true)
                    {
                        currentRight++;
                    }
                    else
                    {
                        currentWrong++;
                    }
                }

            if(q1 == true)
            {
                currentRight = 1;
            }
                //sets the text for current right and current wrong
                questionsRight.append(String.valueOf(currentRight));
                questionsWrong.append(String.valueOf(currentWrong));

                //passed is 7 or more
                if(currentRight <= 7)
                {
                    passedOr.setText("You Failed");


                }
                else
                {
                    passedOr.setText("You Passed");
                }


            }



        }
        else if(view.getId() == R.id.button_take_test_again)
        {
            //resets the counts for everything to reset the test
            currentQuestion = 0;
            currentQuestionCount = 1;

            currentRight = 0;
            currentWrong = 0;
            currentImage = 0;

            //if take test button is hit then change the layout back to the quiz
            setContentView(R.layout.activity_quiz);
            //reset the submit button listener
            findViewById(R.id.button_submit).setOnClickListener(this);


            //resets the radiogroup listener
            RadioGroup rg = findViewById(R.id.radiogroupanswers);
            rg.setOnCheckedChangeListener(this);


            //gets radiobuttons to change the text
            RadioButton a = findViewById(R.id.radioButtonA);
            RadioButton b = findViewById(R.id.radioButtonB);
            RadioButton c = findViewById(R.id.radioButtonC);
            RadioButton d = findViewById(R.id.radioButtonD);

            //next question will be the 4th item in array because the radiobuttons are auto set to first question
            currentQuestion = 4;


        }







    }


    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {

        //gets radiobuttons to change the text
        RadioButton a = findViewById(R.id.radioButtonA);
        RadioButton b = findViewById(R.id.radioButtonB);
        RadioButton c = findViewById(R.id.radioButtonC);
        RadioButton d = findViewById(R.id.radioButtonD);
        RadioButton choice = findViewById(radioGroup.getCheckedRadioButtonId());



        //this else if structure keeps track of the current question 1-10 and if correct
        // radio button is pressed then current right goes up one
        if(currentQuestionCount == 1) {
            if (a.isChecked()) {
                questions[0] = true;
                //currentRight++;

            } else {

                questions[0] = false;
            }


        }
        else if(currentQuestionCount == 2)
        {



            if (a.isChecked()) {
                questions[1] = true;
            }
            else {
                questions[1] = false;
            }
            if(findViewById(R.id.button_submit).isPressed())
            {
                radioGroup.clearCheck();
            }


        }
        else if(currentQuestionCount == 3)
        {
            if (c.isChecked()) {
                questions[2] = true;
            }
            else {
                questions[2] = false;
            }



        }
        else if(currentQuestionCount == 4) {
            if (b.isChecked()) {
                questions[3] = true;
            }
            else {
                questions[3] = false;
            }



        }
        else if(currentQuestionCount == 5)
        {
            if (a.isChecked()) {
                questions[4] = true;
            }
            else {
                questions[4] = false;
            }


        }
        else if(currentQuestionCount == 6)
        {
            if (d.isChecked()) {
                questions[5] = true;
            }
            else {
                questions[5] = false;
            }



        }
       else if(currentQuestionCount == 7) {
            if (b.isChecked()) {
                questions[6] = true;
            }
            else {
                questions[6] = false;
            }



        }
        else if(currentQuestionCount == 8)
        {
            if (c.isChecked()) {
                questions[7] = true;
            }
            else {
                questions[7] = false;
            }



        }
        else if(currentQuestionCount == 9)
        {
            if (b.isChecked()) {
                questions[8] = true;
            }
            else {
                questions[8] = false;
            }



        }
        else if(currentQuestionCount == 10)
        {
            if (b.isChecked()) {
                questions[9] = true;
            }
            else {
                questions[9] = false;
            }


        }



    }




}







