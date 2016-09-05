package ma.slimcook.slimcook.service;

import android.os.AsyncTask;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import ma.slimcook.slimcook.GlobalState;
import ma.slimcook.slimcook.fragments.CategoriesFragment;
import ma.slimcook.slimcook.fragments.ProductsFragment;
import ma.slimcook.slimcook.model.Category;
import ma.slimcook.slimcook.model.Product;
import ma.slimcook.slimcook.model.Variante;
import ma.slimcook.slimcook.parser.JSONParser;

/**
 * Created by aBennouna on 06/07/16.
 */

public class JSONCategories extends AsyncTask<Void, Integer, List<Category>> {

    GlobalState gs;
    CategoriesFragment obj;
    JSONParser parser = new JSONParser();
    JSONArray CategoryResult;
    Gson gson;

    public JSONCategories(CategoriesFragment obj)
    {
        this.obj = obj;
        this.gs = obj.gs;
        gson = new Gson();
    }

    @Override
    protected List<Category> doInBackground(Void... params) {


        List <Category> categories = new ArrayList<>();

        String url = "http://slimcook.bennouna.fr/getCategories.php";
        try {

            CategoryResult = parser.getObject(url).getJSONArray("");

            for (int i = 0 ; i < CategoryResult.length() ; i++) {

                JSONObject categoryJson = CategoryResult.getJSONObject(i);

                Category category = new Category();
                category.setCategoryName(categoryJson.getString("Name"));
                category.setCategoryPicture(categoryJson.getString("image"));
                category.setId(categoryJson.getInt("id"));

                categories.add(category);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return categories;
    }

    @Override
    protected void onPostExecute(List<Category> result) {
        obj.addCategory(result);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

}