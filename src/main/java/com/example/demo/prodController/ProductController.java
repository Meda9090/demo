package com.example.demo.prodController;

import com.example.demo.exception.ProductNotFoundException;
import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
@RequestMapping("/app/product")
public class ProductController {
    // Response Entity - Ответная сущность
    // Data - Данные
    // find - Hаходить
    // String - Просто пред (Команда), как response
    // response - Просто пред (Команда), как String
    // try { - код, который может вызвать исключение
    // } catch - обработка исключения типа ...
    // INTERNAL_SERVER_ERROR - ВНУТРЕННЯЯ ОШИБКА СЕРВЕРА
//_________________________________________________________________________________________
    // ResponseEntity - является оберткой для ответа и дополнительно для HTTP заголовков  |
    // и кода статуса. Метод add отпрвляет обратно ResponseEntity                         |
    // с кодом статуса 201( CREATED ) и заголовком( Location ), чтобы клиент              |
    // мог воспользоваться ими и изучить, как была создана новая запись.                  |
//_________________________________________________________________________________________

    private static  final Logger LOG = LoggerFactory.getLogger(ProductController.class);
    @Autowired
    private ProductService service;   //package --> service.ProductService
//                                          POSTMAN
//__________________________________saveProduct_____________сохранитьПродукт_____________________

//    http://localhost:9595/product/save 	POST  ________________________________________
    @PostMapping("/save")  //сохранять
    public ResponseEntity<String> saveProduct(@RequestBody Product product) { //package --> model.Product

        LOG.info("ENTERD INFO SAVE METHOD"); //ВВЕДИТЕ ИНФОРМАЦИЮ СОХРАНИТЕ МЕТОД
        ResponseEntity<String>response=null;

        try {
            Integer id = service.saveProduct(product); //package --> service.ProductService (package --> model.Product)
            String data = "Product Data Save " + id; //Сохранение данных о продукте
            response = new ResponseEntity<String>(data, HttpStatus.CREATED); //200
            LOG.debug(data);
        }catch (Exception e) { //Исключение
            LOG.error("SAVING IS FAILD {}", e.getMessage()); //СОХРАНЕНИЕ НЕ ПРОШЛО

            e.printStackTrace();

            response = new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); //500
        }
        LOG.info("ABOUT TO LEAVE SAVING METHOD"); //СОХРАНИМ МЕТОД СОХРАНЕНИЯ
        return response;
    }


//________________________getAllProduct______________получитьВсеПродукты_______________________________________

//    http://localhost:9595/product/all         GET __________________________________________
    @GetMapping("/all")  //все
    public ResponseEntity<List<Product>> getAllProduct(){
        List<Product> list = service.getAllProduct(); //package --> service.ProductService

        ResponseEntity<List<Product>> response= new ResponseEntity<List<Product>>(list, HttpStatus.OK); //201
        return response;
    }


//__________________________getOneProduct__________________получитьОдинПродукт_____________________________________

//    http://localhost:9595/product/find/1	     GET ____________________________________________
    @GetMapping("/find/{id}")  //находить
    public ResponseEntity<?> getOneProduct(@PathVariable Integer id) {

        LOG.info("ENTERD INFO GATTING ONE RECORD METHOD"); //ВВЕДИТЕ ИНФОРМАЦИЮ. МЕТОД ПОЛУЧЕНИЯ ОДНОЙ ЗАПИСИ.
        ResponseEntity<?>response = null;

        try {
            Product prd = service.getOneProduct(id);
            response = new ResponseEntity<Product>(prd, HttpStatus.OK);
        } catch (ProductNotFoundException pe) {
            LOG.error("ONE RECORD FAILD {}", pe.getMessage()); //ОШИБКА ОДНОЙ ЗАПИСИ

            pe.printStackTrace();

            response = new ResponseEntity<String>(pe.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); //500
        }
        LOG.info("ABOUT TO LEAVE ONE RECOR METHOD"); //ОСТАВИМ ОДИН МЕТОД ЗАПИСИ

        return response;
    }


//____________________________________deleteProduct____________удалитьПродукт________________________________________

//    http://localhost:9595/product/remove/3    DELETE __________________________________________
    @DeleteMapping("/remove/{id}")  //удалять
    public ResponseEntity<String> deleteOneProduct(@PathVariable Integer id) {
        ResponseEntity<String> response = null;

            try {
                service.deleteProduct(id);
                response = new ResponseEntity<String>("Product remove " + id, HttpStatus.OK); //Удаление продукта
        }catch (ProductNotFoundException pe) {

                pe.printStackTrace();

                    response = new ResponseEntity<String>(pe.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        return response;
    }


//____________________________________updateProduct__________обновитьПродукт___________________________________________

//  http://localhost:9595/product/modify    PUT _________________________________________________
    @PutMapping("modify") //изменить
    public ResponseEntity<String>updateProduct(@RequestBody Product prd) {
        ResponseEntity<String> response = null;

        try {
            service.updateProduct(prd);
            response = new ResponseEntity<String>("Product " + prd.getProdId() + " Updated", HttpStatus.OK); // Продукт ProdId   Обновлено
        }catch (ProductNotFoundException pe) {

            pe.printStackTrace();

            response = new ResponseEntity<String>("Product Not Updated", HttpStatus.INTERNAL_SERVER_ERROR); //Продукт не обновлен
        }
        return response;
    }



//__________________________updateProductCodeById___________обновить Код Продукта ById_________________________________________________

//http://localhost:9595/product/update/1/LLGG       PATCH__________________________________________________________
    @PatchMapping("/update/{id}/{code}")
    public ResponseEntity<String> updateProductCode(@PathVariable Integer id,
                                                    @PathVariable String code) {
        ResponseEntity<String> response = null;

        try {
            service.updateProductCodeById(code, id);
            response = new ResponseEntity<String>("Product " + id + " Updated with code " + code, HttpStatus.OK); // Продукт id Обновлено с кодом
        }catch (ProductNotFoundException pe) {

            response = new ResponseEntity<String>(pe.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

//__________________________________________________________________________________________

}
