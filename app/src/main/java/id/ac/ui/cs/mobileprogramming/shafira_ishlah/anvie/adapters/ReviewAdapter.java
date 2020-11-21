package id.ac.ui.cs.mobileprogramming.shafira_ishlah.anvie.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import id.ac.ui.cs.mobileprogramming.shafira_ishlah.anvie.R;
import id.ac.ui.cs.mobileprogramming.shafira_ishlah.anvie.repositories.ReviewRepo;
import id.ac.ui.cs.mobileprogramming.shafira_ishlah.anvie.models.Reviews;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder> {

    private final Context context;
    private final List<Reviews> reviewList;
    private Bundle bundle;
    private String firstName;
    private ReviewRepo reviewRepo;

    public ReviewAdapter(Context context, List<Reviews> reviewList) {
        this.context = context;
        this.reviewList = reviewList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_review, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        // memindahkan data yang dipilih ke dalam list
        final Reviews reviews = reviewList.get(position);

        // Menampilkan data ke layar
        holder.txtNameReview.setText(reviews.getNama_review());

        // Mengambil huruf pertama
        String nama = reviews.getNama_review();
        if (!nama.isEmpty()) {
            firstName = nama.substring(0, 1);
        } else {
            firstName = " ";
        }

        ColorGenerator generator = ColorGenerator.MATERIAL;
        // generate random color
        int color = generator.getRandomColor();

        TextDrawable drawable = TextDrawable.builder().buildRound(firstName, color);
        holder.imgView.setImageDrawable(drawable);

        // Membuat onclick icon overflow
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                // Buat object database
                reviewRepo = reviewRepo.createDatabase(context);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                alertDialogBuilder.setMessage("Are you sure delete " + reviews.getNama_review() + " ?");
                alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        // Melakukan operasi delete data
                        reviewRepo.daoClass().deleteReview(reviews);

                        // Menghapus data yang telash di hapus pada List
                        reviewList.remove(position);

                        // Memberitahu bahwa data sudah hilang
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(0, reviewList.size());

                        Toast.makeText(context, "Deleted!", Toast.LENGTH_SHORT).show();
                    }
                });

                alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return reviewList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.img_view)
        ImageView imgView;
        @BindView(R.id.txt_name_review)
        TextView txtNameReview;
        @BindView(R.id.btnDelete)
        ImageButton btnDelete;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
