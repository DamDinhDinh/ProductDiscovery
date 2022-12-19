package dd.dinh.productdiscovery.data.local;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import dd.dinh.productdiscovery.data.model.Product;
import io.reactivex.Observable;

@Dao
public interface ProductFts4Dao {

    @Query("SELECT * FROM products JOIN productFts ON products.id = productFts.id " +
        "WHERE productFts.name MATCH :search GROUP BY products.id")
    Observable<List<Product>> searchWith(String search);
}
