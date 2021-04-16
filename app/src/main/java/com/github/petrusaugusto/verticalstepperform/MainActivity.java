package com.github.petrusaugusto.verticalstepperform;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import ernestoyaquello.com.verticalstepperform.VerticalStepperFormLayout;
import ernestoyaquello.com.verticalstepperform.interfaces.VerticalStepperForm;

public class MainActivity extends AppCompatActivity implements VerticalStepperForm {
    protected VerticalStepperFormLayout verticalStepperFormLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.verticalStepperFormLayout = (VerticalStepperFormLayout) findViewById(R.id.verticalStepperFormLayout);
        final String[] mySteps = {"Name", "Email"};
        int colorPrimary = ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary);
        int colorPrimaryDark = ContextCompat.getColor(getApplicationContext(), R.color.colorPrimaryDark);
        VerticalStepperFormLayout.Builder.newInstance(verticalStepperFormLayout, mySteps, this, this)
                .primaryColor(colorPrimary)
                .primaryDarkColor(colorPrimaryDark)
                .confirmationButtonText("Tudo OK")
                .continueButtonText("Proximo")
                .showConfirmationStepLayout(false)
                .displayBottomNavigation(true)
                .init();
    }

    @Override
    public View createStepContentView(int stepNumber) {
        View view = null;
        switch (stepNumber) {
            case 0:
                view = createNameStep();
                break;
            case 1:
                view = createEmailStep();
                break;
        }

        return view;
    }

    @Override
    public void onStepOpening(int stepNumber) {
        this.verticalStepperFormLayout.setActiveStepAsCompleted();
    }

    @Override
    public void sendData() {
        Toast.makeText(this, "Send Data", Toast.LENGTH_SHORT).show();
    }

    private View createNameStep() {
        // Here we generate programmatically the view that will be added by the system to the step content layout
        EditText name = new EditText(this);
        name.setSingleLine(true);
        name.setHint("Your name");
        return name;
    }

    private View createEmailStep() {
        EditText name = new EditText(this);
        name.setSingleLine(true);
        name.setHint("Your Email");
        name.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        return name;
    }
}
