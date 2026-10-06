package com.salesdashboard.service;

import com.salesdashboard.dao.SaleDAO;
import com.salesdashboard.model.Sale;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public class SalesService {

    private final SaleDAO dao = new SaleDAO();

    public void initialize() throws SQLException {
        dao.createTable();
    }

    public boolean addSale(Sale sale) throws SQLException {

        validate(sale);

        return dao.addSale(sale);
    }

    public List<Sale> getAllSales() throws SQLException {

        return dao.getAllSales();
    }

    public Sale getSale(int id) throws SQLException {

        return dao.findById(id);
    }

    public boolean deleteSale(int id) throws SQLException {

        if (id <= 0) {
            throw new IllegalArgumentException(
                    "Invalid sale ID"
            );
        }

        return dao.deleteSale(id);
    }

    private void validate(Sale sale) {

        if (sale.getSaleId() <= 0) {
            throw new IllegalArgumentException(
                    "Sale ID must be positive"
            );
        }

        if (sale.getQuantity() <= 0) {
            throw new IllegalArgumentException(
                    "Quantity must be greater than zero"
            );
        }

        if (sale.getUnitPrice()
                .compareTo(BigDecimal.ZERO) < 0) {

            throw new IllegalArgumentException(
                    "Price cannot be negative"
            );
        }

        if (sale.getUnitCost()
                .compareTo(BigDecimal.ZERO) < 0) {

            throw new IllegalArgumentException(
                    "Cost cannot be negative"
            );
        }
    }
}