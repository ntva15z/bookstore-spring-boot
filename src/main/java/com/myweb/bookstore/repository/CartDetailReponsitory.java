package com.myweb.bookstore.repository;

import com.myweb.bookstore.entity.CartDetail;
import com.myweb.bookstore.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartDetailReponsitory extends JpaRepository<CartDetail,Long> {

    @Query("select d from CartDetail d,Cart c where c.customer.id=:id and c.id=d.cart.id")
    List<CartDetail> findByCustomer(Long id);

    @Query("delete from CartDetail cd  where cd.cart.customer.id=:id ")
    void deleteCartDetailByCustomerId(Long id);
}
