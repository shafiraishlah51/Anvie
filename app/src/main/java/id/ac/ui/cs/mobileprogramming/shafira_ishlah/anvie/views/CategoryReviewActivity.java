package id.ac.ui.cs.mobileprogramming.shafira_ishlah.anvie.views;

import id.ac.ui.cs.mobileprogramming.shafira_ishlah.anvie.R;
import id.ac.ui.cs.mobileprogramming.shafira_ishlah.anvie.adapters.CategoryReviewAdapter;
import id.ac.ui.cs.mobileprogramming.shafira_ishlah.anvie.models.CategoryReview;

import id.ac.ui.cs.mobileprogramming.shafira_ishlah.anvie.repositories.ReviewRepo;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryReviewActivity extends AppCompatActivity {

    private int STORAGE_PERMISSION_CODE = 1;

    @BindView(R.id.rvCategoryReview)
    RecyclerView rvCategoryReview;

    private ReviewRepo reviewRepo;
    private List<CategoryReview> categoryReviewList;

    static {
        System.loadLibrary("native-lib");
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native int Jniint();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_review);
        ButterKnife.bind(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Membuat object database
        reviewRepo = ReviewRepo.createDatabase(this);

        // Membuat object List
        categoryReviewList = new ArrayList<>();

        //Implementasi JNI
        ExtendedFloatingActionButton fabnum = findViewById(R.id.fab);
        fabnum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Our magic JNI number is :" + Jniint() + " !", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //Implementasi perizinan external storage
        ExtendedFloatingActionButton fab = findViewById(R.id.fabAdd);
        fab.setOnClickListener(new  View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(CategoryReviewActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    startActivity(new Intent(CategoryReviewActivity.this, AddCategoryReviewActivity.class));
                    Toast.makeText(CategoryReviewActivity.this, "You have already granted this permission!",
                            Toast.LENGTH_SHORT).show();
                } else {
                    requestStoragePermission();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Mengosongkan List agar dipastikan list dapat disi dengan data yg paling baru
        categoryReviewList.clear();

        getData();

        // Mensetting dan proses menampilkan data ke RecyclerView
        rvCategoryReview.setLayoutManager(new GridLayoutManager(this, 2));
        rvCategoryReview.setAdapter(new CategoryReviewAdapter(this, categoryReviewList));
    }

    private void getData() {
        // Operasi mengambil data dari database Sqlite
        categoryReviewList = reviewRepo.daoClass().select();
    }

    private void requestStoragePermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)) {
            new AlertDialog.Builder(this)
                    .setTitle("Permission needed")
                    .setMessage("This permission is needed because we need to use your external storage")
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(CategoryReviewActivity.this,
                                    new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
                        }
                    })
                    .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create().show();
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == STORAGE_PERMISSION_CODE)  {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission GRANTED", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Permission DENIED", Toast.LENGTH_SHORT).show();
            }
        }
    }






}