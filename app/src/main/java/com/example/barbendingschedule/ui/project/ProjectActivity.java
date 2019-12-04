package com.example.barbendingschedule.ui.project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.FileProvider;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.barbendingschedule.BuildConfig;
import com.example.barbendingschedule.Model.Bar;
import com.example.barbendingschedule.Model.Project;
import com.example.barbendingschedule.R;
import com.example.barbendingschedule.ui.home.HomeActivity;
import com.example.barbendingschedule.ui.home.main.MainFragment;
import com.example.barbendingschedule.ui.project.addDetails.BarDetails;
import com.example.barbendingschedule.ui.project.fieldLengthDialog.FieldLengthDialogContract;
import com.example.barbendingschedule.ui.project.fieldLengthDialog.FieldLength_Dialog;
import com.example.barbendingschedule.utils.Constants;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Objects;

import okhttp3.ResponseBody;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

import static com.example.barbendingschedule.R.menu.project_menu;

public class ProjectActivity extends AppCompatActivity implements ProjectContract.View {
    private ProjectPresenter mPresenter;
    private TextView tvTitle;
    private ImageButton btn_home;
    private Project project;
    private FloatingActionButton flAddDetails;
    private Toolbar toolbar;
   private BarAdapter adapter;
    //private FoldingCellAdapter adapter;

    private RecyclerView rvBars;
    private String p_id;
    private static final int REQUEST_CODE = 2200;
    private static final int READ_WRITE_PERMISSION = 2212;
    private double barlength;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);
        mPresenter = new ProjectPresenter(this);
        this.project = (Project) getIntent().getSerializableExtra("project");

       adapter = new BarAdapter(this);
        //adapter = new FoldingCellAdapter(this);

        toolbar = findViewById(R.id.toolbar_project);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("");

        flAddDetails = findViewById(R.id.btn_addDetails);
        tvTitle = findViewById(R.id.toolbar_project_tv);
        btn_home = findViewById(R.id.home_btn);
        tvTitle.setText(project.getName());
        rvBars = findViewById(R.id.recycler_view_Bars);
        rvBars.setLayoutManager(new LinearLayoutManager(this));
        rvBars.setAdapter(adapter);

        flAddDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProjectActivity.this, BarDetails.class);
                intent.putExtra(Constants.PROJECT_ID,project.get_id());
                startActivityForResult(intent,REQUEST_CODE);
            }
        });

        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(ProjectActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
        mPresenter.getAllBar(project.get_id());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==REQUEST_CODE && resultCode== RESULT_OK){
            Log.d("REQUEST_CODE","ok") ;
            assert data != null;
            Bar bar = (Bar) data.getSerializableExtra(Constants.BAR);
            adapter.addBar(bar);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(project_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.excel:
                //open Excel file
                requestFileAfterPermission();
                break;
            case R.id.optimize:
               // Toast.makeText(this, "optimization pressed", Toast.LENGTH_SHORT).show();
                openDialog();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void openDialog() {

        FieldLength_Dialog fieldLength_dialog = new FieldLength_Dialog();
        fieldLength_dialog.setCancelable(false);
        fieldLength_dialog.show(getSupportFragmentManager(),"Dialog Box open");
    }

    @Override
    public void getProjectId(String p_id) {
        this.p_id = p_id;
    }

    @Override
    public void renderProjects(List<Bar> barList) {
        for (Bar bar : barList) {
            adapter.addBar(bar);
        }

    }

    @Override
    public void deleteBar(Bar bar) {
        mPresenter.deleteBar(bar);
    }

    @Override
    public void removeFromAdapter(Bar bar) {
        adapter.removeBar(bar);
    }


    //(Start)Save the fetched file into phone
    @Override
    public String saveFile(ResponseBody body) {
        String file = Environment.getExternalStorageDirectory().getPath()
                + File.separator+getString(R.string.app_name)
                +File.separator+project.getName();
        File dir = new File(file);

        if(!dir.exists()){
            dir.mkdirs();
        }
        String fileName = "BBS.xlsx";
        File myFile = new File(file, fileName);


        InputStream inputStream = null;
        OutputStream outputStream = null;

        try {
            byte[] fileReader = new byte[4096];

            long fileSize = body.contentLength();

            Log.d("HHHHH",fileSize+"");
            long fileSizeDownloaded = 0;

            inputStream = body.byteStream();
            outputStream = new FileOutputStream(myFile);

            while (true) {
                int read = inputStream.read(fileReader);

                if (read == -1) {
                    break;
                }

                outputStream.write(fileReader, 0, read);

                fileSizeDownloaded += read;
            }

            outputStream.flush();

            return myFile.getPath();
        } catch (IOException e) {
            Log.d("EXCEPTION","ioexception");
            return null;
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    //(End)Save the fetched file into phone


    @Override
    public void showToast(String massage) {
        Toast.makeText(this, massage, Toast.LENGTH_SHORT).show();
    }

    //Start Open Excel file
    @Override
    public void openFile(String path) {
        File file = new File(path);

        Intent target = new Intent(Intent.ACTION_VIEW);
        target.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        Uri uri = FileProvider.getUriForFile(this,
                BuildConfig.APPLICATION_ID+".fileprovider",file);

        target.setDataAndType(uri,"application/vnd.ms-excel");

        Intent intent = Intent.createChooser(target, "Open File");
        try {
            PackageManager pm = getPackageManager();
            if (intent.resolveActivity(pm) != null) {
                startActivity(intent);
            }
        } catch (ActivityNotFoundException e) {
            // Instruct the user to install a PDF reader here, or something
            showToast("Please Download a Excel Reader");
        }

    }//end opening


    @Override
    public void requestOptimizeFile(double bar_length) {
        Log.d("getOptimizeExcel","Successfull "+bar_length);
        barlength = bar_length;
        requestFileAfterPermissionForOptimize();

    }

    public String getPid() {
        return this.p_id;
    }



    //(Start) Ask for Storage Permission
    @AfterPermissionGranted(READ_WRITE_PERMISSION)
    private void requestFileAfterPermission() {
        String[] perms = {Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE};
        if (EasyPermissions.hasPermissions(getApplicationContext(), perms)) {
           //getExcel from Api
            mPresenter.getExcel(project.get_id());
        } else {
            // Do not have permissions, request them now
            EasyPermissions.requestPermissions(this, "App need to Permission for Read and Write Storage",
                    READ_WRITE_PERMISSION, perms);
        }
    }

    @AfterPermissionGranted(READ_WRITE_PERMISSION)
    private void requestFileAfterPermissionForOptimize() {
        String[] perms = {Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE};
        if (EasyPermissions.hasPermissions(getApplicationContext(), perms)) {
            //getExcel from Api
            mPresenter.getOptimizeExcel(project.get_id(),barlength);
           Log.d("getOptimizeExcel","p_id: "+project.get_id()+" barlength: "+barlength);
        } else {
            // Do not have permissions, request them now
            EasyPermissions.requestPermissions(this, "App need to Permission for Read and Write Storage",
                    READ_WRITE_PERMISSION, perms);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        EasyPermissions.onRequestPermissionsResult(
                requestCode, permissions, grantResults, this);
    }
    //(End)Ask for Storage Permission

}
