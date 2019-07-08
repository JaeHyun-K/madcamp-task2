package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.os.Bundle;
import android.text.TextWatcher;
import android.util.Log;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;

import com.example.myapplication.Retrofit.IMyService;
import com.example.myapplication.Retrofit.RetrofitClient;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.JsonArray;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.google.gson.JsonElement;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import androidx.annotation.Nullable;
import android.view.ViewGroup;
import java.text.ParseException;
import java.util.HashMap;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView.ItemDecoration;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import android.text.TextUtils;
import android.widget.Button;
import android.view.View;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.OrientationHelper;
import android.hardware.SensorManager;
import android.widget.Toast;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;


public class NavigationOne extends Fragment {
    View view;
    EditText editText;
    String account;
    private JsonParser jsonParser;
    private JsonArray jsonArray;
    private RecyclerAdapter recyclerAdapter;
    private ArrayList<Contactlist> recyclerItems = new ArrayList<>();
    private List<Contactlist> list = new ArrayList<Contactlist>();
    List<String> listName = new ArrayList<>();
    List<String> listPhone = new ArrayList<>();
    List<String> listEmail = new ArrayList<>();

    private FloatingActionButton imageButton;
    private final int ADD_RESULT = 2222;

    CompositeDisposable compositeDisposable = new CompositeDisposable();
    IMyService iMyService;

    public NavigationOne() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        account = getActivity().getIntent().getStringExtra("account");
        view = inflater.inflate(R.layout.navigation_contact, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerview_main_list);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        imageButton = (FloatingActionButton) view.findViewById(R.id.Addbutton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), NavigationOneAdd.class);
                intent.putExtra("account", account);
                startActivityForResult(intent, ADD_RESULT);
            }
        });

        recyclerAdapter = new RecyclerAdapter(getContext(), new RecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }
        }, new RecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                deleteContacts(account, recyclerItems.get(position).getName(), recyclerItems.get(position).getNumber());
            }
        });
        recyclerView.setAdapter(recyclerAdapter);

        editText = (EditText) view.findViewById(R.id.editSearch);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String text = editText.getText().toString();
                searchText(text);
            }
        });

        return view;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ADD_RESULT) {
            if (resultCode == Activity.RESULT_OK) {
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        DataProcessing();
    }

    private void getContacts(String email) {
        compositeDisposable.add((iMyService.getContacts(email)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String string) throws Exception {
                        parsingContacts(string);
                    }
                })
        ));
    }

    private void deleteContacts(String user, String erasename, String erasenumber) {
        compositeDisposable.add(iMyService.deleteContact(user, erasename, erasenumber)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String string) throws Exception {
                        Toast.makeText(getContext(), "" + string, Toast.LENGTH_SHORT).show();
                        DataProcessing();
                    }
                })
        );
    }

    private void parsingContacts(String string) {
        Log.d("TAG", string);
        jsonParser = new JsonParser();
        jsonArray = (JsonArray) jsonParser.parse(string);

        recyclerItems.clear();

        for (int i = 0; i < jsonArray.size(); i++) {
            JsonElement user = (JsonElement) jsonArray.get(i);

            listName.add(user.getAsJsonObject().get("name").getAsString());
            listPhone.add(user.getAsJsonObject().get("phone").getAsString());
            listEmail.add(user.getAsJsonObject().get("email").getAsString());


            Contactlist recyclerItem = new Contactlist();

            recyclerItem.setName(listName.get(i));
            recyclerItem.setNumber(listPhone.get(i));
            recyclerItem.setEmail(listEmail.get(i));


            recyclerItems.add(recyclerItem);
        }

        recyclerAdapter.resetItem();

        String tag = Integer.toString(recyclerItems.size());
        Log.d("TAG", tag);
        for (int i = 0; i < recyclerItems.size(); i++) {
            recyclerAdapter.addItem(recyclerItems.get(i));
        }

        recyclerAdapter.notifyDataSetChanged();
    }

    public void searchText(String text) {
        list.clear();
        recyclerAdapter.resetItem();

        if (text.length() == 0) {
            list.addAll(recyclerItems);
        } else {
            for (int i = 0; i < recyclerItems.size(); i++) {
                if (recyclerItems.get(i).getName().toLowerCase().contains(text) || recyclerItems.get(i).getNumber().toLowerCase().contains(text)) {
                    list.add(recyclerItems.get(i));
                }
            }
        }

        for (int i = 0; i < list.size(); i++) {
            recyclerAdapter.addItem(list.get(i));
        }
        recyclerAdapter.notifyDataSetChanged();
    }

    private void DataProcessing() {
        Retrofit retrofit = RetrofitClient.getInstance();
        iMyService = retrofit.create(IMyService.class);
        getContacts(account);
    }
}