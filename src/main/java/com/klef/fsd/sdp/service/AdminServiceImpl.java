package com.klef.fsd.sdp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klef.fsd.sdp.model.Admin;
import com.klef.fsd.sdp.model.Customer;
import com.klef.fsd.sdp.repository.AdminRepository;
import com.klef.fsd.sdp.repository.CustomerRepository;

@Service
public class AdminServiceImpl implements AdminService
{
  @Autowired
  private AdminRepository adminRepository;

  @Autowired
  private CustomerRepository customerRepository;

  @Override
  public Admin checkadminlogin(String username, String password) 
  {
    return adminRepository.findByUsernameAndPassword(username, password);
  }

  @Override
  public List<Customer> displaycustomers() 
  {
    return customerRepository.findAll();
  }

  @Override
  public String deletecustomer(int cid) 
  {
    Optional<Customer> customer = customerRepository.findById(cid);

    if (customer.isPresent()) 
    {	
      customerRepository.deleteById(cid);
      return "Customer Deleted Successfully";
    } 
    else 
    {
      return "Customer ID Not Found";
    }
  }
}
