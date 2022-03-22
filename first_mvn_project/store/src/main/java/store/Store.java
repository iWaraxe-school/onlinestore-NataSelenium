package store;

import by.issoft.domain.Category;

import java.util.ArrayList;
import java.util.List;

public class Store {

    private List<Category> categoryList  = new ArrayList<Category>();

    public Store() {}

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public void addCategoryItem(Category category)
    {
        this.categoryList.add(category);
    }

    public void printAllCategories()
    {
        for (Category category : categoryList)
        {
            System.out.println(category.getCategoryName());
        }
    }
}
