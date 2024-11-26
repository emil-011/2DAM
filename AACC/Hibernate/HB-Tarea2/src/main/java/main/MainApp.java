package main;

import org.hibernate.query.Query;

import utils.HibernateUtil;

import java.util.List;

public class MainApp {

    public static void main(String[] args) {
        
        String hql = "SELECT c.categoria FROM Categorias c";  // Selecciona solo la propiedad 'categoria'
        Query<String> query = HibernateUtil.getSession().createQuery(hql, String.class);

        // Ejecuta la consulta y obtén los resultados
        List<String> categorias = query.list();

        // Imprime los resultados
        for (String categoria : categorias) {
            System.out.println(categoria);
        }

        // Cierra la sesión
        HibernateUtil.closeSession();
    }
}
