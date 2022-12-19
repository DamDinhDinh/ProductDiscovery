package dd.dinh.productdiscovery.data.local;

import androidx.room.Entity;
import androidx.room.Fts4;

import dd.dinh.productdiscovery.data.model.Product;

@Fts4(contentEntity = Product.class)
@Entity(tableName = "productFts")
public class ProductFts4 {
    private long id;
    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
