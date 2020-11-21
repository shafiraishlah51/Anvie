package id.ac.ui.cs.mobileprogramming.shafira_ishlah.anvie.views;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import id.ac.ui.cs.mobileprogramming.shafira_ishlah.anvie.R;
import id.ac.ui.cs.mobileprogramming.shafira_ishlah.anvie.adapters.ShopListAdapter;
import id.ac.ui.cs.mobileprogramming.shafira_ishlah.anvie.databinding.FragmentProductDetailBinding;
import id.ac.ui.cs.mobileprogramming.shafira_ishlah.anvie.viewmodels.ShopViewModel;

public class ProductDetailFragment extends Fragment {
    FragmentProductDetailBinding fragmentProductDetailBinding;
    ShopViewModel shopViewModel;
    public ProductDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentProductDetailBinding = FragmentProductDetailBinding.inflate(inflater, container, false);
        return fragmentProductDetailBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        shopViewModel = new ViewModelProvider(requireActivity()).get(ShopViewModel.class);
        fragmentProductDetailBinding.setShopViewModel(shopViewModel);

        NavController navController = Navigation.findNavController(view);
        fragmentProductDetailBinding.seeTrailerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProductDetailFragment.this.getActivity(), SeeTrailerActivity.class));
            }
        });
    }
}