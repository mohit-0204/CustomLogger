package com.example.customlogger;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SearchView searchView;
    private AppAdapter adapter;
    private List<AppItem> appList;
    private List<AppItem> appListTemp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.appsRecyclerView);
        searchView = findViewById(R.id.searchView);
        appList = new ArrayList<>();
        appListTemp = new ArrayList<>();

        adapter = new AppAdapter(appList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        loadApps();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                ArrayList<AppItem> tempList=new ArrayList<>();
                for (AppItem item : appList) {
                    if (item.getAppName().toLowerCase().contains(query.toLowerCase().trim())) {
                        tempList.add(item);
                    }
                    adapter.setDataList(tempList);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<AppItem> tempList=new ArrayList<>();
                for (AppItem item : appList) {
                    if (item.getAppName().toLowerCase().contains(newText.toLowerCase().trim())) {
                        tempList.add(item);
                    }
                    adapter.setDataList(tempList);
                }
                return true;
            }
        });
    }

    private void loadApps() {
        PackageManager packageManager = getPackageManager();
        Intent intent = new Intent(Intent.ACTION_MAIN, null);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> resolveInfos = packageManager.queryIntentActivities(intent, 0);
        for (ResolveInfo resolveInfo : resolveInfos) {
            String appName = resolveInfo.loadLabel(packageManager).toString();
            String packageName = resolveInfo.activityInfo.packageName;
            Drawable appIcon = resolveInfo.loadIcon(packageManager);
            appList.add(new AppItem(appName, packageName, appIcon));
        }
        adapter.notifyDataSetChanged();
    }


}
