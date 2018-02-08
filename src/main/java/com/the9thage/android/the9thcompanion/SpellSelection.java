package com.the9thage.android.the9thcompanion;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ExpandableListView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SpellSelection extends Activity {

    private ExpandableListAdapter listAdapter;
    private ExpandableListView expListView;
    private List<String> listDataHeader;
    private HashMap<String, List<String>> listDataChild;
    private final int positionPlayer1 = 0;
    private final int positionPlayer2 = 1;
    private List<String> player1Spells;
    private List<String> player2Spells;
    private File tempFile;
    private String FILE_NAME = "T9CompanionSpells.txt";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spell_selection);

        // preparing list data
        prepareListData();

        // displaying the lists
        prepareListDisplay();

        getActionBar().setTitle("Spell selection");
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("We resume the activity");
        setContentView(R.layout.activity_spell_selection);

        // preparing list data
        prepareListData();

        // displaying the lists
        prepareListDisplay();
        System.out.println("player1Spells = " + player1Spells.toString());
        System.out.println("player2Spells = " + player2Spells.toString());

        getActionBar().setTitle("Spell selection");
    }

    /*
     * Preparing the list data
     */
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        player1Spells = new ArrayList<String>();
        player2Spells = new ArrayList<String>();

        try{
            System.out.println("We load the lists");
            loadList();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Adding child data
        if (player1Spells.isEmpty()){
            player1Spells.add("Add a spell");
        }

        if (player2Spells.isEmpty()){
            player2Spells.add("Add a spell");
        }

        listDataHeader.add("Player 1");
        listDataHeader.add("Player 2");

        if (listDataChild.get(0) == null){
        listDataChild.put(listDataHeader.get(0), player1Spells);}
        if (listDataChild.get(1) == null){
        listDataChild.put(listDataHeader.get(1), player2Spells);}
    }

    private void prepareListDisplay(){

        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.listPlayer);

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);

        /* No need to set the following listeners
        // Listview Group click listener
        expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                parent.expandGroup(groupPosition);
                return false;
            }
        });

        // Listview Group expanded listener
        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
            }
        });

        // Listview Group collapsed listenerc
        expListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {

            }
        });*/

        // Listview on child click listener
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                List<String> listSpells = new ArrayList<String>();
                if (groupPosition == positionPlayer1)
                    listSpells = player1Spells;
                else if (groupPosition == positionPlayer2)
                    listSpells = player2Spells;
                if (childPosition < listSpells.size() - 1) {
                    deleteSpell(listSpells, childPosition);
                }
                else if (childPosition == listSpells.size() - 1) {
                    addSpell(listSpells);
                }
                parent.expandGroup(groupPosition);
                return false;
            }
        });
    }

    public void deleteSpell(List<String> listSpells, int spellPosition){
        listSpells.remove(spellPosition);

        try {
            saveList();
        } catch (IOException e) {
            e.printStackTrace();
        }

        prepareListData();
        prepareListDisplay();
    }

    public void addSpell(List<String> listSpells){

        final int playerNumber;
        if (listSpells == player1Spells) playerNumber = 1;
        else playerNumber = 2;

        // get prompts.xml view
        LayoutInflater li = LayoutInflater.from(this);
        View promptsView = li.inflate(R.layout.spell_prompts, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        // set prompts.xml to alertdialog builder
        alertDialogBuilder.setView(promptsView);

        final EditText userInput = (EditText) promptsView
                .findViewById(R.id.editAddSpell);

        // set dialog message
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("Add",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                // get user input and set it to result
                                appendList(playerNumber, userInput.getText().toString());
                                prepareListData();
                                prepareListDisplay();
                                //listToComplete.add(userInput.getText().toString());
                                //resultAddSpell.setText(userInput.getText());
                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                            }
                        });


        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }

    public void appendList(int playerNumber, String newValue){
        // we add the new element at the beginning of the list
        if (playerNumber == 1) player1Spells.add(0, newValue);
        if (playerNumber == 2) player2Spells.add(0, newValue);
        try {
            saveList();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveList() throws IOException {

        FileOutputStream fos = openFileOutput(FILE_NAME, Context.MODE_PRIVATE);

        for (String spell : player1Spells)
            fos.write(new String("player1Spells_" + spell + "\n").getBytes());
        for (String spell : player2Spells)
            fos.write(new String("player2Spells_" + spell + "\n").getBytes());
        fos.close();
    }

    public void loadList() throws IOException {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(openFileInput(FILE_NAME)));
            String line;
            player1Spells.clear();
            player2Spells.clear();
            while ((line = bufferedReader.readLine()) != null) {
                if (line.contains("player1Spells_")){
                    player1Spells.add(line.substring(14));
                }
                else if (line.contains("player2Spells_")){
                    player2Spells.add(line.substring(14));
                }
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
