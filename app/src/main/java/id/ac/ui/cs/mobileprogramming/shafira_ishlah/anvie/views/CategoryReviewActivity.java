package id.ac.ui.cs.mobileprogramming.shafira_ishlah.anvie.views;

import id.ac.ui.cs.mobileprogramming.shafira_ishlah.anvie.R;
import id.ac.ui.cs.mobileprogramming.shafira_ishlah.anvie.adapters.CategoryReviewAdapter;
import id.ac.ui.cs.mobileprogramming.shafira_ishlah.anvie.models.CategoryReview;

import id.ac.ui.cs.mobileprogramming.shafira_ishlah.anvie.repositories.ReviewRepo;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryReviewActivity extends AppCompatActivity {

    @BindView(R.id.rvCategoryReview)
    RecyclerView rvCategoryReview;

    private ReviewRepo reviewRepo;
    private List<CategoryReview> categoryReviewList;


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

        ExtendedFloatingActionButton fab = findViewById(R.id.fabAdd);
        fab.setOnClickListener(view -> startActivity(new Intent(CategoryReviewActivity.this, AddCategoryReviewActivity.class)));
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






}