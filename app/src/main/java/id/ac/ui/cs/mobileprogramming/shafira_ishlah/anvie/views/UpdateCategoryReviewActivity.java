package id.ac.ui.cs.mobileprogramming.shafira_ishlah.anvie.views;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import id.ac.ui.cs.mobileprogramming.shafira_ishlah.anvie.R;
import id.ac.ui.cs.mobileprogramming.shafira_ishlah.anvie.models.CategoryReview;
import id.ac.ui.cs.mobileprogramming.shafira_ishlah.anvie.repositories.Constant;
import id.ac.ui.cs.mobileprogramming.shafira_ishlah.anvie.repositories.ReviewRepo;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UpdateCategoryReviewActivity extends AppCompatActivity {

    @BindView(R.id.edtCategoryName)
    EditText edtCategoryName;
    @BindView(R.id.edtDescription)
    EditText edtDescription;
    @BindView(R.id.btnSave)
    Button btnSave;

    Bundle bundle;
    ReviewRepo reviewRepo;
    int id_category_review;

    String category_name, description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_category_review);
        ButterKnife.bind(this);

        Toolbar tbUpdateReview = findViewById(R.id.toolbar);
        tbUpdateReview.setTitle("Update Review");
        setSupportActionBar(tbUpdateReview);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Menangkap data dari activity sebelumnya
        bundle = getIntent().getExtras();

        // Menampilkan data sebelumnya ke layar
        showData();
    }

    private void showData() {
        // mengambil data di dalam bundle
        id_category_review = bundle.getInt(Constant.KEY_ID_CATEGORY_REVIEW);
        category_name = bundle.getString(Constant.KEY_CATEGORY_NAME);
        description = bundle.getString(Constant.KEY_DESCRIPTION);

        // Menampilkan ke layar
        edtCategoryName.setText(category_name);
        edtDescription.setText(description);
    }

    @OnClick(R.id.btnSave)
    public void onViewClicked() {

        // Mengambil data
        getData();

        // Mengirim data ke sqlite
        saveData();

        Toast.makeText(this, "Saved!", Toast.LENGTH_SHORT).show();
        finish();
    }

    private void getData() {
        // Mengambil inputan user dan dimasukkan ke dalam variable
        category_name = edtCategoryName.getText().toString();
        description = edtDescription.getText().toString();
    }

    private void saveData() {

        // Membuat object kelasmodel
        CategoryReview categoryReview = new CategoryReview();

        // Memasukkan data ke kelasmodel
        categoryReview.setId_category_review(id_category_review);
        categoryReview.setCategory_name(category_name);
        categoryReview.setDescription(description);

        // Melakukan operasi update untuk mengupdate data ke sqlite
        reviewRepo.daoClass().update(categoryReview);
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