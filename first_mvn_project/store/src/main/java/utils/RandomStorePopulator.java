package utils;

import com.github.javafaker.Faker;

public class RandomStorePopulator {

    private Faker faker = new Faker();

    public String getProductName(String categoryName)
    {
        switch (categoryName)
        {
            case  "Food":
                    return faker.food().ingredient();
            case  "Book":
                return  faker.book().title();
            default:
                return null;
        }

    }

    public int getProductRate()
    {
        return faker.number().numberBetween(1, 10);
    }

    public Double getProductPrice()
    {
        return faker.number().randomDouble(1,1, 100);
    }
}
