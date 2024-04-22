package com.koreait.exam.batch_ex_24_04.app.product.repository;

import com.koreait.exam.batch_ex_24_04.app.product.entity.BackupedProduct;
import com.koreait.exam.batch_ex_24_04.app.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BackupedProductRepository extends JpaRepository<BackupedProduct,Long> {
}
