package net.promasoft.trawellmate.frg;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.google.android.material.appbar.AppBarLayout;

import net.promasoft.trawellmate.DetailsAct;
import net.promasoft.trawellmate.NotificationAct;
import net.promasoft.trawellmate.R;
import net.promasoft.trawellmate.SearchViewAct;
import net.promasoft.trawellmate.util.AlertMsgDialog;
import net.promasoft.trawellmate.util.CheckPermissionHelper;
import net.promasoft.trawellmate.util.DrawerFrgListner;

import java.util.ArrayList;

import static net.promasoft.trawellmate.util.CheckPermission.MY_PERMISSIONS_REQUEST;

public class FrgHome extends Fragment {

    private Activity mActivity;
    private View view;
    private DrawerFrgListner mDrawerListner;
    private TextView searchEditText;
    private SpeechRecognizer recognizer;
    private ImageView closeBt, speachBt;

    public FrgHome(Activity activity) {
        this.mActivity = activity;
        // Required empty public constructor
    }

    public static FrgHome newInstance(Activity activity) {
        return new FrgHome(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.home_page, container, false);

        initLayoutView(view);

        return view;
    }

    private ImageView searchLay;
    public boolean searchBgVisible = false;

    private void initLayoutView(View mView) {

        ImageView menuSlider = mView.findViewById(R.id.ID_menu_iv);
        menuSlider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mDrawerListner.onDrawerClicked();
            }
        });

//        loginBt = mView.findViewById(R.id.ID_hm_login);
//        AnimHelper.slideDown(loginBt);
//
//        loginBt.setOnClickListener(view -> {
//            loginBt.clearAnimation();
//            loginBt.setVisibility(View.GONE);
//            new DialogLogin(mActivity).showPage();
//        });

        searchLay = mView.findViewById(R.id.ID_search_bg_lay);

        AppBarLayout appBarLayout = mView.findViewById(R.id.id_appBar_main);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

                if (Math.abs(verticalOffset) == appBarLayout.getTotalScrollRange()) {
                    //  Collapsed
                    if (searchLay.getVisibility() == View.GONE) {
                        searchBgVisible = true;

                        if (mDrawerListner != null) {
                            mDrawerListner.onCoordClose();
                        }
//                        if (loginBt.getVisibility() == View.VISIBLE) {
//                            AnimHelper.slideUp(loginBt);
//                        }
                        searchLay.setVisibility(View.VISIBLE);
                        searchLay.startAnimation(AnimationUtils.loadAnimation(mActivity, R.anim.fade_in_anim));
                    }

                } else if (verticalOffset == 0) {
                    // Expanded
                    if (searchLay.getVisibility() == View.VISIBLE) {
                        searchBgVisible = false;
                        searchLay.setVisibility(View.GONE);
                        searchLay.startAnimation(AnimationUtils.loadAnimation(mActivity, R.anim.fade_out_anim));
                    }
                } else if (searchBgVisible) {
                    //  Collapsed
                    if (searchLay.getVisibility() == View.VISIBLE) {
                        searchBgVisible = false;

                        if (mDrawerListner != null) {
                            mDrawerListner.onCoordOpened();
                        }
//                        if (loginBt.getVisibility() == View.VISIBLE) {
//                            AnimHelper.slideDown(loginBt);
//                        }
                        searchLay.setVisibility(View.GONE);
                        searchLay.startAnimation(AnimationUtils.loadAnimation(mActivity, R.anim.fade_out_anim));

                    }

                }

            }
        });


        ImageView notification = mView.findViewById(R.id.ID_hm_notification);
        notification.setOnClickListener(view -> {
            startActivity(new Intent(mActivity, NotificationAct.class));
        });

        TextView viewPackages = mView.findViewById(R.id.ID_view_packages);
        viewPackages.setOnClickListener(view -> {
            startActivity(new Intent(mActivity, DetailsAct.class));
        });

        CardView viewPackages1 = mView.findViewById(R.id.ID_hm_pack1);
        viewPackages1.setOnClickListener(view -> {
            startActivity(new Intent(mActivity, DetailsAct.class));
        });
        CardView viewPackages2 = mView.findViewById(R.id.ID_hm_pack2);
        viewPackages2.setOnClickListener(view -> {
            startActivity(new Intent(mActivity, DetailsAct.class));
        });
        CardView viewPackages3 = mView.findViewById(R.id.ID_hm_pack3);
        viewPackages3.setOnClickListener(view -> {
            startActivity(new Intent(mActivity, DetailsAct.class));
        });
        CardView viewPackages4 = mView.findViewById(R.id.ID_hm_pack4);
        viewPackages4.setOnClickListener(view -> {
            startActivity(new Intent(mActivity, DetailsAct.class));
        });
        CardView viewPackages5 = mView.findViewById(R.id.ID_hm_pack5);
        viewPackages5.setOnClickListener(view -> {
            startActivity(new Intent(mActivity, DetailsAct.class));
        });
        CardView viewPackages6 = mView.findViewById(R.id.ID_hm_pack6);
        viewPackages6.setOnClickListener(view -> {
            startActivity(new Intent(mActivity, DetailsAct.class));
        });

        ImageView searchICon = mView.findViewById(R.id.ID_search_icon);
        searchEditText = mView.findViewById(R.id.ID_search_et);

        searchICon.setOnClickListener(view1 -> {
            startActivity(new Intent(getActivity(), SearchViewAct.class));
            getActivity().overridePendingTransition(R.anim.slide_up_search, R.anim.fade_out_anim);
        });

        searchEditText.setOnClickListener(view1 -> {
            startActivity(new Intent(getActivity(), SearchViewAct.class));
            getActivity().overridePendingTransition(R.anim.slide_up_search, R.anim.fade_out_anim);
        });

        closeBt = mView.findViewById(R.id.ID_close_bt);
        speachBt = mView.findViewById(R.id.ID_speach_bt);
        speachBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), SearchViewAct.class);
                intent.putExtra("OpenSpeak", true);
                startActivity(intent);

