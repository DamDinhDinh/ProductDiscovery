package dd.dinh.productdiscovery.data.local;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.RawQuery;
import androidx.room.Update;
import androidx.sqlite.db.SupportSQLiteQuery;

import java.util.List;

import dd.dinh.productdiscovery.data.model.Product;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;

@Dao
public interface ProductDao {

    @Insert
    Single<Long> insert(Product product);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertAll(List<Product> products);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    Single<Integer> update(Product product);

    @Delete
    Single<Integer> delete(Product product);

    @Query("SELECT * FROM products WHERE id = :id")
    Single<Product> getProductById(long id);

    @Query("SELECT * FROM products")
    Observable<List<Product>> getAllProduct();

    @Query("SELECT * FROM products WHERE brand = :brand")
    Observable<List<Product>> getSameKindProduct(String brand);
}
