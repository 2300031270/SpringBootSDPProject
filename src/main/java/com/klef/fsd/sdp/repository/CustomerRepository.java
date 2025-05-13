package com.klef.fsd.sdp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.klef.fsd.sdp.model.Customer;
import java.util.List;


@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer>
{
  public Customer findByUsernameAndPassword(String username, String password);
  
  @Query("select c from Customer c where c.gender=?1")
  public List<Customer> displaycustomersbygender(String gender);
  
  @Modifying
  @Transactional
  @Query("delete from Customer c where c.location=?1")
  public Long deletecustomerbylocation(String location);
  
}
