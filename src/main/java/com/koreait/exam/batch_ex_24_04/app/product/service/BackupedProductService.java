package com.koreait.exam.batch_ex_24_04.app.product.service;

import com.koreait.exam.batch_ex_24_04.app.product.entity.BackupedProduct;
import com.koreait.exam.batch_ex_24_04.app.product.entity.BackupedProductOption;
import com.koreait.exam.batch_ex_24_04.app.product.entity.Product;
import com.koreait.exam.batch_ex_24_04.app.product.entity.ProductOption;
import com.koreait.exam.batch_ex_24_04.app.product.repository.BackupedProductRepository;
import com.koreait.exam.batch_ex_24_04.app.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BackupedProductService {

    private final BackupedProductRepository productRepository;

    @Transactional
    public List<BackupedProduct> backupAll(List<Product> products) {

        List<BackupedProduct> backupedProducts = new ArrayList<>();

        for(Product product : products) {
            BackupedProduct backupedProduct = BackupedProduct.builder()
                .name(product.getName())
                .salePrice(product.getSalePrice()) // 판매가
                .price(product.getPrice()) // 권장 소비자가
                .wholesalePrice(product.getWholesalePrice())
                .makerShopName(product.getMakerShopName()).build();

            for (ProductOption option : product.getProductOptions()) {
                BackupedProductOption backupedOption = new BackupedProductOption(option);

                backupedProduct.addOption(backupedOption);
            }
            backupedProducts.add(backupedProduct);

        }
        productRepository.saveAll(backupedProducts);

        return backupedProducts;
    }
}
