package dd.dinh.productdiscovery.data.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import dd.dinh.productdiscovery.data.model.Product;

@Database(entities = {Product.class, ProductFts4.class}, version = 3)
public abstract class AppDatabase extends RoomDatabase {

    public static final String DB_NAME = "products_database";

    public abstract ProductDao productDao();

    public abstract ProductFts4Dao productFts4Dao();
}
