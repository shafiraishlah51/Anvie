package id.ac.ui.cs.mobileprogramming.shafira_ishlah.anvie.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.amulyakhare.textdrawable.util.ColorGenerator;

import id.ac.ui.cs.mobileprogramming.shafira_ishlah.anvie.R;
import id.ac.ui.cs.mobileprogramming.shafira_ishlah.anvie.models.CategoryReview;
import id.ac.ui.cs.mobileprogramming.shafira_ishlah.anvie.views.UpdateCategoryReviewActivity;
import id.ac.ui.cs.mobileprogramming.shafira_ishlah.anvie.repositories.Constant;
import id.ac.ui.cs.mobileprogramming.shafira_ishlah.anvie.repositories.ReviewRepo;
import id.ac.ui.cs.mobileprogramming.shafira_ishlah.anvie.views.ListReviewActivity;
import id.ac.ui.cs.mobileprogramming.shafira_ishlah.anvie.models.Reviews;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryReviewAdapter extends RecyclerView.Adapter<CategoryReviewAdapter.ViewHolder> {

    private final Context context;
    private final List<CategoryReview> categoryReviewList;
    private Bundle bundle;
    private ReviewRepo reviewRepo;

    public CategoryReviewAdapter(Context context, List<CategoryReview> categoryReviewList) {
        this.context = context;
        this.categoryReviewList = categoryReviewList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_category_review, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        // Memindahkan data di dalam list dengan index position ke dalam KelasModel
        final CategoryReview categoryReview = categoryReviewList.get(position);

        // Menampilkan data ke layar
        holder.tvCategoryName.setText(categoryReview.getCategory_name());
        holder.tvDescription.setText(categoryReview.getDescription());

        ColorGenerator generator = ColorGenerator.MATERIAL;

        // generate random color
        int color = generator.getRandomColor();
        holder.cvCategoryReview.setCardBackgroundColor(color);

        // Onlick pada itemview
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bundle = new Bundle();

                bundle.putInt(Constant.KEY_ID_CATEGORY_REVIEW, categoryReview.getId_category_review());
                context.startActivity(new Intent(context, ListReviewActivity.class).putExtras(bundle));
            }
        });

        // Membuat onclick icon overflow
        holder.overflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                // Buat object database
               reviewRepo = reviewRepo.createDatabase(context);

                // Membuat object popumemu
                PopupMenu popupMenu = new PopupMenu(context, view);

                // Inflate menu ke dalam popupmenu
                popupMenu.inflate(R.menu.popup_menu);

                // Menampilkan menu
                popupMenu.show();

                // Onclick pada salah satu menu pada popupmenu
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.delete:

                                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                                alertDialogBuilder.setMessage("Are you sure want to delete " + categoryReview.getCategory_name() + " ?");
                                alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                        // Melakukan operasi delete data
                                        reviewRepo.daoClass().delete(categoryReview);

                                        // Menghapus data yang telash di hapus pada List
                                        categoryReviewList.remove(position);

                                        // Memberitahu bahwa data sudah hilang
                                        notifyItemRemoved(position);
                                        notifyItemRangeChanged(0, categoryReviewList.size());

                                        Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();
                                    }
                                });

                                alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                    }
                                });

                                AlertDialog alertDialog = alertDialogBuilder.create();
                                alertDialog.show();

                                break;

                            case R.id.edit:

                                // Membuat object bundle
                                bundle = new Bundle();

                                // Mengisi bundle dengan data
                                bundle.putInt(Constant.KEY_ID_CATEGORY_REVIEW, categoryReview.getId_category_review());
                                bundle.putString(Constant.KEY_CATEGORY_NAME, categoryReview.getCategory_name());
                                bundle.putString(Constant.KEY_DESCRIPTION, categoryReview.getDescription());

                                // Berpindah halaman dengan membawa data
                                context.startActivity(new Intent(context, UpdateCategoryReviewActivity.class).putExtras(bundle));
                                break;
                        }
                        return true;
                    }
                });
            }
        });

    }

    @Override
    public int getItemCount() {
        return categoryReviewList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvCategoryName)
        TextView tvCategoryName;
        @BindView(R.id.tvDescription)
        TextView tvDescription;
        @BindView(R.id.cvCategoryReview)
        CardView cvCategoryReview;
        @BindView(R.id.overflow)
        ImageButton overflow;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}