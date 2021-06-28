package com.example.resume;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class Education extends AppCompatActivity {

    educationJava educ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
    }

    public void readData() throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factor = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = factor.newDocumentBuilder();
        Document doc = dBuilder.parse(getAssets().open("Resume.xml"));

        doc.getDocumentElement().normalize();

        String edu = doc.getElementsByTagName("edu").item(0).getTextContent();
        String status = doc.getElementsByTagName("status").item(0).getTextContent();
        String period = doc.getElementsByTagName("period").item(0).getTextContent();
        String cgpa = doc.getElementsByTagName("cgpa").item(0).getTextContent();
        String edu1 = doc.getElementsByTagName("edu1").item(0).getTextContent();
        String status1 = doc.getElementsByTagName("status1").item(0).getTextContent();
        String period1 = doc.getElementsByTagName("period1").item(0).getTextContent();
        String cgpa1 = doc.getElementsByTagName("cgpa1").item(0).getTextContent();

        educ = new educationJava(edu, status, period, cgpa, edu1, status1, period1, cgpa1);

        TextView txtEdu = (TextView) findViewById(R.id.txtEdu);
        txtEdu.setText(educ.edu);

        TextView txtStatus = (TextView) findViewById(R.id.txtStatus);
        txtStatus.setText(educ.status);

        TextView txtPeriod = (TextView) findViewById(R.id.txtPeriod);
        txtPeriod.setText(educ.period);

        TextView txtCGPA = (TextView) findViewById(R.id.txtCGPA);
        txtCGPA.setText(educ.cgpa);

        TextView txtEdu1 = (TextView) findViewById(R.id.txtEdu1);
        txtEdu1.setText(educ.edu1);

        TextView txtStatus1 = (TextView) findViewById(R.id.txtStatus1);
        txtStatus1.setText(educ.status1);

        TextView txtPeriod1 = (TextView) findViewById(R.id.txtPeriod1);
        txtPeriod1.setText(educ.period1);

        TextView txtCGPA1 = (TextView) findViewById(R.id.txtCGPA1);
        txtCGPA1.setText(educ.cgpa1);
    }

    public void buttonClick5(View view) throws ParserConfigurationException, IOException, SAXException {
        Button btn = (Button) findViewById(R.id.buttonViewWork);
        readData();
    }

    public void buttonStoreFB2(View view)
    {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // Create a new user
        Map<String, Object> EducationFB = new HashMap<>();
        EducationFB.put("edu", educ.edu);
        EducationFB.put("status", educ.status);
        EducationFB.put("period", educ.period);
        EducationFB.put("cgpa", educ.cgpa);
        EducationFB.put("edu1", educ.edu1);
        EducationFB.put("status1", educ.status1);
        EducationFB.put("period1", educ.period1);
        EducationFB.put("cgpa1", educ.cgpa1);

        // Add a new document with a generated ID
        db.collection("education")
                .add(EducationFB)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference)
                    {
                        Toast.makeText(Education.this, "Done", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e)
                    {
                        Toast.makeText(Education.this, "Not Done Yet", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void buttonRetrieveFB2(View view)
    {
        //retrieve the document
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("education")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>()
                {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task)
                    {
                        if (task.isSuccessful())
                        {
                            for (QueryDocumentSnapshot document : task.getResult())
                            {
                                Toast.makeText(Education.this, document.getId() + " => " + document.getData(), Toast.LENGTH_LONG).show();
                            }
                        }
                        else
                        {
                            Toast.makeText(Education.this, "Failed to Retrieve", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }





}