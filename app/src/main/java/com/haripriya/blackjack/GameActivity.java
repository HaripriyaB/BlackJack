package com.haripriya.blackjack;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class GameActivity extends AppCompatActivity {

    private ArrayList<String> dealerImageNames = new ArrayList<>();
    private ArrayList<Integer> dealerImageID = new ArrayList<>();
    private ArrayList<String> playerImageNames = new ArrayList<>();
    private ArrayList<Integer> playerImageID = new ArrayList<>();
    private HashMap<String, Integer> map = new HashMap<>();
    private List<String> deck = new ArrayList<>();
    private static int dealerSum = 0;
    private static int playerSum = 0;
    private String s = null;
    TextView dealerScore;
    TextView playerScore;
    private Button exit_button;
    private Button restart_button;
    Dialog dialog;
    String result;
    Boolean hit_or_stay = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        map.put("2", 2);
        map.put("3", 3);
        map.put("4", 4);
        map.put("5", 5);
        map.put("6", 6);
        map.put("7", 7);
        map.put("8", 8);
        map.put("9", 9);
        map.put("10", 10);
        map.put("K", 10);
        map.put("Q", 10);
        map.put("J", 10);
        map.put("A", 1);
        //**********************************
        deck.add("clubs_2");
        deck.add("clubs_3");
        deck.add("clubs_4");
        deck.add("clubs_5");
        deck.add("clubs_6");
        deck.add("clubs_7");
        deck.add("clubs_8");
        deck.add("clubs_9");
        deck.add("clubs_10");
        deck.add("king_of_clubs");
        deck.add("queen_of_clubs");
        deck.add("jack_of_clubs");
        deck.add("ace_of_clubs");
        deck.add("diamonds_2");
        deck.add("diamonds_3");
        deck.add("diamonds_4");
        deck.add("diamonds_5");
        deck.add("diamonds_6");
        deck.add("diamonds_7");
        deck.add("diamonds_8");
        deck.add("diamonds_9");
        deck.add("diamonds_10");
        deck.add("king_of_diamonds");
        deck.add("queen_of_diamonds");
        deck.add("jack_of_diamonds");
        deck.add("ace_of_diamonds");
        deck.add("hearts_2");
        deck.add("hearts_3");
        deck.add("hearts_4");
        deck.add("hearts_5");
        deck.add("hearts_6");
        deck.add("hearts_7");
        deck.add("hearts_8");
        deck.add("hearts_9");
        deck.add("hearts_10");
        deck.add("king_of_hearts");
        deck.add("queen_of_hearts");
        deck.add("jack_of_hearts");
        deck.add("ace_of_hearts");
        deck.add("spades_2");
        deck.add("spades_3");
        deck.add("spades_4");
        deck.add("spades_5");
        deck.add("spades_6");
        deck.add("spades_7");
        deck.add("spades_8");
        deck.add("spades_9");
        deck.add("spades_10");
        deck.add("king_of_spades");
        deck.add("queen_of_spades");
        deck.add("jack_of_spades");
        deck.add("ace_of_spades");
        //**************************************
        Collections.shuffle(deck);
        setContentView(R.layout.activity_game);
        initRecyclerViewDealer();
        initRecyclerViewPlayer();
        exit_button = findViewById(R.id.exit_button);
        restart_button = findViewById(R.id.restart_button);
        dialog = new Dialog(this);

        s = deck.remove(0);
        dealerImageID.add(this.getResources().getIdentifier(s, "drawable", this.getPackageName()));
        dealerImageNames.add(s);
        if (s.charAt(0) == 'k' || s.charAt(0) == 'q' || s.charAt(0) == 'j') {
            dealerSum += 10;
        } else if (s.charAt(0) == 'a') {
            dealerSum += 1;
        } else {
            dealerSum += map.get(s.split("_")[1]);
        }

        s = deck.remove(0);
        playerImageID.add(this.getResources().getIdentifier(s, "drawable", this.getPackageName()));
        playerImageNames.add(s);
        if (s.charAt(0) == 'k' || s.charAt(0) == 'q' || s.charAt(0) == 'j') {
            playerSum += 10;
        } else if (s.charAt(0) == 'a') {
            //showDialogBoxToDetermineValueOfAce();
            if (playerSum + 11 <= 21)
                playerSum += 11;
            else
                playerSum += 1;
        } else {
            playerSum += map.get(s.split("_")[1]);
        }


        s = deck.remove(0);
        dealerImageID.add(this.getResources().getIdentifier(s, "drawable", this.getPackageName()));
        dealerImageNames.add(s);
        if (s.charAt(0) == 'k' || s.charAt(0) == 'q' || s.charAt(0) == 'j') {
            dealerSum += 10;
        } else if (s.charAt(0) == 'a') {
            dealerSum += 1;
        } else {
            dealerSum += map.get(s.split("_")[1]);
        }

        s = deck.remove(0);
        playerImageID.add(this.getResources().getIdentifier(s, "drawable", this.getPackageName()));
        playerImageNames.add(s);
        if (s.charAt(0) == 'k' || s.charAt(0) == 'q' || s.charAt(0) == 'j') {
            playerSum += 10;
        } else if (s.charAt(0) == 'a') {
            //showDialogBoxToDetermineValueOfAce();
            if (playerSum + 11 <= 21)
                playerSum += 11;
            else
                playerSum += 1;
        } else {
            playerSum += map.get(s.split("_")[1]);
        }
        dealerScore = findViewById(R.id.text_dealer_sum);
        playerScore = findViewById(R.id.text_player_sum);

        dealerScore.setText(String.valueOf(dealerSum));
        playerScore.setText(String.valueOf(playerSum));
        int flag = 0;

        RestOfTheProgram(flag);

    }

    private void RestOfTheProgram(int flag) {
        while (true) {
            if (playerSum > 21) {
                if (dealerSum > 21) {
                    showDialogBox("Both Lost");
                    break;
                }
                showDialogBox("Dealer Won");
                break;
            } else if (dealerSum > 21) {
                showDialogBox("Player Won");
                break;
            } else if (playerSum == 21) {
                if (dealerSum == 21) {
                    showDialogBox("Game Tied");
                    break;
                }
                showDialogBox("Player Won");
                break;
            } else if (dealerSum == 21) {
                showDialogBox("Dealer Won");
                break;
            }
            if (playerSum < 21) {
                // Here we need to ask the player whether HIT/STAY?
                //showDialogBoxForHitOrStay();
                if (hit_or_stay) {
                    // if HIT
                    s = deck.remove(0);
                    playerImageID.add(this.getResources().getIdentifier(s, "drawable", this.getPackageName()));
                    playerImageNames.add(s);
                    if (s.charAt(0) == 'k' || s.charAt(0) == 'q' || s.charAt(0) == 'j') {
                        playerSum += 10;
                    } else if (s.charAt(0) == 'a') {

                        //showDialogBoxToDetermineValueOfAce();
                        if (playerSum + 11 <= 21)
                            playerSum += 11;
                        else
                            playerSum += 1;
                    } else {
                        playerSum += map.get(s.substring(s.length() - 1));
                    }
                } else {
                    flag = 1;
                    dealerScore.setText(String.valueOf(dealerSum));
                    playerScore.setText(String.valueOf(playerSum));
                    break;
                }
                //if STAY = >break from loop
                // Flag=stop game and give out the results
            }
            if (dealerSum < 17) {
                s = deck.remove(0);
                dealerImageID.add(this.getResources().getIdentifier(s, "drawable", this.getPackageName()));
                dealerImageNames.add(s);
                if (s.charAt(0) == 'k' || s.charAt(0) == 'q' || s.charAt(0) == 'j') {
                    dealerSum += 10;
                } else if (s.charAt(0) == 'a') {
                    dealerSum += 1;
                } else {
                    dealerSum += map.get(s.substring(s.length() - 1));
                }
            }
            dealerScore.setText(String.valueOf(dealerSum));
            playerScore.setText(String.valueOf(playerSum));
        }
        if (flag == 1) {
            if (dealerSum < playerSum)
                showDialogBox("Player Won");
            else if (dealerSum > playerSum)
                showDialogBox("Dealer Won");
            else showDialogBox("Game Tied");
        }
        exit_button.setVisibility(View.VISIBLE);
        restart_button.setVisibility(View.VISIBLE);
        restart_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dealerSum =0;
                playerSum=0;
                Intent i = new Intent(GameActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });
        exit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GameActivity.this.finish();
                System.exit(0);
            }
        });
    }

    private void showDialogBoxToDetermineValueOfAce() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                dialog.setContentView(R.layout.ace_value_dialog);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                Button oneButton = dialog.findViewById(R.id.one_button);
                Button elevenButton = dialog.findViewById(R.id.eleven_button);

                oneButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        result = "1";
                        dialog.dismiss();
                        notify();
                    }
                });
                elevenButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        result = "11";
                        dialog.dismiss();
                        notify();
                    }
                });
                dialog.show();
            }
        });

        try {
            wait();
        } catch (InterruptedException ignored) {
        }
    }

    private void showDialogBoxForHitOrStay() {

        dialog.setContentView(R.layout.hit_or_stay_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Button hitButton = dialog.findViewById(R.id.hit_button);
        Button stayButton = dialog.findViewById(R.id.stay_button);

        hitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hit_or_stay = true;
                dialog.dismiss();

            }
        });
        stayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hit_or_stay = false;
                dialog.dismiss();

            }
        });
        dialog.show();
    }

    private void showDialogBox(String message) {

        dialog.setContentView(R.layout.result_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        TextView textView = dialog.findViewById(R.id.text_win_or_lose);
        Button okButton = dialog.findViewById(R.id.ok_button);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        textView.setText(message);
        dialog.show();

    }

    private void initRecyclerViewDealer() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerView = findViewById(R.id.dealer_rv);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, dealerImageNames, dealerImageID);
        recyclerView.setAdapter(adapter);
    }

    private void initRecyclerViewPlayer() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerView = findViewById(R.id.player_rv);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, playerImageNames, playerImageID);
        recyclerView.setAdapter(adapter);
    }

}