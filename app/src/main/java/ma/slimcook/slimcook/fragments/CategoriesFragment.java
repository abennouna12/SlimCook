package ma.slimcook.slimcook.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.google.gson.Gson;

import java.util.List;

import ma.slimcook.slimcook.GlobalState;
import ma.slimcook.slimcook.R;
import ma.slimcook.slimcook.adapter.RVACategories;
import ma.slimcook.slimcook.adapter.RVAProducts;
import ma.slimcook.slimcook.model.Category;
import ma.slimcook.slimcook.model.Product;
import ma.slimcook.slimcook.service.JSONCategories;
import ma.slimcook.slimcook.service.JSONProducts;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoriesFragment extends Fragment {

    public GlobalState gs;

    RecyclerView rvCategories;

    RVACategories adapter;
    GridLayoutManager glm;
    Gson gson;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_categories, container, false);


        gs = (GlobalState) getActivity().getApplication();

        rvCategories = (RecyclerView) rootView.findViewById(R.id.rvCategories);

        glm = new GridLayoutManager(this.getContext(),2);
        rvCategories.setLayoutManager(glm);

        Fresco.initialize(this.getContext());

        gson = new Gson();
        initializeAdapter();

        JSONCategories w = new JSONCategories(this);
        w.execute();

        return rootView;
    }

    public void addCategory(List<Category> categories) {
        gs.categories = categories;
        initializeAdapter();
    }

    public void initializeAdapter() {
        adapter = new RVACategories(this.getContext(),gs.categories);
        adapter.setOnItemClickListener(new RVACategories
                .MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {

                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                ProductsFragment fragment = new ProductsFragment();
                fragment.setCategoryId(gs.categories.get(position).getId());
                fragmentTransaction.replace(R.id.fragment_container, fragment);
                fragmentTransaction.commit();

            }
        });
        rvCategories.setAdapter(adapter);
    }

    public CategoriesFragment() {
        // Required empty public constructor
    }


}