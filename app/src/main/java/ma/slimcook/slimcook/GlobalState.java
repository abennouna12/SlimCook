package ma.slimcook.slimcook;

import android.app.Application;

import java.util.List;

import ma.slimcook.slimcook.model.Category;
import ma.slimcook.slimcook.model.Product;

/**
 * Created by aBennouna on 06/07/16.
 */
public class GlobalState extends Application {

    public List<Product> products;
    public List<Category> categories;
    public Product selectedProduct;

}
