package com.example.demo.serviceImple;

import com.example.demo.exception.ProductNotFoundException;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductService;
import com.example.demo.util.CalculationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service

public class ProductServiceImple implements ProductService {

    @Autowired
    private ProductRepository repository;
//______________________________________________________________________________________
    @Override
    public Integer saveProduct(Product p) {
        CalculationUtil.bzlogic(p);  //package --> util.CalculationUtil
        repository.save(p);
        return p.getProdId();
    }
//______________________________________________________________________________________

    @Override
    public void updateProduct(Product p) {
        CalculationUtil.bzlogic(p);  //package --> util.CalculationUtil
        repository.save(p);
    }
//______________________________________________________________________________________

    @Override
    public void deleteProduct(Integer id) {
        repository.delete(getOneProduct(id));

    }
//______________________________________________________________________________________

    @Override
    public Product getOneProduct(Integer id) { //Если нам что нибудь понадоваеться
        Optional<Product> optional = repository.findById(id); //Можем найти один запись
        if(optional.isPresent()) { //если (элемент.настоящее())
            return optional.get(); //возвращать элемент получать()
        }else {                     //}еще{
            throw new ProductNotFoundException("Product " + id + " Not Exist");
              //Исключение «Продукт не найден»("Product " + id + " Не существует");
        }
    }
//______________________________________________________________________________________

    @Override
    public List<Product> getAllProduct() {
        return repository.findAll();
    }
//______________________________________________________________________________________

    @Override
    @Transactional
    public void updateProductCodeById(String code, Integer id) {
        if (!repository.existsById(id)) { // не существует
            throw new ProductNotFoundException("Product " + id + " Not Exist");
//То выдаем (new) Исключение «Продукт не найден» (Product + id + не существует
        }
        repository.updateProductCodeById(code, id);
    }
//______________________________________________________________________________________

}
