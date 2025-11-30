package edu.jdbcproject.dao.impl;

import edu.jdbcproject.config.Helper;
import edu.jdbcproject.dao.ProductDao;
import edu.jdbcproject.exception.ProductNotFoundException;
import edu.jdbcproject.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {

    @Override
    public void addProduct(Product product) {
        String sql = "insert into products values(?,?,?,?)";
        try (Connection con = Helper.makeCon()) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, product.getId());
            ps.setString(2, product.getName());
            ps.setDouble(3, product.getPrice());
            ps.setInt(4, product.getQuantity());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("SQL ERROR: " + e.getMessage());
        }
    }
    @Override
    public Product getProduct(int id) {
        String sql = "select * from products where id = ?";
        Product product = null;

        try (Connection con = Helper.makeCon()) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                product = new Product();
                product.setId(rs.getInt(1));
                product.setName(rs.getString(2));
                product.setPrice(rs.getDouble(3));
                product.setQuantity(rs.getInt(4));
            } else {
                throw new ProductNotFoundException("Product not found with id " + id);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return product;
    }

    @Override
    public List<Product> getAllProducts() {
        String sql = "select * from products";
        List<Product> products = new ArrayList<>();

        try (Connection con = Helper.makeCon()) {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getInt(1));
                p.setName(rs.getString(2));
                p.setPrice(rs.getDouble(3));
                p.setQuantity(rs.getInt(4));
                products.add(p);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return products;
    }


    @Override
    public void updateProduct(Product product) {
        String sql = "update products set name = ?, price = ?, quantity = ? where id = ?";

        try (Connection con = Helper.makeCon()) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, product.getName());
            ps.setDouble(2, product.getPrice());
            ps.setInt(3, product.getQuantity());
            ps.setInt(4, product.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }


    @Override
    public void deleteProduct(int id) {
        String sql = "delete from products where id = ?";

        try (Connection con = Helper.makeCon()) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }


}
