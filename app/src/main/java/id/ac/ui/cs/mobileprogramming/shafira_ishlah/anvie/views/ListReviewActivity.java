package id.ac.ui.cs.mobileprogramming.shafira_ishlah.anvie.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import id.ac.ui.cs.mobileprogramming.shafira_ishlah.anvie.R;
import id.ac.ui.cs.mobileprogramming.shafira_ishlah.anvie.adapters.ReviewAdapter;
import id.ac.ui.cs.mobileprogramming.shafira_ishlah.anvie.repositories.Constant;
import id.ac.ui.cs.mobileprogramming.shafira_ishlah.anvie.repositories.ReviewRepo;
import id.ac.ui.cs.mobileprogramming.shafira_ishlah.anvie.models.Reviews;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ListReviewActivity extends AppCompatActivity {

    @BindView(R.id.rvReview)
    RecyclerView rvReview;
    @BindView(R.id.fab)
    FloatingActionButton fab;

    ReviewRepo reviewRepo;
    List<Reviews> reviewsList;
    int id_category_review;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_review);

        ButterKnife.bind(this);

        Toolbar tbDetailDokter = findViewById(R.id.toolbar);
        tbDetailDokter.setTitle("List Review");
        setSupportActionBar(tbDetailDokter);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        bundle = getIntent().getExtras();

        if (bundle != null) {
            id_category_review = bundle.getInt(Constant.KEY_ID_CATEGORY_REVIEW);
        }

        reviewRepo = ReviewRepo.createDatabase(this);

        reviewsList = new ArrayList<>();
    }

    @Override
    protected void onResume() {
        super.onResume();

        reviewsList.clear();

        getData();

        rvReview.setLayoutManager(new LinearLayoutManager(this));
        rvReview.setAdapter(new ReviewAdapter(this, reviewsList));
    }

    private void getData() {
        reviewsList = reviewRepo.daoClass().selectReview(id_category_review);
    }

    @OnClick(R.id.fab)
    public void onViewClicked() {
        startActivity(new Intent(this, AddReviewActivity.class).putExtra(Constant.KEY_ID_CATEGORY_REVIEW, id_category_review));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
