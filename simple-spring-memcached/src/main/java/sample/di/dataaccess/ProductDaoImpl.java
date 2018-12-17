package sample.di.dataaccess;

import com.google.code.ssm.api.ParameterValueKeyProvider;
import com.google.code.ssm.api.ReadThroughSingleCache;
import org.springframework.stereotype.Repository;
import sample.di.business.domain.Product;
import sample.di.business.service.ProductDao;

import java.util.HashMap;
import java.util.Map;

@Repository
public class ProductDaoImpl implements ProductDao {

    // RDB의 대체
    private Map<String, Product> storage = new HashMap<String, Product>();

    // Dao이지만 단순화 하기 위해 RDB에는 액세스 하지 않음

    /**
     * @param name
     * @return
     * @Cacheable : 캐시를 적용함
     */
//    @Cacheable(value = "area")
    @ReadThroughSingleCache(namespace = "area")
    public Product findProduct(@ParameterValueKeyProvider String name) {
        slowly(); // 고의로 지연시킴
        return storage.get(name);
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