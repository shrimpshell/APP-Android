package com.example.hsinhwang.shrimpshell;

import android.content.Intent;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.Window;

import com.example.hsinhwang.shrimpshell.Authentication.LoginActivity;
import com.example.hsinhwang.shrimpshell.Classes.Common;
import com.example.hsinhwang.shrimpshell.Classes.LogIn;
<<<<<<< HEAD
import com.example.hsinhwang.shrimpshell.InstantEmployeePanel.EmployeeCallService;
import com.example.hsinhwang.shrimpshell.InstantEmployeePanel.EmployeeCleanService;
=======
import com.example.hsinhwang.shrimpshell.Classes.MainOptions;
import com.example.hsinhwang.shrimpshell.EmployeePanel.EmployeeHomeActivity;
>>>>>>> b35a483fedc46feae97f9807203e1466b17dce0f

public class MainActivity extends AppCompatActivity {
    private Window window;
    boolean login = false;
    BottomNavigationView navigation;

    int request_code = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        int count = getSupportFragmentManager().getBackStackEntryCount();
        if (keyCode == KeyEvent.KEYCODE_BACK && count == 0) {
            new AlertDialogFragment().show(getSupportFragmentManager(), "exit");
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.item_booking:
                    fragment = new BookingFragment();
                    changeFragment(fragment);
                    setTitle(R.string.booking);
                    return true;
<<<<<<< HEAD
                case R.id.item_instant:
                    Intent intent1 = new Intent(MainActivity.this,InstantActivity.class);
                    startActivity(intent1);
                    return true;
                case R.id.item_profile:
                    if (LogIn.EmployeeLogIn()) {
                            Intent intent = new Intent(MainActivity.this, EmployeeCallService.class);
                            startActivity(intent);
                        } else {
                            fragment = new ProfileFragment();
                            changeFragment(fragment);
                            setTitle(R.string.profile);
=======
                case R.id.item_reserved:
                    fragment = new ReservedFragment();
                    changeFragment(fragment);
                    setTitle(R.string.instant);
                    return true;
                case R.id.item_profile:
                    SharedPreferences page = getSharedPreferences(Common.PAGE, MODE_PRIVATE);
                    int pageId = page.getInt("page", 0);

                    switch (pageId) {
                        case 3:
                            Intent intent3 = new Intent(MainActivity.this, EmployeeHomeActivity.class);
                            startActivityForResult(intent3, 3);
                            break;
                        case 2:
                            fragment = new ProfileFragment();
                            changeFragment(fragment);
                            setTitle(R.string.profile);
                            break;
                        default:
                            Intent intent1 = new Intent(MainActivity.this, LoginActivity.class);
                            startActivityForResult(intent1, 1);
>>>>>>> b35a483fedc46feae97f9807203e1466b17dce0f
                    }

                    return true;
                default:
                    item.setChecked(true);
                    initContent();
                    return true;
            }
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initContent();
        initialization();

    }

    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences page = getSharedPreferences(Common.PAGE, MODE_PRIVATE);
        int pageId = page.getInt("page", 0);

        switch (pageId) {
            case 2:
                if (request_code == 2) {
                    Fragment profileFragment = new ProfileFragment();
                    changeFragment(profileFragment);
                    setTitle(R.string.profile);
                    navigation.setSelectedItemId(R.id.item_profile);
                    break;
                }
            default:
                if (request_code == 0) {
                    Fragment homeFragment = new HomeFragment();
                    changeFragment(homeFragment);
                    setTitle(R.string.profile);
                    navigation.setSelectedItemId(R.id.item_home);
                }
        }
    }

    private void initialization() {
        navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window = getWindow();
            window.setStatusBarColor(Color.parseColor("#01728B"));
        }
    }

    private void initContent() {
        Fragment fragment = new HomeFragment();
        changeFragment(fragment);
        setTitle(R.string.home);
    }

    private void changeFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction =
                fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content, fragment);
        fragmentTransaction.commit();
    }

    public static class AlertDialogFragment extends DialogFragment implements DialogInterface.OnClickListener {
        @NonNull
        @Override
        public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
            return new AlertDialog.Builder(getActivity())
                    .setTitle(R.string.title_exit)
                    .setMessage(R.string.confirm_exit)
                    .setPositiveButton(R.string.confirm, this)
                    .setNegativeButton(R.string.cancel, this)
                    .create();
        }

        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            switch (i) {
                case DialogInterface.BUTTON_POSITIVE:
                    if (getActivity() != null) {
                        getActivity().finish();
                    }
                    break;
                default:
                    dialogInterface.cancel();
                    break;
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            request_code = requestCode;
            switch (requestCode) {
                case 1:
                    Fragment homeFragment = new HomeFragment();
                    changeFragment(homeFragment);
                    break;
                case 2:
                    Fragment profileFragment = new ProfileFragment();
                    changeFragment(profileFragment);
                    break;
                case 3:
                    Intent intent = new Intent(MainActivity.this, EmployeeHomeActivity.class);
                    startActivity(intent);
                    break;
                case 4:
                    Fragment BookingFragment = new BookingFragment();
                    changeFragment(BookingFragment);
            }
        }

    }

}
