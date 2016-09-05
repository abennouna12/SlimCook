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
import ma.slimcook.slimcook.model.Category;
import ma.slimcook.slimcook.model.Product;
import me.grantland.widget.AutofitTextView;


public class RVACategories extends RecyclerView.Adapter<RVACategories.CategoryViewHlder> {


    private MyClickListener myClickListener;

    public class CategoryViewHlder extends RecyclerView.ViewHolder implements View.OnClickListener {

        CardView categoryCardView;
        AutofitTextView categoryName;
        SimpleDraweeView categoryPicture;

        public CategoryViewHlder(View itemView) {
            super(itemView);
            categoryCardView = (CardView)itemView.findViewById(R.id.categoryCardView);
            categoryName = (AutofitTextView)itemView.findViewById(R.id.categoryName);
            categoryPicture = (SimpleDraweeView)itemView.findViewById(R.id.categoryPicture);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            myClickListener.onItemClick(getAdapterPosition(), v);
        }
    }

    List<Category> categories;
    Context context;

    public RVACategories(Context context, List<Category> categories){
        this.context = context;
        this.categories = categories;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public void setOnItemClickListener(MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }

    @Override
    public CategoryViewHlder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_category, viewGroup, false);
        return new CategoryViewHlder(v);
    }

    @Override
    public void onBindViewHolder(final CategoryViewHlder productViewHolder, final int i) {

        productViewHolder.categoryName.setText(categories.get(i).getCategoryName());
        Uri uri = Uri.parse("http://slimcook.ma/site_media/"+categories.get(i).getCategoryPicture());
        productViewHolder.categoryPicture.setImageURI(uri);

    }

    @Override
    public int getItemCount() {
        return (categories == null) ? 0 : categories.size();
    }

    public interface MyClickListener {
        void onItemClick(int position, View v);
    }

}
