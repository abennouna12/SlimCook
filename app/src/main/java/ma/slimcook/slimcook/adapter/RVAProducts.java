package ma.slimcook.slimcook.adapter;


import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import ma.slimcook.slimcook.R;
import ma.slimcook.slimcook.model.Product;
import me.grantland.widget.AutofitTextView;


public class RVAProducts extends RecyclerView.Adapter<RVAProducts.ProductViewHolder> {


    private MyClickListener myClickListener;

    public class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        CardView productCardView;
        AutofitTextView productName;
        TextView productPrice;
        SimpleDraweeView productPicture;

        public ProductViewHolder(View itemView) {
            super(itemView);
            productCardView = (CardView)itemView.findViewById(R.id.productCardView);
            productName = (AutofitTextView)itemView.findViewById(R.id.productName);
            productPrice = (TextView)itemView.findViewById(R.id.productPrice);
            productPicture = (SimpleDraweeView)itemView.findViewById(R.id.productPicture);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            myClickListener.onItemClick(getAdapterPosition(), v);
        }
    }

    List<Product> products;
    Context context;

    public RVAProducts(Context context, List<Product> products){
        this.context = context;
        this.products = products;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public void setOnItemClickListener(MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_product, viewGroup, false);
        return new ProductViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ProductViewHolder productViewHolder, final int i) {

        productViewHolder.productName.setText(products.get(i).getProductName());
        productViewHolder.productPrice.setText(products.get(i).getSelectedVariante().getPrice() + " Dhs");
        Uri uri = Uri.parse(products.get(i).getProductPicture()+".720x500_q85.jpg");
        productViewHolder.productPicture.setImageURI(uri);

        }

    @Override
    public int getItemCount() {
        return (products == null) ? 0 : products.size();
    }

    public interface MyClickListener {
        void onItemClick(int position, View v);
    }

}
