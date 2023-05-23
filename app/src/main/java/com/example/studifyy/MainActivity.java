package com.example.studifyy;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.example.studifyy.BTechFirstSem.MainBTechFirstSemActivity;
import com.example.studifyy.BTechThirdSem.MainBTechThirdSemActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
public class MainActivity extends AppCompatActivity {
    EditText phonenumber, receivedOTP;
    Button otp, submit;
    TextView resend;
    ///254*319
    String OTPid, combo_phone;
    PhoneAuthProvider.OnVerificationStateChangedCallbacks callbacks;
    PhoneAuthProvider.ForceResendingToken token;
    FirebaseAuth mAuth;
    Spinner university, program;
    ArrayList<String> universitylist;
    ArrayAdapter<String> universityAdapter;
    ArrayList<String> cukprogramlist, kuprogramlist;
    ArrayAdapter<String> cukprogramAdapter, kuprogramAdapter;
    String selectedprogram, selecteduniversity,pro,uni;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        university = findViewById(R.id.university);
        program = findViewById(R.id.program);
        phonenumber = findViewById(R.id.PhoneNumber);
        mAuth = FirebaseAuth.getInstance();
        resend = findViewById(R.id.resend);
        resend.setEnabled(false);
        otp = findViewById(R.id.getOTP);
        receivedOTP = findViewById(R.id.Received);
        submit = findViewById(R.id.Submit);
        otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (phonenumber.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, " Field Required !", Toast.LENGTH_SHORT).show();
                }
                combo_phone = "+91" + phonenumber.getText().toString();
                verifyPhone(combo_phone);
            }
        });
        resend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verifyPhone(combo_phone);
                resend.setEnabled(false);
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (receivedOTP.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Fill OTP", Toast.LENGTH_SHORT).show();
                }
                PhoneAuthCredential credential = PhoneAuthProvider.getCredential(OTPid, receivedOTP.getText().toString());
                Authticate(credential);
            }
        });
        callbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                Authticate(phoneAuthCredential);
                Toast.makeText(MainActivity.this, "SuccessFully", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {
                Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
                OTPid = s;
                token = forceResendingToken;
                resend.setEnabled(false);
            }
            @Override
            public void onCodeAutoRetrievalTimeOut(@NonNull String s) {
                super.onCodeAutoRetrievalTimeOut(s);
                resend.setEnabled(true);
            }
        };

    }
    public void verifyPhone(String PhoneNum) {
        PhoneAuthOptions options = PhoneAuthOptions.newBuilder(mAuth).setActivity(this).
                setPhoneNumber(PhoneNum)
                .setTimeout(60L, TimeUnit.SECONDS)
                .setCallbacks(callbacks).build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    public void Authticate(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Spinner();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void Spinner() {
        university.setVisibility(View.VISIBLE);
        program.setVisibility(View.VISIBLE);
        universitylist = new ArrayList<>();
        universitylist.add("Select Collage/University");
        universitylist.add("Central University of Kashmir");
        universitylist.add("Kashmir University");
        universityAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, universitylist);
        university.setAdapter(universityAdapter);
        cukprogramlist = new ArrayList<>();
        cukprogramlist.add("Select Program");
        cukprogramlist.add("B-Tech in Computer Science");
        cukprogramlist.add("M-Tech in Computer Science");
        kuprogramlist = new ArrayList<>();
        kuprogramlist.add("Select program");
        kuprogramlist.add("BCom");
        kuprogramlist.add("BCA");
        university.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selecteduniversity = university.getSelectedItem().toString();
                sendData();
                if (i == 1) {
                    cukprogramAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, cukprogramlist);
                    program.setAdapter(cukprogramAdapter);
                } else if (i == 2) {
                    kuprogramAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, kuprogramlist);
                    program.setAdapter(kuprogramAdapter);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        program.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedprogram = program.getSelectedItem().toString();
                sendData();
                getData();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }
    public void sendData(){
        GetUserDetail detail=new GetUserDetail(selecteduniversity,selectedprogram,combo_phone);
        DatabaseReference reference= FirebaseDatabase.getInstance().getReference("UserDetail");
        reference.child(Objects.requireNonNull(Objects.requireNonNull(FirebaseAuth.getInstance().getUid()))).setValue(detail).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
            }
        });
    }
    public void getData() {
        reference = FirebaseDatabase.getInstance().getReference("UserDetail");
        reference.child(Objects.requireNonNull(FirebaseAuth.getInstance().getUid())).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()) {
                    DataSnapshot snapshot = task.getResult();
                    pro = String.valueOf(snapshot.child("programenrolled").getValue());
                    uni = String.valueOf(snapshot.child("universityattend").getValue());
                    if (uni.equals("Central University of Kashmir") && pro.equals("B-Tech in Computer Science")) {
                       Intent intent1=new Intent(MainActivity.this,MainBTechFirstSemActivity.class);
                       startActivity(intent1);
                       finish();
                    } else if (uni.equals("Kashmir University") && pro.equals("BCA")) {
                        Intent intent2=new Intent(MainActivity.this,MainBTechThirdSemActivity.class);
                        startActivity(intent2);
                        finish();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "fail", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
