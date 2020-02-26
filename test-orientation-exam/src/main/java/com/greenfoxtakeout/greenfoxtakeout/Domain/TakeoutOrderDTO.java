package com.greenfoxtakeout.greenfoxtakeout.Domain;

public class TakeoutOrderDTO {

    private String name;
    private String base;
    private String topping;
    private String status;

    public TakeoutOrderDTO(TakeoutOrder order) {
        this.name = order.getName();
        this.base = order.getBase();
        this.topping = order.getTopping();
        this.status = order.getStatus();
    }

    public TakeoutOrderDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getTopping() {
        return topping;
    }

    public void setTopping(String topping) {
        this.topping = topping;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
