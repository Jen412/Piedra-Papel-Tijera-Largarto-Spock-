package com.example.piedrapapel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;

public class MainActivity extends AppCompatActivity {
    int cont=0, contPiedra=0, contPapel=0, contTijeras=0, contLagarto=0, contSpock=0;
    float  probPiedra=0, probPapel=0, probTijera=0, probLagarto=0, probSpock=0;
    ImageButton btiPiedra, btiPapel, btiTijeras, btiLagarto, btiSpock;
    TextView tvGanador;
    Button btDenuevo;
    boolean piedra =false, papel=false, tijeras=false, lagarto=false, spock=false, gano=false, draw=false;
    ArrayList jugadas = new ArrayList();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btiPapel = (ImageButton) findViewById(R.id.btiPapel);
        btiTijeras = (ImageButton) findViewById(R.id.btiTijeras);
        btiPiedra = (ImageButton) findViewById(R.id.btiPiedra);
        btiLagarto = (ImageButton) findViewById(R.id.btiLagarto);
        btiSpock = (ImageButton) findViewById(R.id.btiSpock);
        btDenuevo =(Button) findViewById(R.id.btDenuevo);
        tvGanador =(TextView)findViewById(R.id.tvGanador);
        btDenuevo.setVisibility(View.INVISIBLE);
    }

    public void jugar(){
        String[]  opciones= {"piedra", "papel", "tijeras", "lagarto", "spock"};
        String opcion="";
        if (cont<5){
            Random ran = new Random();
            int opc = ran.nextInt(5) + 0;
            System.out.println(opc);
            opcion = opciones[opc];

        }
        else{
            opcion = analisis();
        }
        if (piedra==true){
            jugadas.add("piedra");
            switch (opcion){
                case "papel":
                    gano=false;
                    break;
                case "tijeras":
                    gano=true;
                    break;
                case "lagarto":
                    gano=true;
                    break;
                case "spock":
                    gano=false;
                    break;
                case "piedra":
                    draw=true;
                    break;
            }
        }
        if (papel==true) {
            jugadas.add("papel");
            switch (opcion){
                case "papel":
                    draw=true;
                    break;
                case "tijeras":
                    gano=false;
                    break;
                case "lagarto":
                    gano=false;
                    break;
                case "spock":
                    gano=true;
                    break;
                case "piedra":
                    gano=true;
                    break;
            }
        }
        if (tijeras==true) {
            jugadas.add("tijeras");
            switch (opcion) {
                case "papel":
                    gano = true;
                    break;
                case "tijeras":
                    draw = true;
                    break;
                case "lagarto":
                    gano = true;
                    break;
                case "spock":
                    gano = false;
                    break;
                case "piedra":
                    gano = false;
                    break;
            }
        }
        if (lagarto == true) {
            jugadas.add("lagarto");
            switch (opcion) {
                case "papel":
                    gano = true;
                    break;
                case "tijeras":
                    gano = false;
                    break;
                case "lagarto":
                    draw = true;
                    break;
                case "spock":
                    gano = true;
                    break;
                case "piedra":
                    gano = false;
                    break;
            }
        }
        if (spock == true) {
            jugadas.add("spock");
            switch (opcion) {
                case "papel":
                    gano = false;
                    break;
                case "tijeras":
                    gano = true;
                    break;
                case "lagarto":
                    gano = false;
                    break;
                case "spock":
                    draw = true;
                    break;
                case "piedra":
                    gano = true;
                    break;
            }
        }
        cont++;
        btDenuevo.setVisibility(View.VISIBLE);
        ganador();
    }

    public String analisis() {
        String opc="";
        for (int i = 0; i < jugadas.size(); i++) {
            String op=(String)jugadas.get(i);
            if (jugadas.get(i).equals("piedra")){
                contPiedra+=1;
            }
            else if(jugadas.get(i).equals("papel")){
                contPapel+=1;
            }
            else if(jugadas.get(i).equals("tijeras")){
                contTijeras+=1;
            }
            else if(jugadas.get(i).equals("lagarto")){
                contLagarto+=1;
            }
            else if(jugadas.get(i).equals("spock")){
                contSpock+=1;
            }
            else{}
        }
        probPiedra = (float)contPiedra/cont*100;
        probPapel = (float)contPapel/cont*100;
        probTijera = (float)contTijeras/cont*100;
        probLagarto = (float)contLagarto/cont*100;
        probSpock = (float)contSpock/cont*100;

    //System.out.println(probPiedra);
        ArrayList probCinco = new ArrayList();
        probCinco.add("piedra");
        probCinco.add("papel");
        probCinco.add("tijeras");
        probCinco.add("lagarto");
        probCinco.add("spock");
        ArrayList probTres = new ArrayList();
        Random ran = new Random();
        int opcProb5 = ran.nextInt(5) + 0;
        int opcProb3 = ran.nextInt(2) + 0;
        if (probTijera==probPapel&& probPapel==probPiedra&&probPiedra==probLagarto&& probLagarto==probSpock){
            opc =(String) probCinco.get(opcProb5);
        }else{
            Hashtable<Float,String> prob = new Hashtable<Float,String>();
            prob.put(probPiedra,"piedra");
            prob.put(probPapel,"papel");
            prob.put(probTijera,"tijeras" );
            prob.put(probLagarto,"lagarto");
            prob.put(probSpock,"spock");
            prob = ordenarHash(prob);
            Set<Float> keys = prob.keySet();
            Iterator<Float> itr = keys.iterator();
            for (int i = 0; i <2; i++) {
                Float f = itr.next();
                probTres.add(prob.get(f));
                //System.out.println(f+" "+ prob.get(f));
            }

            opc = (String) probTres.get(opcProb3);
        }

        System.out.println(opc);
        contSpock=0;
        contLagarto=0;
        contPiedra=0;
        contTijeras=0;
        contPapel=0;
        return opc;
    }

    public void piedra(View view){
        piedra=true;
        papel=false;
        tijeras=false;
        lagarto=false;
        spock=false;
        btiLagarto.setEnabled(false);
        btiLagarto.setAlpha(0.75f);
        btiPapel.setEnabled(false);
        btiPapel.setAlpha(0.75f);
        btiSpock.setEnabled(false);
        btiSpock.setAlpha(0.75f);
        btiTijeras.setEnabled(false);
        btiTijeras.setAlpha(0.75f);
        //System.out.println("Piedra");
        jugar();
    }

    public void papel(View view){
        piedra=false;
        papel=true;
        tijeras=false;
        lagarto=false;
        spock=false;
        btiLagarto.setEnabled(false);
        btiLagarto.setAlpha(0.75f);
        btiPiedra.setEnabled(false);
        btiPiedra.setAlpha(0.75f);
        btiSpock.setEnabled(false);
        btiSpock.setAlpha(0.75f);
        btiTijeras.setEnabled(false);
        btiTijeras.setAlpha(0.75f);
        //System.out.println("Papel");
        jugar();
    }

    public void tijeras(View view){
        piedra=false;
        papel=false;
        tijeras=true;
        lagarto=false;
        spock=false;
        btiLagarto.setEnabled(false);
        btiLagarto.setAlpha(0.75f);
        btiPapel.setEnabled(false);
        btiPapel.setAlpha(0.75f);
        btiSpock.setEnabled(false);
        btiSpock.setAlpha(0.75f);
        btiPiedra.setEnabled(false);
        btiPiedra.setAlpha(0.75f);
        //System.out.println("Tijeras");
        jugar();
    }

    public void lagarto(View view){
        piedra=false;
        papel=false;
        tijeras=false;
        lagarto=true;
        spock=false;
        btiPiedra.setEnabled(false);
        btiPiedra.setAlpha(0.75f);
        btiPapel.setEnabled(false);
        btiPapel.setAlpha(0.75f);
        btiSpock.setEnabled(false);
        btiSpock.setAlpha(0.75f);
        btiTijeras.setEnabled(false);
        btiTijeras.setAlpha(0.75f);
        //System.out.println("Lagarto");
        jugar();
    }

    public void spock(View view){
        piedra=false;
        papel=false;
        tijeras=false;
        lagarto=false;
        spock=true;
        btiLagarto.setEnabled(false);
        btiLagarto.setAlpha(0.75f);
        btiPapel.setEnabled(false);
        btiPapel.setAlpha(0.75f);
        btiPiedra.setEnabled(false);
        btiPiedra.setAlpha(0.75f);
        btiTijeras.setEnabled(false);
        btiTijeras.setAlpha(0.75f);
        //System.out.println("Spock");
        jugar();
    }

    public void reinicio(View view){
        btiPapel.setAlpha(1f);
        btiTijeras.setAlpha(1f);
        btiPiedra.setAlpha(1f);
        btiLagarto.setAlpha(1f);
        btiSpock.setAlpha(1f);
        btiTijeras.setEnabled(true);
        btiPapel.setEnabled(true);
        btiPiedra.setEnabled(true);
        btiLagarto.setEnabled(true);
        btiSpock.setEnabled(true);
        tvGanador.setText("");
        btDenuevo.setVisibility(View.INVISIBLE);
        draw=false;
        piedra=false;
        papel=false;
        tijeras=false;
        lagarto=false;
        spock=false;
    }

    public void ganador(){
        if (draw==false) {
            if (gano == true) {
                tvGanador.setText("GANO USAURIO");
            } else {
                tvGanador.setText("GANO CPU");
            }
        }
        else {
            tvGanador.setText("Empate");
        }
    }

    public Hashtable ordenarHash(Hashtable h){
        Hashtable<Float,String> hr = new Hashtable<Float, String>();
        TreeMap<Float, String> tm
                = new TreeMap<Float, String>(h);

        // create a keyset
        Set<Float> keys = tm.keySet();
        Iterator<Float> itr = keys.iterator();

        // traverse the TreeMap using iterator
        while (itr.hasNext()) {
            Float i = itr.next();
            hr.put(i,(String)h.get(i));
            System.out.println(i + " " + tm.get(i));
        }
        return hr;
    }
}