package com.appestan.moodsaroundme;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    DBHelper dbHelper;
    BuddyRepo buddyRepo;
    Adapter adapter;
    private int lastFirstVisibleItem = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DBHelper(MainActivity.this);
        buddyRepo = new BuddyRepo(dbHelper);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {


            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int currentFirstVisibleItem = layoutManager.findFirstVisibleItemPosition();

                if (currentFirstVisibleItem > lastFirstVisibleItem) {
                    // hide
                    findViewById(R.id.add_new_buddy_imageview).setVisibility(View.INVISIBLE);
                } else if (currentFirstVisibleItem <= lastFirstVisibleItem) {
                    // show
                    findViewById(R.id.add_new_buddy_imageview).setVisibility(View.VISIBLE);
                }
                lastFirstVisibleItem = currentFirstVisibleItem;
            }
        });


        ArrayList<Friend> friendArrayList = loadData();

        adapter = new Adapter(friendArrayList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));


        findViewById(R.id.add_new_buddy_imageview).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayAddNewBuddyDialog(view);

            }
        });


    }

    @NonNull
    private ArrayList<Friend> loadData() {
        ArrayList<Friend> friendArrayList = new ArrayList<>();
        Cursor cursor = buddyRepo.loadAllBuddies();

        if (cursor.moveToNext()) {
            cursor.moveToFirst();
            do {
                Log.d(TAG, "onClick: " + cursor.getString(cursor.getColumnIndex("name")));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.YEAR, Integer.valueOf(cursor.getString(cursor.getColumnIndex("birthdayYear"))));
                calendar.set(Calendar.MONTH, Integer.valueOf(cursor.getString(cursor.getColumnIndex("birthdayMonth"))));
                calendar.set(Calendar.DAY_OF_MONTH, Integer.valueOf(cursor.getString(cursor.getColumnIndex("birthdayDay"))));
                Friend friend = new Friend();
                friend.setName(name);
                friend.setBirthday(calendar.getTime());
                friendArrayList.add(friend);

            } while (cursor.moveToNext());
        }
        return friendArrayList;
    }

    private void displayAddNewBuddyDialog(View view) {
        MaterialDialog dialog = new MaterialDialog.Builder(this)
                .title(R.string.addNewBuddy)
                .customView(R.layout.dialog_custom_view, false)
                .positiveText(R.string.addButtonInDialog).onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(MaterialDialog dialog, DialogAction which) {

                        EditText yearEditText = dialog.getView().findViewById(R.id.year_edittext);
                        EditText monthEditText = dialog.getView().findViewById(R.id.month_edittext);
                        EditText dayEditText = dialog.getView().findViewById(R.id.day_edittext);
                        EditText nameOfEditText = dialog.getView().findViewById(R.id.name_of_buddy_edittext);

                        Birthday birthday = new Birthday();
                        birthday.setYearDateInt(Utils.validateYearEditText(yearEditText));
                        birthday.setMonthDateInt(Utils.validateMonthEditText(monthEditText));
                        birthday.setDayDateInt(Utils.validateMonthEditText(dayEditText));


                        if (!Utils.validateBirthDay(birthday)) {
                            Toast.makeText(MainActivity.this, "Birthday Not Valid!", Toast.LENGTH_SHORT).show();
                        } else if (!Utils.validateName(nameOfEditText)) {
                            Toast.makeText(MainActivity.this, "Name Not Valid!", Toast.LENGTH_SHORT).show();
                        } else {
                            addNewBuddy(Utils.fetchName(nameOfEditText), birthday);
                        }

                    }
                }).build();
        dialog.show();

    }

    private void addNewBuddy(String nameString, Birthday birthday) {
        Log.d(TAG, "addNewBuddy: " + nameString + ", " + birthday.toString());
        Friend friend = new Friend();
        friend.setName(nameString);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, birthday.getYearDateInt());
        calendar.set(Calendar.MONTH, birthday.getMonthDateInt());
        calendar.set(Calendar.DAY_OF_MONTH, birthday.getDayDateInt());
        friend.setBirthday(calendar.getTime());
        friend.setDay_(birthday.getDayDateInt() + "");
        friend.setMonth_(birthday.getMonthDateInt() + "");
        friend.setYear_(birthday.getYearDateInt() + "");
        buddyRepo.save(friend);

        ArrayList<Friend> friendArrayList = loadData();
        adapter.setFriendArrayList(friendArrayList);
        adapter.notifyDataSetChanged();


    }

}
