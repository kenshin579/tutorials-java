package sample.di.dataaccess;

import com.google.code.ssm.api.ParameterValueKeyProvider;
import com.google.code.ssm.api.ReadThroughAssignCache;
import com.google.code.ssm.api.ReadThroughMultiCache;
import com.google.code.ssm.api.ReadThroughSingleCache;
import org.springframework.stereotype.Repository;
import sample.di.business.domain.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class ProductDaoImpl implements ProductDao {

    // RDB의 대체
    private Map<String, Product> storage = new HashMap<String, Product>();

    @ReadThroughAssignCache(namespace = "area", assignedKey="all")
    public List<Product> findAllProducts() {
        slowly(); // 고의로 지연시킴
        List<Product> productList = new ArrayList<>();

        return storage.values().stream().collect(Collectors.toList());
    }

    @ReadThroughSingleCache(namespace = "area")
    public Product findProduct(@ParameterValueKeyProvider String name) {
//    public Product findProductByReadThroughSingleCache(String name) {
        slowly(); // 고의로 지연시킴
        return storage.get(name);
    }

    @ReadThroughMultiCache(namespace = "area")
    public List<Integer> getIncrementValue(@ParameterValueKeyProvider List<Integer> nums, int incrementValue) {
        return nums.stream().map(x -> x + incrementValue).collect(Collectors.toList());
    }

    //    @ReadThroughSingleCache(namespace = "area")
    public int getPrice(@ParameterValueKeyProvider String name) {
        slowly(); // 고의로 지연시킴
        return storage.get(name).getPrice();
    }

    /**
     * value : 캐싱 이름
     * key : 인자의 프로퍼티
     *
     * @param product
     * @CacheEvict : 키에 해당하는 캐시가 있으면 삭제하고 데이터를 읽어들이면 캐싱함
     */
    //@CacheEvict(value = "product", allEntries = true) 모든 캐시 엔트리 삭제
//    @CacheEvict(value = "area", key = "#product.name")
//    @InvalidateAssignCache(namespace = "area", assignedKey = "product.name")
//    @UpdateAssignCache(namespace = "area")
    public void addProduct(Product product) {
        storage.put(product.getName(), product);
    }

    private void slowly() {
        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }
}