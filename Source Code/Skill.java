package com.example.resume;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
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

public class Skill extends AppCompatActivity {

    skillJava skill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
    }

    public void readData() throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factor = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = factor.newDocumentBuilder();
        Document doc = dBuilder.parse(getAssets().open("Resume.xml"));

        doc.getDocumentElement().normalize();

        NodeList language = doc.getElementsByTagName("lang");
        String[] listLanguage = new String[language.getLength()];
        for (int i = 0; i < language.getLength(); i++) {
            listLanguage[i] = language.item(i).getTextContent();
        }

        NodeList complang = doc.getElementsByTagName("complangs");
        String[] listComplang = new String[complang.getLength()];
        for (int i = 0; i < complang.getLength(); i++) {
            listComplang[i] = complang.item(i).getTextContent();
        }

        NodeList software = doc.getElementsByTagName("soft");
        String[] listSoftware = new String[software.getLength()];
        for (int i = 0; i < software.getLength(); i++) {
            listSoftware[i] = software.item(i).getTextContent();
        }

        NodeList hardware = doc.getElementsByTagName("hard");
        String[] listHardware = new String[hardware.getLength()];
        for (int i = 0; i < hardware.getLength(); i++) {
            listHardware[i] = hardware.item(i).getTextContent();
        }

        NodeList technology = doc.getElementsByTagName("tech");
        String[] listTechnology = new String[technology.getLength()];
        for (int i = 0; i < technology.getLength(); i++) {
            listTechnology[i] = technology.item(i).getTextContent();
        }

        skill = new skillJava(listLanguage, listComplang, listSoftware, listHardware, listTechnology);

        Spinner spinLanguage = (Spinner) findViewById(R.id.spinnerLanguage);
        ArrayAdapter<String> languageAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, skill.language);
        languageAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinLanguage.setAdapter(languageAdapter);

        Spinner spinCompLang = (Spinner) findViewById(R.id.spinnerCompLang);
        ArrayAdapter<String> complangAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, skill.complang);
        complangAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinCompLang.setAdapter(complangAdapter);

        Spinner spinSoftware = (Spinner) findViewById(R.id.spinnerSoftware);
        ArrayAdapter<String> softwareAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, skill.software);
        softwareAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinSoftware.setAdapter(softwareAdapter);

        Spinner spinHardware = (Spinner) findViewById(R.id.spinnerHardware);
        ArrayAdapter<String> hardwareAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, skill.hardware);
        hardwareAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinHardware.setAdapter(hardwareAdapter);

        Spinner spinTechnology = (Spinner) findViewById(R.id.spinnerTechnology);
        ArrayAdapter<String> technologyAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, skill.technology);
        technologyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinTechnology.setAdapter(technologyAdapter);
    }

    public void buttonClick2(View view) throws ParserConfigurationException, IOException, SAXException {
        Button btn = (Button) findViewById(R.id.buttonRetrieveFB5);
        readData();
    }

    public void buttonStoreFB5(View view)
    {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // Create a new user
        Map<String, Object> SkillFB = new HashMap<>();
        SkillFB.put("language", Arrays.toString(skill.language));
        SkillFB.put("complang", Arrays.toString(skill.complang));
        SkillFB.put("software", Arrays.toString(skill.software));
        SkillFB.put("hardware", Arrays.toString(skill.hardware));
        SkillFB.put("technology", Arrays.toString(skill.technology));

        // Add a new document with a generated ID
        db.collection("skill")
                .add(SkillFB)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference)
                    {
                        Toast.makeText(Skill.this, "Done", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e)
                    {
                        Toast.makeText(Skill.this, "Not Done Yet", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void buttonRetrieveFB5(View view)
    {
        //retrieve the document
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("skill")
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
                                Toast.makeText(Skill.this, document.getId() + " => " + document.getData(), Toast.LENGTH_LONG).show();
                            }
                        }
                        else
                        {
                            Toast.makeText(Skill.this, "Failed to Retrieve", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


}