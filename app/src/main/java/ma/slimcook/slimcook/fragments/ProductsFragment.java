package ma.slimcook.slimcook.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import ma.slimcook.slimcook.adapter.RVAProducts;
import ma.slimcook.slimcook.model.Product;
import ma.slimcook.slimcook.service.JSONProducts;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductsFragment extends Fragment {

    public GlobalState gs;

    RecyclerView rvProducts;

    RVAProducts adapter;
    LinearLayoutManager llm;
    Gson gson;
    int categoryId;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_products, container, false);


        gs = (GlobalState) getActivity().getApplication();

        rvProducts = (RecyclerView) rootView.findViewById(R.id.rvProducts);

        llm = new LinearLayoutManager(this.getContext());
        rvProducts.setLayoutManager(llm);
        rvProducts.setHasFixedSize(true);

        Fresco.initialize(this.getContext());

        gson = new Gson();
        initializeAdapter();

        JSONProducts w = new JSONProducts(this);
        w.execute(categoryId);

        return rootView;
    }

    public void addProducts(List<Product> products) {
        gs.products = products;
        initializeAdapter();
    }

    public void initializeAdapter() {
        adapter = new RVAProducts(this.getContext(),gs.products);
        adapter.setOnItemClickListener(new RVAProducts
                .MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                gs.selectedProduct = gs.products.get(position);

            }
        });
        rvProducts.setAdapter(adapter);
    }

    public ProductsFragment() {
        // Required empty public constructor
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}