package id.ac.ui.cs.mobileprogramming.shafira_ishlah.anvie.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import id.ac.ui.cs.mobileprogramming.shafira_ishlah.anvie.repositories.Constant;

@Entity(tableName = "tb_review")

public class Reviews {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_review")
    private int id_review;

    @ColumnInfo(name = Constant.id_category_review)
    private int id_category_review;

    @ColumnInfo(name = "nama_review")
    private String nama_review;

    @ColumnInfo(name = "umur")
    private String umur;

    @ColumnInfo(name = "jenis_kelamin")
    private String jenis_kelamin;

    @ColumnInfo(name = "asal")
    private String asal;

    @ColumnInfo(name = "email")
    private String email;

    public int getId_review() {
        return id_review;
    }

    public void setId_review(int id_review) {
        this.id_review = id_review;
    }

    public int getId_category_review() {
        return id_category_review;
    }

    public void setId_category_review(int id_category_review) {
        this.id_category_review = id_category_review;
    }

    public String getNama_review() {
        return nama_review;
    }

    public void setNama_review(String nama_review) {
        this.nama_review = nama_review;
    }

    public String getUmur() {
        return umur;
    }

    public void setUmur(String umur) {
        this.umur = umur;
    }

    public String getJenis_kelamin() {
        return jenis_kelamin;
    }

    public void setJenis_kelamin(String jenis_kelamin) {
        this.jenis_kelamin = jenis_kelamin;
    }

    public String getAsal() {
        return asal;
    }

    public void setAsal(String asal) {
        this.asal = asal;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}