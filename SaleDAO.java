package com.salesdashboard.dao;

import com.salesdashboard.model.Sale;
import com.salesdashboard.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SaleDAO {

    public void createTable() throws SQLException {

        String sql = """
                CREATE TABLE IF NOT EXISTS sales (
                    sale_id INT PRIMARY KEY,
                    sale_date DATE NOT NULL,
                    product VARCHAR(100) NOT NULL,
                    category VARCHAR(50) NOT NULL,
                    region VARCHAR(50) NOT NULL,
                    quantity INT NOT NULL,
                    unit_price DECIMAL(12,2) NOT NULL,
                    unit_cost DECIMAL(12,2) NOT NULL,
                    customer_type VARCHAR(30) NOT NULL,
                    payment_method VARCHAR(30) NOT NULL
                )
                """;

        try (
                Connection con = DBConnection.getConnection();
                Statement stmt = con.createStatement()
        ) {
            stmt.executeUpdate(sql);
        }
    }

    public boolean addSale(Sale sale) throws SQLException {

        String sql = """
                INSERT INTO sales
                (sale_id, sale_date, product, category, region,
                 quantity, unit_price, unit_cost,
                 customer_type, payment_method)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                """;

        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setInt(1, sale.getSaleId());
            ps.setDate(2, Date.valueOf(sale.getSaleDate()));
            ps.setString(3, sale.getProduct());
            ps.setString(4, sale.getCategory());
            ps.setString(5, sale.getRegion());
            ps.setInt(6, sale.getQuantity());
            ps.setBigDecimal(7, sale.getUnitPrice());
            ps.setBigDecimal(8, sale.getUnitCost());
            ps.setString(9, sale.getCustomerType());
            ps.setString(10, sale.getPaymentMethod());

            return ps.executeUpdate() > 0;
        }
    }

    public List<Sale> getAllSales() throws SQLException {

        List<Sale> sales = new ArrayList<>();

        String sql =
                "SELECT * FROM sales ORDER BY sale_date, sale_id";

        try (
                Connection con = DBConnection.getConnection();
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql)
        ) {

            while (rs.next()) {

                Sale sale = new Sale();

                sale.setSaleId(rs.getInt("sale_id"));
                sale.setSaleDate(
                        rs.getDate("sale_date").toLocalDate()
                );
                sale.setProduct(rs.getString("product"));
                sale.setCategory(rs.getString("category"));
                sale.setRegion(rs.getString("region"));
                sale.setQuantity(rs.getInt("quantity"));
                sale.setUnitPrice(
                        rs.getBigDecimal("unit_price")
                );
                sale.setUnitCost(
                        rs.getBigDecimal("unit_cost")
                );
                sale.setCustomerType(
                        rs.getString("customer_type")
                );
                sale.setPaymentMethod(
                        rs.getString("payment_method")
                );

                sales.add(sale);
            }
        }

        return sales;
    }

    public Sale findById(int id) throws SQLException {

        String sql =
                "SELECT * FROM sales WHERE sale_id = ?";

        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    Sale sale = new Sale();

                    sale.setSaleId(rs.getInt("sale_id"));
                    sale.setSaleDate(
                            rs.getDate("sale_date").toLocalDate()
                    );
                    sale.setProduct(rs.getString("product"));
                    sale.setCategory(rs.getString("category"));
                    sale.setRegion(rs.getString("region"));
                    sale.setQuantity(rs.getInt("quantity"));
                    sale.setUnitPrice(
                            rs.getBigDecimal("unit_price")
                    );
                    sale.setUnitCost(
                            rs.getBigDecimal("unit_cost")
                    );
                    sale.setCustomerType(
                            rs.getString("customer_type")
                    );
                    sale.setPaymentMethod(
                            rs.getString("payment_method")
                    );

                    return sale;
                }
            }
        }

        return null;
    }

    public boolean deleteSale(int id) throws SQLException {

        String sql =
                "DELETE FROM sales WHERE sale_id = ?";

        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;
        }
    }
}