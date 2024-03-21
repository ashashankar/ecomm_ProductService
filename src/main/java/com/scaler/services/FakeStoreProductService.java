package com.scaler.services;

import com.scaler.dtos.FakeStoreProductDto;
import com.scaler.models.Category;
import com.scaler.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class FakeStoreProductService implements ProductService {
    private RestTemplate restTemplate;

    FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    @Override
    public Product getProductById(Long Id) {
        //call the fakestoreApi to get given id
       FakeStoreProductDto fakeStoreProductDto =  restTemplate.getForObject("https://fakestoreapi.com/products/"+Id, FakeStoreProductDto.class);
        //convert fakestoreproductdto to product
        Product product = convertFakeStoreProductDtotoProduct(fakeStoreProductDto);
       return product;
    }

    @Override
    public List<Product> getAllProducts() {
        List<FakeStoreProductDto> fakeStoreProductDtos;
        fakeStoreProductDtos = (List<FakeStoreProductDto>) restTemplate.getForEntity("https://fakestoreapi.com/products", List.class);

        return null;
    }

    @Override
    public Product updateProduct(Long Id) {
        return null;
    }

    @Override
    public Product replaceProduct(Long Id) {
        return null;
    }

    @Override
    public Product createProduct(Long Id) {
        return null;
    }

    @Override
    public void deleteProduct(Long Id) {

    }

    private Product convertFakeStoreProductDtotoProduct(FakeStoreProductDto  fakeStoreProductDto) {
        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setTitle(fakeStoreProductDto.getTitle());
        Category category = new Category();
        category.setTitle(fakeStoreProductDto.getCatergory());
        product.setCategory(category);
        return product;
    }
}
