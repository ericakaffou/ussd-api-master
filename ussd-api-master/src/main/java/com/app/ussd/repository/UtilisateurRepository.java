package com.app.ussd.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.app.ussd.model.Utilisateur;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {

   //LEFT JOIN FETCH u.roles charge les rôles dans la même requête SQL que l'utilisateur.
   @Query(" SELECT DISTINCT u FROM Utilisateur u LEFT JOIN FETCH u.roles  WHERE u.username = :email")
   public Optional<Utilisateur> findByUsername (String email);
   
   
   public List<Utilisateur> findByAccountId(long accountId);
   

   //@Query(nativeQuery = true, value ="select role_name  from roles r  join utilisateur_role j ON r.id = j.role_id Join utilisateur u ON j.user_id = u.id where u.id = :id")
   @Query( " SELECT r.roleName FROM Utilisateur u JOIN u.roles r WHERE u.id = :id")
   public List<String>findRoleNamesById(@Param("id") Long id);
  
}