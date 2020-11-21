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

    @ColumnInfo(name = "movie_name")
    private String movie_name;

    @ColumnInfo(name = "rating_score")
    private String rating_score;

    @ColumnInfo(name = "recommended")
    private String recommended;

    @ColumnInfo(name = "comment")
    private String comment;

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

    public String getMovie_name() {
        return movie_name;
    }

    public void setMovie_name(String movie_name) {
        this.movie_name = movie_name;
    }

    public String getRating_score() {
        return rating_score;
    }

    public void setRating_score(String rating_score) {
        this.rating_score = rating_score;
    }

    public String getRecommended() {
        return recommended;
    }

    public void setRecommended(String recommended) {
        this.recommended = recommended;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}