//                String[] permissions = new String[]{
//                        Manifest.permission.RECORD_AUDIO,
//                };
//                if (CheckPermissionHelper.isGranded(getActivity(), permissions)) {
//                    continueOnMicroPh();
//                    speachBt.setVisibility(View.GONE);
//                    closeBt.setVisibility(View.VISIBLE);
//                } else {
//                    requestPermissions(permissions, MY_PERMISSIONS_REQUEST);
//                }
            }
        });

        closeBt.setOnClickListener(view1 -> {
            closeBt.setVisibility(View.GONE);
            searchEditText.setHint("Where are you going?");
            speachBt.setVisibility(View.VISIBLE);
            if (recognizer != null) {
                recognizer.stopListening();
            }
        });
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
                    Toast.makeText(mActivity, R.string.app_per_check, Toast.LENGTH_SHORT).show();
//                    checkVersion();
                }
            }
        }
    }

    private void continueOnMicroPh() {
        listnSpeach();
    }

    private void permisionDenied() {
        new AlertMsgDialog(mActivity, AlertMsgDialog.DIA_LOGOUT, "Allow Access", "You need to allow access to microphone to use this feature", "ok", null).showPage();
    }

    private void listnSpeach() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE,
                "com.domain.app");

        recognizer = SpeechRecognizer
                .createSpeechRecognizer(mActivity);
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
//                        searchEditText.setSelection(voiceResults.get(0).length());
                        searchEditText.setHint("Where are you going?");
                        closeBt.setVisibility(View.GONE);
                        speachBt.setVisibility(View.VISIBLE);

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


    public void setDrawerListner(DrawerFrgListner drawerListner) {
        this.mDrawerListner = drawerListner;
    }


}
