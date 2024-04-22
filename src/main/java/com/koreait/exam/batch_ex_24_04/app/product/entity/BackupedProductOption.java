package com.koreait.exam.batch_ex_24_04.app.product.entity;

import com.koreait.exam.batch_ex_24_04.app.base.entity.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
public class BackupedProductOption extends BaseEntity {
    private String color;
    private String size;
    private String displayColor;
    private String displaySize;
    private int price; // 권장 판매가 53000
    private int salePrice; // 실제 판매가 39800
    private int wholesalePrice; // 도매가 < 53000
    private int payPrice; // 결제금액
    private int refundPrice; // 환불금액
    private int pgFee; // 결제대행사 수수료
    private int refundQuantity; // 환불 한 갯수
    private boolean isPaid;  // 결제 여부

    @ManyToOne(fetch = LAZY)
    @ToString.Exclude
    private BackupedProduct product;

    private boolean isSoldOut; // 사입처에서의 품절여부
    private int stockQuantity; // 쇼핑몰에서 보유한 물건 갯수

    public BackupedProductOption(String color, String size) {
        this.color = color; // 네이비
        this.displayColor = color; // 곤색
        this.size = size;
        this.displaySize = size;
    }

    public BackupedProductOption(ProductOption productOption) {
        this.color = productOption.getColor(); // 네이비
        this.displayColor = productOption.getDisplayColor(); // 곤색
        this.size = productOption.getSize();
        this.displaySize = productOption.getDisplaySize();
        this.price = productOption.getPrice(); // 권장 판매가 53000
        this.salePrice = productOption.getSalePrice(); // 실제 판매가 39800
        this.wholesalePrice = productOption.getWholesalePrice(); // 도매가 < 53000
        this.payPrice = productOption.getPayPrice(); // 결제금액
        this.refundPrice = productOption.getRefundPrice(); // 환불금액
        this.pgFee = productOption.getPgFee(); // 결제대행사 수수료
        this.refundQuantity = productOption.getRefundQuantity(); // 환불 한 갯수
        this.isPaid = productOption.isPaid();  // 결제 여부
    }

    public boolean isOrderable(int quantity) {
        if(isSoldOut() == false) return true;

        return getStockQuantity() >= quantity;
    }
}