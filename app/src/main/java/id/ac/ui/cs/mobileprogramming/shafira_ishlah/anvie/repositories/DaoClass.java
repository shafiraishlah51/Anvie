package id.ac.ui.cs.mobileprogramming.shafira_ishlah.anvie.repositories;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import id.ac.ui.cs.mobileprogramming.shafira_ishlah.anvie.models.CategoryReview;
import id.ac.ui.cs.mobileprogramming.shafira_ishlah.anvie.models.Reviews;

import java.util.List;

@Dao
public interface DaoClass {

    // Mengambil data
    @Query("SELECT * FROM REVIEW ORDER BY category_name ASC")
    List<CategoryReview> select();

    // Memasukkan data
    @Insert
    void insert(CategoryReview categoryReview);

    // Menghapus data
    @Delete
    void delete(CategoryReview categoryReview);

    // Mengupdate data
    @Update
    void update(CategoryReview categoryReview);

    // Mengambil data siswa
    @Query("SELECT * FROM TB_REVIEW WHERE id_category_review = :id_category_review ORDER BY movie_name ASC")
    List<Reviews> selectReview(int id_category_review);

    // Memasukkan data siswa
    @Insert
    void insertReview(Reviews reviews);

    // Menghapus data siswa
    @Delete
    void deleteReview(Reviews reviews);

    // Mengupdate data
    @Update
    void updateReview(Reviews reviews);
}