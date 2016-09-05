package ma.slimcook.slimcook.service;

import android.os.AsyncTask;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import ma.slimcook.slimcook.GlobalState;
import ma.slimcook.slimcook.fragments.ProductsFragment;
import ma.slimcook.slimcook.model.Product;
import ma.slimcook.slimcook.model.Variante;
import ma.slimcook.slimcook.parser.JSONParser;

/**
 * Created by aBennouna on 06/07/16.
 */

public class JSONProducts extends AsyncTask<Integer, Integer, List<Product>> {

    GlobalState gs;
    ProductsFragment obj;
    JSONParser ProductsParser = new JSONParser();
    JSONArray ProductsResult;
    Gson gson;

    public JSONProducts(ProductsFragment obj)
    {
        this.obj = obj;
        this.gs = obj.gs;
        gson = new Gson();
    }

    @Override
    protected List<Product> doInBackground(Integer... params) {


        List <Product> products = new ArrayList<>();

        String url = "http://slimcook.bennouna.fr/getProducts.php?id=" + params[0];
        try {
            ProductsResult = ProductsParser.getObject(url).getJSONArray("");


            for (int i = 0 ; i < ProductsResult.length() ; i++) {

                JSONObject produitJSON = ProductsResult.getJSONObject(i);
                JSONArray variantes = produitJSON.getJSONArray("Variante");

                Product product = new Product();
                product.setProductName(produitJSON.getString("Name"));
                product.setProductDescription(produitJSON.getString("Description"));
                product.setProductPicture(produitJSON.getString("main_image"));
                        product.setProductVariante(new ArrayList<Variante>());

                for(int j = 0; j < variantes.length() ; j++) {
                    JSONObject varianteJSON = variantes.getJSONObject(j);
                    Variante variante = new Variante();
                    variante.setId(varianteJSON.getInt("id"));
                    variante.setPrice(varianteJSON.getString("Prix"));
                    variante.setName(varianteJSON.getString("Name"));
                    product.getProductVariante().add(variante);
                }
                product.setSelectedVariante(product.getProductVariante().get(0));
                products.add(product);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return products;
    }

    @Override
    protected void onPostExecute(List<Product> result) {
        obj.addProducts(result);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

}