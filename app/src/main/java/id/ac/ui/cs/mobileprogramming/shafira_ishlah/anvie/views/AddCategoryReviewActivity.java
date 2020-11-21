package id.ac.ui.cs.mobileprogramming.shafira_ishlah.anvie.views;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import id.ac.ui.cs.mobileprogramming.shafira_ishlah.anvie.R;
import id.ac.ui.cs.mobileprogramming.shafira_ishlah.anvie.repositories.ReviewRepo;
import id.ac.ui.cs.mobileprogramming.shafira_ishlah.anvie.models.CategoryReview;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddCategoryReviewActivity extends AppCompatActivity {

    @BindView(R.id.edtCategoryName)
    EditText edtCategoryName;
    @BindView(R.id.edtDescription)
    EditText edtDescription;
    @BindView(R.id.btnSave)
    Button btnSave;

    ReviewRepo reviewRepo;
    String categoryName, description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);
        ButterKnife.bind(this);

        Toolbar tbAddReview = findViewById(R.id.toolbar);
        tbAddReview.setTitle("Add Category");
        setSupportActionBar(tbAddReview);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        reviewRepo = ReviewRepo.createDatabase(this);

    }

    @OnClick(R.id.btnSave)
    public void onViewClicked() {

        getData();

        saveData();

        clearData();

        Toast.makeText(this, "Saved!", Toast.LENGTH_SHORT).show();

        finish();
    }

    private void clearData() {
        edtCategoryName.setText("");
        edtDescription.setText("");
    }

    private void saveData() {

        // Membuat object KelasModel untuk menampung data
        CategoryReview categoryReview = new CategoryReview();

        // Memasukkan data ke dalam KelasModel
        categoryReview.setCategory_name(categoryName);
        categoryReview.setDescription(description);

        // Perintah untuk melakukan operasi Insert menggunakan siswaDatabase
        reviewRepo.daoClass().insert(categoryReview);

    }

    private void getData() {
        categoryName = edtCategoryName.getText().toString();
        description = edtDescription.getText().toString();
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