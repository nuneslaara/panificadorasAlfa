
package br.com.panificadora.factory;

import java.sql.Connection;
import java.sql.SQLException;

public class teste {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Connection connection = new ConnectionFactory().getConnection();
        System.out.println("Conexão aberta!");
        connection.close();
    }
}
