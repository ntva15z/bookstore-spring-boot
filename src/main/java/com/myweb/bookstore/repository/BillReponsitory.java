package com.myweb.bookstore.repository;

import com.myweb.bookstore.entity.Bill;
import com.myweb.bookstore.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillReponsitory extends JpaRepository<Bill,Long> {

    @Query("select b.activedate,sum(b.total) from Bill b where b.status=2 group by b.activedate")
    List<Object[]> totalByDate();


}
