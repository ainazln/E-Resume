package com.example.resume;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
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
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class Work extends AppCompatActivity {

    workJava work;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
    }

    public void readData() throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factor = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = factor.newDocumentBuilder();
        Document doc = dBuilder.parse(getAssets().open("Resume.xml"));

        doc.getDocumentElement().normalize();

        String internname = doc.getElementsByTagName("internname").item(0).getTextContent();
        String internperiod = doc.getElementsByTagName("internperiod").item(0).getTextContent();

        NodeList internjob = doc.getElementsByTagName("scope");
        String[] listJob = new String[internjob.getLength()];
        for (int i = 0; i < internjob.getLength(); i++) {
            listJob[i] = internjob.item(i).getTextContent();
        }

        work = new workJava(internname, internperiod, listJob);

        TextView internName = (TextView) findViewById(R.id.internName);
        internName.setText(work.internname);

        TextView internPeriod = (TextView) findViewById(R.id.internPeriod);
        internPeriod.setText(work.internperiod);

        Spinner spinJob = (Spinner) findViewById(R.id.spinnerJob);
        ArrayAdapter<String> jobAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, work.internjob);
        jobAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinJob.setAdapter(jobAdapter);
    }

    public void buttonClick1(View view) throws ParserConfigurationException, IOException, SAXException {
        Button btn = (Button) findViewById(R.id.buttonViewWork);
        readData();
    }

    public void buttonStoreFB6(View view)
    {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // Create a new user
        Map<String, Object> WorkFB = new HashMap<>();
        WorkFB.put("internname", work.internname);
        WorkFB.put("internperiod", work.internperiod);
        WorkFB.put("internjob", Arrays.toString(work.internjob));

        // Add a new document with a generated ID
        db.collection("work")
                .add(WorkFB)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference)
                    {
                        Toast.makeText(Work.this, "Done", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e)
                    {
                        Toast.makeText(Work.this, "Not Done Yet", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void buttonRetrieveFB6(View view)
    {
        //retrieve the document
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("work")
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
                                Toast.makeText(Work.this, document.getId() + " => " + document.getData(), Toast.LENGTH_LONG).show();
                            }
                        }
                        else
                        {
                            Toast.makeText(Work.this, "Failed to Retrieve", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }



}