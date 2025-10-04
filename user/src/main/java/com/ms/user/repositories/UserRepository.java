package com.ms.user.repositories;

import com.ms.user.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.UUID;

//Jpa repository eh uma interface que encapsula os scripts sql fazendo a conexao com o banco sem precisar escrever
//codigo SQL
public interface UserRepository extends JpaRepository<UserModel, UUID> {

    //por padrao essa busca nao existe no
    UserDetails findByEmail(String email);

    boolean existsByEmail(String email);
}
