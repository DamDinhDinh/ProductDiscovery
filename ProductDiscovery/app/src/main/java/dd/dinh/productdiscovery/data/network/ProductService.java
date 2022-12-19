package dd.dinh.productdiscovery.data.network;

import java.util.List;

import dd.dinh.productdiscovery.data.model.Product;
import io.reactivex.Single;
import retrofit2.http.GET;

public interface ProductService {

    String BASE_URL = "https://run.mocky.io/v3/";

    @GET("7af6f34b-b206-4bed-b447-559fda148ca5/")
    Single<List<Product>> listProduct();
}
