/*
 * Copyright 2011-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package pl.com.bottega.ecommerce.sales.domain.offer;

import java.math.BigDecimal;

public class OfferItem {
    private Product product;

    private int quantity;

    private BigDecimal totalCost;

    private String currency;

    private boolean isAvailable;

    private Discount discount;

    public OfferItem(Product product, int quantity,
                     Discount discount, String currency) {

        this.quantity = quantity;
        this.product = product;
        this.currency = currency;
        this.discount = discount;

        BigDecimal discountValue = new BigDecimal(0);
        if (discount != null)
            discountValue = discountValue.subtract(discount.getDiscount());

        this.totalCost = product.getPrice()
                .multiply(new BigDecimal(quantity)).subtract(discountValue);
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public String getTotalCostCurrency() {
        return currency;
    }

    public int getQuantity() {
        return quantity;
    }

    public Discount getDiscount() {
        return discount;
    }

    public String getCurrency() {
        return currency;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public Product getProduct() {
        return product;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (getProduct() != null ? getProduct().hashCode() : 0);
        result = prime * result + (getDiscount() != null ? getDiscount().hashCode() : 0);
        result = prime * result + quantity;
        result = prime * result
                + ((totalCost == null) ? 0 : totalCost.hashCode());
        result = 31 * result + (isAvailable() ? 1 : 0);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        OfferItem other = (OfferItem) obj;
        if (isAvailable() != other.isAvailable()) return false;
        if (quantity != other.quantity)
            return false;
        if (totalCost == null) {
            if (other.totalCost != null)
                return false;
        } else if (!totalCost.equals(other.totalCost))
            return false;
        if (product != null) {
            if (!product.equals(other.getProduct())) {
                return false;
            }
            ;
        } else if (other.getProduct() != null) {
            return false;
        }
        if (discount != null) {
            if (!discount.equals(other.getDiscount())) {
                return false;
            }
            ;
        } else if (other.getDiscount() != null) {
            return false;
        }
        return true;
    }

    /**
     * @param other
     * @param delta acceptable percentage difference
     * @return
     */
    public boolean sameAs(OfferItem other, double delta) {
        if (quantity != other.quantity)
            return false;

        BigDecimal max, min;
        if (totalCost.compareTo(other.totalCost) > 0) {
            max = totalCost;
            min = other.totalCost;
        } else {
            max = other.totalCost;
            min = totalCost;
        }

        BigDecimal difference = max.subtract(min);
        BigDecimal acceptableDelta = max.multiply(new BigDecimal(delta / 100));

        return acceptableDelta.compareTo(difference) > 0;
    }

}
