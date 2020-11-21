package id.ac.ui.cs.mobileprogramming.shafira_ishlah.anvie.views;


import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import id.ac.ui.cs.mobileprogramming.shafira_ishlah.anvie.R;
import id.ac.ui.cs.mobileprogramming.shafira_ishlah.anvie.repositories.Constant;
import id.ac.ui.cs.mobileprogramming.shafira_ishlah.anvie.repositories.ReviewRepo;
import id.ac.ui.cs.mobileprogramming.shafira_ishlah.anvie.models.Reviews;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddReviewActivity extends AppCompatActivity {

    @BindView(R.id.edtMovieName)
    EditText edtMovieName;
    @BindView(R.id.edtRatingScore)
    EditText edtRatingScore;
    @BindView(R.id.radio_recommended)
    RadioButton radioRecommended;
    @BindView(R.id.radio_not_recommended)
    RadioButton radioNotRecommended;
    @BindView(R.id.edtComment)
    EditText edtComment;
    @BindView(R.id.btnSave)
    Button btnSave;
    @BindView(R.id.radio_recommended_group)
    RadioGroup radioRecommendedGroup;

    ReviewRepo reviewRepo;
    int id_category_review;
    String movieName, comment, ratingScore, recommended;
    boolean empty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_review);
        ButterKnife.bind(this);

        Toolbar tbDetailDokter = findViewById(R.id.toolbar);
        tbDetailDokter.setTitle("Add Movie Review");
        setSupportActionBar(tbDetailDokter);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        id_category_review = getIntent().getIntExtra(Constant.KEY_ID_CATEGORY_REVIEW, 0);

        reviewRepo = ReviewRepo.createDatabase(this);
    }

    @OnClick(R.id.btnSave)
    public void onViewClicked() {

        // Memastikan semuanya terisi
        cekData();

        if (!empty) {
            saveData();
            clearData();
            Toast.makeText(this, "Saved!", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Not completed!", Toast.LENGTH_SHORT).show();
        }
    }

    private void clearData() {
        edtMovieName.setText("");
        edtRatingScore.setText("");
        edtComment.setText("");
        radioRecommendedGroup.clearCheck();
    }

    private void saveData() {

        // Membuat penampung dengan membaut object SiswaModel
        Reviews reviews = new Reviews();

        // kita masukkan data ke dalam siswaModel
        reviews.setId_category_review(id_category_review);
        reviews.setMovie_name(movieName);
        reviews.setComment(comment);
        reviews.setRating_score(ratingScore);
        reviews.setRecommended(recommended);

        // Kita lakukan operasi insert
        reviewRepo.daoClass().insertReview(reviews);
    }

    private void cekData() {
        movieName = edtMovieName.getText().toString();
        comment = edtComment.getText().toString();
        ratingScore = edtRatingScore.getText().toString();
        empty = movieName.isEmpty() || comment.isEmpty() || ratingScore.isEmpty() || recommended.isEmpty();
    }

    @OnClick({R.id.radio_recommended, R.id.radio_not_recommended})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.radio_recommended:
                recommended = radioRecommended.getText().toString();
                break;
            case R.id.radio_not_recommended:
                recommended = radioNotRecommended.getText().toString();
                break;
        }
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