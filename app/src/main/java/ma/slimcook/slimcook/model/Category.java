package ma.slimcook.slimcook.model;

/**
 * Created by aBennouna on 22/08/16.
 */
public class Category {


    private int id;
    private String categoryName;
    private String categoryPicture;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryPicture() {
        return categoryPicture;
    }

    public void setCategoryPicture(String categoryPicture) {
        this.categoryPicture = categoryPicture;
    }

    public Category() {
    }

    public Category(int id, String categoryName, String categoryPicture) {
        this.id = id;
        this.categoryName = categoryName;
        this.categoryPicture = categoryPicture;
    }
}
