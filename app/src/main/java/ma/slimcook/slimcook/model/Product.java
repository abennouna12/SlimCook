package ma.slimcook.slimcook.model;


import java.util.List;

/**
 * Created by aBennouna on 06/07/16.
 */
public class Product {

    private int id;
    private String productName;
    private String productDescription;
    private String productPicture;
    private List<Variante> productVariante;
    private Variante selectedVariante;

    public Variante getSelectedVariante() {
        return selectedVariante;
    }

    public void setSelectedVariante(Variante selectedVariante) {
        this.selectedVariante = selectedVariante;
    }

    public Product() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductPicture() {
        return productPicture;
    }

    public void setProductPicture(String productPicture) {
        this.productPicture = productPicture;
    }

    public List<Variante> getProductVariante() {
        return productVariante;
    }

    public void setProductVariante(List<Variante> productVariante) {
        this.productVariante = productVariante;
    }
}
