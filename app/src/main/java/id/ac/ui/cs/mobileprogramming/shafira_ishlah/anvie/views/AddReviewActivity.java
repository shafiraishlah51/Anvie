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

    @BindView(R.id.edtNamaReview)
    EditText edtNamaReview;
    @BindView(R.id.edtUmur)
    EditText edtUmur;
    @BindView(R.id.radio_laki)
    RadioButton radioLaki;
    @BindView(R.id.radio_perempuan)
    RadioButton radioPerempuan;
    @BindView(R.id.edtAsal)
    EditText edtAsal;
    @BindView(R.id.edtEmail)
    EditText edtEmail;
    @BindView(R.id.btnSave)
    Button btnSave;
    @BindView(R.id.radio_jenis_kelamin)
    RadioGroup radioJenisKelaminGroup;

    ReviewRepo reviewRepo;
    int id_category_review;
    String namaReview, asal, umur, jenis_kelamin, email;
    boolean empty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_review);
        ButterKnife.bind(this);

        Toolbar tbDetailDokter = findViewById(R.id.toolbar);
        tbDetailDokter.setTitle("Add Review");
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
        edtNamaReview.setText("");
        edtUmur.setText("");
        edtAsal.setText("");
        edtEmail.setText("");
        radioJenisKelaminGroup.clearCheck();
    }

    private void saveData() {

        // Membuat penampung dengan membaut object SiswaModel
        Reviews reviews = new Reviews();

        // kita masukkan data ke dalam siswaModel
        reviews.setId_category_review(id_category_review);
        reviews.setNama_review(namaReview);
        reviews.setAsal(asal);
        reviews.setUmur(umur);
        reviews.setJenis_kelamin(jenis_kelamin);
        reviews.setEmail(email);

        // Kita lakukan operasi insert
        reviewRepo.daoClass().insertReview(reviews);
    }

    private void cekData() {
        namaReview = edtNamaReview.getText().toString();
        asal = edtAsal.getText().toString();
        umur = edtUmur.getText().toString();
        email = edtEmail.getText().toString();

        empty = namaReview.isEmpty() || asal.isEmpty() || umur.isEmpty() || email.isEmpty() || jenis_kelamin.isEmpty();
    }

    @OnClick({R.id.radio_laki, R.id.radio_perempuan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.radio_laki:
                jenis_kelamin = radioLaki.getText().toString();
                break;
            case R.id.radio_perempuan:
                jenis_kelamin = radioPerempuan.getText().toString();
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