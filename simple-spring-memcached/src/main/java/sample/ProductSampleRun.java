package sample;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import sample.di.business.domain.Product;
import sample.di.business.service.ProductService;

public class ProductSampleRun {

    public static void main(String[] args) {
        ProductSampleRun productSampleRun = new ProductSampleRun();
        productSampleRun.execute();
    }

    public void execute() {
        ProductService productService = initProductService();

        String productName = "hoge";
        productService.addProduct(new Product(productName, 100));
        productService.findProduct(productName);
        productService.findProduct(productName);
//        productService.findProduct(productName);

//        productService.getPrice(productName);
//        productService.findProduct(productName);

//        productService.addProduct(new Product(productName, 200));
//        productService.findProduct(productName);
//        productService.findProduct(productName);
//        productService.findProduct(productName);
    }

    private ProductService initProductService() {
        @SuppressWarnings("resource")
        ApplicationContext ctx = new ClassPathXmlApplicationContext(
                "/applicationContext-cache.xml");
        ProductService productService = ctx.getBean(ProductService.class);
        return productService;
    }
}