/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tungtn.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import tungtn.model.Account;
import tungtn.model.Category;
import tungtn.model.Product;
import tungtn.util.DBUtils;

/**
 *
 * @author ASUS
 */
public class DAO {

    Connection conn = null; // Kết nối sql server
    PreparedStatement pstm = null; // Ném câu lệnh query sang sql server
    ResultSet rs = null; // Nhận kết quả trả về

    public List<Product> getAllProduct() {
        List<Product> list = new ArrayList<>(); //Khởi tạo 1 cái list để load sản phẩm lên thì lưu vào trong cái list này
        String query = "SELECT * FROM PRODUCT";
        try {
            conn = new DBUtils().getConnection(); //Mở kết nối với sql server
            pstm = conn.prepareStatement(query);
            rs = pstm.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<Category> getAllCategory() {
        List<Category> list = new ArrayList<>(); //Khởi tạo 1 cái list để load sản phẩm lên thì lưu vào trong cái list này
        String query = "SELECT * FROM Category";
        try {
            conn = new DBUtils().getConnection(); //Mở kết nối với sql server
            pstm = conn.prepareStatement(query);
            rs = pstm.executeQuery();
            while (rs.next()) {
                list.add(new Category(rs.getInt(1),
                        rs.getString(2)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public Product getLast() {
        String query = "SELECT TOP 1 * FROM product "
                + " ORDER BY id DESC ";
        try {
            conn = new DBUtils().getConnection(); //Mở kết nối với sql server
            pstm = conn.prepareStatement(query);
            rs = pstm.executeQuery();
            while (rs.next()) {
                return new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6));
            }
        } catch (Exception e) {
        }
        return null;
    }

    public List<Product> getProductByCID(String cID) {
        List<Product> list = new ArrayList<>(); //Khởi tạo 1 cái list để load sản phẩm lên thì lưu vào trong cái list này
        String query = "SELECT * FROM product WHERE cateID = ?";
        try {
            conn = new DBUtils().getConnection(); //Mở kết nối với sql server
            pstm = conn.prepareStatement(query);
            pstm.setString(1, cID);
            rs = pstm.executeQuery();
            while (rs.next()) { //Lấy kết quả từ bảng Resultset đẩy vào trong 1 cái Object(Product) sau đó đẩy Object vào trong list của mình
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public Product getProductByID(String id) {
        String query = "SELECT * FROM product WHERE id = ?";
        try {
            conn = new DBUtils().getConnection(); //Mở kết nối với sql server
            pstm = conn.prepareStatement(query);
            pstm.setString(1, id);
            rs = pstm.executeQuery();
            while (rs.next()) { //Lấy kết quả từ bảng Resultset đẩy vào trong 1 cái Object(Product) sau đó đẩy Object vào trong list của mình
                return new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6));
            }
        } catch (Exception e) {
        }
        return null;
    }

    public List<Product> searchByName(String txtSearch) {
        List<Product> list = new ArrayList<>(); //Khởi tạo 1 cái list để load sản phẩm lên thì lưu vào trong cái list này
        String query = "SELECT * FROM product WHERE [name] LIKE ?";
        try {
            conn = new DBUtils().getConnection(); //Mở kết nối với sql server
            pstm = conn.prepareStatement(query);
            pstm.setString(1, "%" + txtSearch + "%");
            rs = pstm.executeQuery();
            while (rs.next()) { //Lấy kết quả từ bảng Resultset đẩy vào trong 1 cái Object(Product) sau đó đẩy Object vào trong list của mình
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public Account login(String user, String pass) {
        String query = "SELECT * FROM account WHERE [userName]=? AND pass=?";
        try {
            conn = new DBUtils().getConnection(); //Mở kết nối với sql server
            pstm = conn.prepareStatement(query);
            pstm.setString(1, user);
            pstm.setString(2, pass);
            rs = pstm.executeQuery();
            while (rs.next()) {
                return new Account(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5));
            }
        } catch (Exception e) {
        }

        return null;
    }

    public Account checkAccountExist(String user) {
        String query = "SELECT * FROM account WHERE [user]=?";
        try {
            conn = new DBUtils().getConnection(); //Mở kết nối với sql server
            pstm = conn.prepareStatement(query);
            pstm.setString(1, user);
            rs = pstm.executeQuery();
            while (rs.next()) {
                return new Account(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5));
            }
        } catch (Exception e) {
        }

        return null;
    }

    public List<Product> getProductBySellID(int id) {
        List<Product> list = new ArrayList<>(); //Khởi tạo 1 cái list để load sản phẩm lên thì lưu vào trong cái list này
        String query = "SELECT * FROM product WHERE [sell_ID] = ?";
        try {
            conn = new DBUtils().getConnection(); //Mở kết nối với sql server
            pstm = conn.prepareStatement(query);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();
            while (rs.next()) { //Lấy kết quả từ bảng Resultset đẩy vào trong 1 cái Object(Product) sau đó đẩy Object vào trong list của mình
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public void deleteProduct(String pid) {

        String query = "DELETE FROM product WHERE id=?";
        try {
            conn = new DBUtils().getConnection(); //Mở kết nối với sql server
            pstm = conn.prepareStatement(query);
            pstm.setString(1, pid);
            pstm.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void insertProduct(String name, String image, String price,
            String title, String description, String category, int sid) {

        String query = "INSERT [dbo].[product] ([name], [image], [price],\n"
                + "[title], [description], [cateID], [sell_ID])\n"
                + "VALUES(?,?,?,?,?,?,?)";
        try {
            conn = new DBUtils().getConnection(); //Mở kết nối với sql server
            pstm = conn.prepareStatement(query);
            pstm.setString(1, name);
            pstm.setString(2, image);
            pstm.setString(3, price);
            pstm.setString(4, title);
            pstm.setString(5, description);
            pstm.setString(6, category);
            pstm.setInt(7, sid);
            pstm.executeUpdate();
        } catch (Exception e) {
        }

    }

    public void editProduct(String name, String image, String price,
            String title, String description, String category, String pid) {

        String query = "UPDATE product\n"
                + "SET [name] = ?,\n"
                + "[image] = ?,\n"
                + "[price] = ?,\n"
                + "[title] = ?,\n"
                + "[description] = ?,\n"
                + "[cateID] = ?\n"
                + "WHERE id = ?";
        try {
            conn = new DBUtils().getConnection(); //Mở kết nối với sql server
            pstm = conn.prepareStatement(query);
            pstm.setString(1, name);
            pstm.setString(2, image);
            pstm.setString(3, price);
            pstm.setString(4, title);
            pstm.setString(5, description);
            pstm.setString(6, category);
            pstm.setString(7, pid);
            pstm.executeUpdate();
        } catch (Exception e) {
        }

    }

    public void signup(String user, String pass) {
        String query = "INSERT INTO account VALUES(?,?,0,0)";
        try {
            conn = new DBUtils().getConnection(); //Mở kết nối với sql server
            pstm = conn.prepareStatement(query);
            pstm.setString(1, user);
            pstm.setString(2, pass);
            pstm.executeUpdate();
        } catch (Exception e) {
        }
    }
}
