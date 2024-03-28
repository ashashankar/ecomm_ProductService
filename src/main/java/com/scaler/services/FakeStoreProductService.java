package com.scaler.services;

import com.scaler.dtos.FakeStoreProductDto;
import com.scaler.dtos.ProductDto;
import com.scaler.models.Category;
import com.scaler.models.Product;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
        List<Product> products = new ArrayList<>();
        ResponseEntity<FakeStoreProductDto[]> response = restTemplate.getForEntity("https://fakestoreapi.com/products", FakeStoreProductDto[].class);
        FakeStoreProductDto[] fakeStoreProductDtos = response.getBody();
        for(FakeStoreProductDto fakeStoreProductDto : fakeStoreProductDtos) {
            Product product = convertFakeStoreProductDtotoProduct(fakeStoreProductDto);
            products.add(product);
        }
        return products;
    }

    @Override
    public Product updateProduct(Long Id) {
        return null;
    }

    @Override
    public Product replaceProduct(Long Id, ProductDto productDTO) {
        //replace product with givenId with input product and return updated product.
        Product product = convertProductDtoToProduct(productDTO);
        RequestCallback requestCallback = restTemplate.httpEntityCallback(product, FakeStoreProductDto.class);
        HttpMessageConverterExtractor<FakeStoreProductDto> responseExtractor = new HttpMessageConverterExtractor(FakeStoreProductDto.class, restTemplate.getMessageConverters());
        FakeStoreProductDto fakeStoreProductDto =  restTemplate.execute("https://fakestoreapi.com/products/"+Id, HttpMethod.PUT, requestCallback, responseExtractor);
        return convertFakeStoreProductDtotoProduct(fakeStoreProductDto);
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
        category.setTitle(fakeStoreProductDto.getCategory());
        product.setCategory(category);
        return product;
    }

    private Product convertProductDtoToProduct(ProductDto productDto) {
        Product product = new Product();
        //product.setId(productDto.getId());
        product.setTitle(productDto.getTitle());
        Category category = new Category();
        category.setTitle(productDto.getCategory());
        product.setCategory(category);
        return product;
    }
}
