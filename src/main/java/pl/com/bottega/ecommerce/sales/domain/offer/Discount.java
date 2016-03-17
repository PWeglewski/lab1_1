package pl.com.bottega.ecommerce.sales.domain.offer;

import java.math.BigDecimal;

/**
 * Created by piotr on 17.03.2016.
 */
public class Discount {
    private String discountCause;

    private BigDecimal discount;

    public Discount(String discountCause, BigDecimal discount) {
        this.discountCause = discountCause;
        this.discount = discount;
    }

    public String getDiscountCause() {
        return discountCause;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Discount)) return false;

        Discount discount1 = (Discount) o;

        if (getDiscountCause() != null ? !getDiscountCause().equals(discount1.getDiscountCause()) : discount1.getDiscountCause() != null)
            return false;
        return getDiscount() != null ? getDiscount().equals(discount1.getDiscount()) : discount1.getDiscount() == null;

    }

    @Override
    public int hashCode() {
        int result = getDiscountCause() != null ? getDiscountCause().hashCode() : 0;
        result = 31 * result + (getDiscount() != null ? getDiscount().hashCode() : 0);
        return result;
    }
}
