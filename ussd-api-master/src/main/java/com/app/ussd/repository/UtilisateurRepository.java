package com.app.ussd.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.app.ussd.model.Roles;
import com.app.ussd.model.Utilisateur;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {

   public Optional<Utilisateur> findByUsername (String email);
   
   public List<Utilisateur> findByAccountId(long accountId);
   
   @Query(nativeQuery = true, value ="select role_name  from roles r  join utilisateur_role j ON r.id = j.role_id Join utilisateur u ON j.user_id = u.id where u.id = :id")
   public List<Roles>getUserRoles(@Param("id") Long id);
  
}