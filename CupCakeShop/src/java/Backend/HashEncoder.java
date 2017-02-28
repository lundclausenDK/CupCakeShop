/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bade
 */
public class HashEncoder
{

    public static String get_SHA_256_SecurePassword(String passwordToHash, byte[] salt)
    {
        String generatedPassword = null;
        try
        {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt);
            byte[] bytes = md.digest(passwordToHash.getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException ex)
        {
            Logger.getLogger(HashEncoder.class.getName()).log(Level.SEVERE, null, ex);
        }
        return generatedPassword;
    }

    public static byte[] generateSalt() throws NoSuchAlgorithmException
    {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt;
    }

    public static void insertSaltInDB(byte[] salt)
    {
        String sql = "insert into Salt (salt) values (?)";
        Connection con = new DBConnector().getConnection();

        try
        {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setBytes(1, salt);
            stmt.executeUpdate();
        } catch (SQLException ex)
        {
            Logger.getLogger(HashEncoder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static byte[] getUserSalt()
    {
        String sql = "select * from Salt where id = 1";
        Connection con = new DBConnector().getConnection();
        byte[] salt = null;

        try
        {
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next())
            {
                salt = rs.getBytes("salt");
            }
        } catch (SQLException ex)
        {
            Logger.getLogger(HashEncoder.class.getName()).log(Level.SEVERE, null, ex);
        }
        return salt;
    }
}
