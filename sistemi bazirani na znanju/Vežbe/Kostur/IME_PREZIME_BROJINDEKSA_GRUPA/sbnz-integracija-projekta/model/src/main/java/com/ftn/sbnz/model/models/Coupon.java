package com.ftn.sbnz.model.models;

public class Coupon {
    
    public Long id;
    public double discount;
   
    public double getDiscount() {
        return discount;
    }
    public void setDiscount(double discount) {
        this.discount = discount;
    }
    public Coupon() {
    }
    public Coupon(Long id, double discount) {
        this.id = id;
        this.discount = discount;
    }
   
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        long temp;
        temp = Double.doubleToLongBits(discount);
        result = prime * result + (int) (temp ^ (temp >>> 32));
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
        Coupon other = (Coupon) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (Double.doubleToLongBits(discount) != Double.doubleToLongBits(other.discount))
            return false;
        return true;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    
    
}
