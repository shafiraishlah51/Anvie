package id.ac.ui.cs.mobileprogramming.shafira_ishlah.anvie.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import id.ac.ui.cs.mobileprogramming.shafira_ishlah.anvie.repositories.Constant;

@Entity(tableName = Constant.table_name)

public class CategoryReview {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = Constant.id_category_review)
    private int id_category_review;

    @ColumnInfo(name = Constant.category_name)
    private String category_name;

    @ColumnInfo(name = Constant.description)
    private String description;

    public int getId_category_review() {
        return id_category_review;
    }

    public void setId_category_review(int id_category_review) {
        this.id_category_review = id_category_review;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}