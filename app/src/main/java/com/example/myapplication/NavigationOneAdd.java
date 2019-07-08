package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Retrofit.IMyService;
import com.example.myapplication.Retrofit.RetrofitClient;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class NavigationOneAdd extends AppCompatActivity {

    String newName;
    String newPhone;
    String newEmail;
    String account;
    ImageView userImage;

    CompositeDisposable compositeDisposable = new CompositeDisposable();
    IMyService iMyService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addcontact);

        Retrofit retrofit = RetrofitClient.getInstance();
        iMyService = retrofit.create(IMyService.class);

        account = getIntent().getStringExtra("account");
        userImage = (ImageView) findViewById(R.id.imagebutton);
    }


    public void onAddButtonClicked(View view) {

        EditText editName = (EditText) findViewById(R.id.inputname_contact);
        newName = editName.getText().toString();

        EditText editNumber = (EditText) findViewById(R.id.inputnum_contact);
        newPhone = editNumber.getText().toString();

        EditText editEmail = (EditText) findViewById(R.id.inputemail_contact);
        newEmail = editEmail.getText().toString();



        if (newName.equals("")) {
            Toast.makeText(getApplicationContext(), "이름을 입력하세요", Toast.LENGTH_SHORT).show();
        } else if (newPhone.equals("")) {
            Toast.makeText(getApplicationContext(), "전화번호를 입력하세요", Toast.LENGTH_SHORT).show();
        } else {
            addContacts(account, newName, newPhone, newEmail);

            Intent intent = new Intent(NavigationOneAdd.this, TabActivity.class);
            intent.putExtra("account", account);
            startActivity(intent);
        }
    }

    private void addContacts(String user, String name, String phone, String email) {
        compositeDisposable.add(iMyService.makeContact(user, name, phone, email)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String string) throws Exception {
                        Toast.makeText(getApplicationContext(), "" + string, Toast.LENGTH_SHORT).show();
                    }
                }));
    }
}
