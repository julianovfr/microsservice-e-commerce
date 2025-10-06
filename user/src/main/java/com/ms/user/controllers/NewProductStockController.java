package com.ms.user.controllers;

import com.ms.user.dtos.CreateProductDto;
import com.ms.user.models.UserModel;
import com.ms.user.repositories.UserRepository;
import com.ms.user.services.AddProductService;
import com.ms.user.services.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.transform.impl.AddPropertyTransformer;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NewProductStockController {

    @Autowired
    AddProductService addProductService;

    @Autowired
    TokenService tokenService;

    @Autowired
    UserRepository userRepository;



    @PostMapping("/add-product")
    public ResponseEntity addProduct(@RequestBody CreateProductDto createProductDto,
                                     HttpServletRequest tokenJwt){

        String token = this.recoverToken(tokenJwt);

        String email = tokenService.validateToken(token);
        UserModel userModel = new UserModel();
        userModel = (UserModel) userRepository.findByEmail(email);
        String status = userModel.getRole();

        if("ADMIN".equals(status)){
            return addProductService.pedidoAddProduct(createProductDto);

        }

        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body("Usuario nao autorizado a realizcao operacao");
    }

    private String recoverToken(HttpServletRequest request){
        var authHeader = request.getHeader("Authorization");
        if(authHeader == null) return null;
        return authHeader.replace("Bearer ", "");
    }

}
