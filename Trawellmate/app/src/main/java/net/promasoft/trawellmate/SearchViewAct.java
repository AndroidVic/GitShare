package net.promasoft.trawellmate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import net.promasoft.trawellmate.adp.AdpSearchDeatils;
import net.promasoft.trawellmate.argapp.DataSearch;
import net.promasoft.trawellmate.util.AlertMsgDialog;
import net.promasoft.trawellmate.util.CheckPermission;

import java.util.ArrayList;
import java.util.List;

import static net.promasoft.trawellmate.util.CheckPermission.MY_PERMISSIONS_REQUEST;

public class SearchViewAct extends AppCompatActivity {

    AdpSearchDeatils adapter;
    RecyclerView recyclerView;
    private List<DataSearch> list;
    private LinearLayoutManager layoutManager;
    private EditText searchEditText;
    private ImageView closeBt, speachBt;
    private SpeechRecognizer recognizer;
    private boolean isSpeaking = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_view);
        recyclerView = findViewById(R.id.recyclerview);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        list = new ArrayList<>();
        retrievePackages();
        adapter = new AdpSearchDeatils(list);
        recyclerView.setAdapter(adapter);
        LinearLayout linearLayout = findViewById(R.id.layout_top_deals);

        searchEditText = findViewById(R.id.ID_search_et);
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().length() > 2) {
                    recyclerView.setVisibility(View.VISIBLE);
                    linearLayout.setVisibility(View.GONE);
                }
                if (charSequence.toString().length() > 0) {
                    speachBt.setVisibility(View.GONE);
                    closeBt.setVisibility(View.VISIBLE);
                } else {
                    if (!isSpeaking) {
                        speachBt.setVisibility(View.VISIBLE);
                        closeBt.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                //after the change calling the method and passing the search input
                // filter(editable.toString());
            }

        });


        closeBt = findViewById(R.id.ID_close_bt);
        speachBt = findViewById(R.id.ID_speach_bt);
        speachBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isSpeaking = true;
                String[] permissions = new String[]{
                        Manifest.permission.RECORD_AUDIO,
                };
                if (CheckPermission.selfPermissionList(SearchViewAct.this, permissions)) {
                    continueOnMicroPh();
                    speachBt.setVisibility(View.GONE);
                    closeBt.setVisibility(View.VISIBLE);
                }
            }
        });

        closeBt.setOnClickListener(view1 -> {
            isSpeaking = false;
            closeBt.setVisibility(View.GONE);
            searchEditText.setHint("Where are you going?");
            searchEditText.getText().clear();
            speachBt.setVisibility(View.VISIBLE);
            if (recognizer != null) {
                recognizer.stopListening();
                recognizer.cancel();
                recognizer.destroy();
            }
        });

        boolean openSpeak = getIntent().getBooleanExtra("OpenSpeak", false);
        if (openSpeak) {
            String[] permissions = new String[]{Manifest.permission.RECORD_AUDIO};
            if (CheckPermission.selfPermissionList(SearchViewAct.this, permissions)) {
                continueOnMicroPh();
                speachBt.setVisibility(View.GONE);
                closeBt.setVisibility(View.VISIBLE);
            }
        }

    }

    private void retrievePackages() {
        list = new ArrayList<DataSearch>();
        list.add(new DataSearch("James", "Bond is James Bond is bond "));
        list.add(new DataSearch("Jason", "Bourne"));
        list.add(new DataSearch("Ethan", "Hunt"));
        list.add(new DataSearch("Sherlock", "Holmes"));
        list.add(new DataSearch("David", "Beckham"));
        list.add(new DataSearch("Bryan", "Adams"));
        list.add(new DataSearch("Arjen", "Robben"));
        list.add(new DataSearch("Van", "Persie"));
        list.add(new DataSearch("Zinedine", "Zidane"));
        list.add(new DataSearch("Luis", "Figo"));
        list.add(new DataSearch("John", "Watson"));
//        adapter.notifyDataSetChanged();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST: {
                try {
                    boolean permissiongranted = true;
                    // If request is cancelled, the result arrays are empty.
                    if (grantResults.length > 0) {
                        for (int result : grantResults) {
                            if (result != PackageManager.PERMISSION_GRANTED) {
                                permissiongranted = false;
                            }
                        }
                        if (permissiongranted) {
//                            checkVersion();
                            continueOnMicroPh();

                        } else {
                            permisionDenied();
                        }
                    } else {
                        permisionDenied();
                        //  continueService();
                    }
                    return;
                } catch (Exception e) {
                    Toast.makeText(SearchViewAct.this, R.string.app_per_check, Toast.LENGTH_SHORT).show();
//                    checkVersion();
                }
            }
        }
    }

    private void continueOnMicroPh() {
        listnSpeach();
    }

    private void permisionDenied() {
        new AlertMsgDialog(SearchViewAct.this, AlertMsgDialog.DIA_LOGOUT, "Allow Access", "You need to allow access to microphone to use this feature", "ok", null).showPage();
    }

    private void listnSpeach() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE,
                "net.promasoft.trawellmate");

        recognizer = SpeechRecognizer
                .createSpeechRecognizer(SearchViewAct.this);
        RecognitionListener listener = new RecognitionListener() {
            @Override
            public void onResults(Bundle results) {
                ArrayList<String> voiceResults = results
                        .getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                if (voiceResults == null) {
                    System.out.println("No voice results");
                } else {
                    System.out.println("Printing matches: ");
                    for (String match : voiceResults) {
                        System.out.println(match);
                    }
                    if (voiceResults.size() > 0) {
                        searchEditText.setText("" + voiceResults.get(0));
                        searchEditText.setSelection(voiceResults.get(0).length());
                        searchEditText.setHint("Where are you going?");
//                        closeBt.setVisibility(View.GONE);
//                        speachBt.setVisibility(View.VISIBLE);
                        isSpeaking = false;

                    }
                }
            }

            @Override
            public void onReadyForSpeech(Bundle params) {
                System.out.println("Ready for speech");
                searchEditText.setText("");
                searchEditText.setHint("Speak Now...");
            }

            /**
             *  ERROR_NETWORK_TIMEOUT = 1;
             *  ERROR_NETWORK = 2;
             *  ERROR_AUDIO = 3;
             *  ERROR_SERVER = 4;
             *  ERROR_CLIENT = 5;
             *  ERROR_SPEECH_TIMEOUT = 6;
             *  ERROR_NO_MATCH = 7;
             *  ERROR_RECOGNIZER_BUSY = 8;
             *  ERROR_INSUFFICIENT_PERMISSIONS = 9;
             *
             * @param error code is defined in SpeechRecognizer
             */
            @Override
            public void onError(int error) {
                System.err.println("Error listening for speech: " + error);
            }

            @Override
            public void onBeginningOfSpeech() {
                System.out.println("Speech starting");
            }

            @Override
            public void onBufferReceived(byte[] buffer) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onEndOfSpeech() {
                // TODO Auto-generated method stub
                searchEditText.setHint("Processing...");

            }

            @Override
            public void onEvent(int eventType, Bundle params) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onPartialResults(Bundle partialResults) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onRmsChanged(float rmsdB) {
                // TODO Auto-generated method stub

            }
        };
        recognizer.setRecognitionListener(listener);
        recognizer.startListening(intent);
    }

}
