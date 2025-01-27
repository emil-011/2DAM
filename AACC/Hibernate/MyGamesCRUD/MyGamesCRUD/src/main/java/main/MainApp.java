package main;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;

import dao.*;

import models.*;

public class MainApp {

    public static void main(String[] args) {
        // Abro sesión
        Session session = HibernateUtil.getSession();

        try {
            // Insertar un grupo de plataformas
            System.out.println("Insertar grupo de plataformas");
            GruposPlataformaDaoImpl grupoPlataformasDao = new GruposPlataformaDaoImpl(session);
            GrupoPlataformas grupoPC = new GrupoPlataformas();
            grupoPC.setNombre("PC");
            grupoPlataformasDao.insert(grupoPC);
            System.out.println("-------------------");

            // Insertar plataformas dentro del grupo
            System.out.println("Insertar plataformas");
            PlataformaDaoImpl plataformaDao = new PlataformaDaoImpl(session);
            Plataforma pc = new Plataforma();
            pc.setNombre("PC");
            pc.setGrupoPlataforma(grupoPC);
            plataformaDao.insert(pc);

            Plataforma switchConsole = new Plataforma();
            switchConsole.setNombre("Nintendo Switch");
            switchConsole.setGrupoPlataforma(grupoPC);
            plataformaDao.insert(switchConsole);
            System.out.println("-------------------");

            // Insertar un usuario
            System.out.println("Insertar usuario");
            UsuarioDaoImpl usuarioDao = new UsuarioDaoImpl(session);

            Usuario usuario1 = new Usuario();
            usuario1.setNombre("Ana García");
            usuario1.setEmail("ana@example.com");
            usuario1.setContrasena("mypassword123");
            usuario1.setFechaRegistro(new Date());
            usuarioDao.insert(usuario1);

            Usuario usuario2 = new Usuario();
            usuario2.setNombre("Carlos Pérez");
            usuario2.setEmail("carlos@example.com");
            usuario2.setContrasena("passwordCarlos567");
            usuario2.setFechaRegistro(new Date());
            usuarioDao.insert(usuario2);

            System.out.println("Usuarios insertados con éxito.");
            System.out.println("-------------------");

            // Insertar juegos
            System.out.println("Insertar juegos");

            JuegoDaoImpl juegoDao = new JuegoDaoImpl(session);
            DesarolladorDaoImpl devDao = new DesarolladorDaoImpl(session);
            GeneroDaoImpl genDao = new GeneroDaoImpl(session);

            // Crear desarrolladores
            Desarollador desarrollador1 = new Desarollador();
            desarrollador1.setNombre("Rockstar Games");
            devDao.insert(desarrollador1);

            Desarollador desarrollador2 = new Desarollador();
            desarrollador2.setNombre("Bethesda Game Studios");
            devDao.insert(desarrollador2);

            // Crear géneros
            Genero genRPG = new Genero();
            genRPG.setNombre("RPG");
            genDao.insert(genRPG);

            Genero genDeportes = new Genero();
            genDeportes.setNombre("Deportes");
            genDao.insert(genDeportes);

            // Insertar los juegos
            List<Genero> gens1 = new ArrayList<Genero>();
            gens1.add(genRPG);
            gens1.add(genDeportes);

            List<Genero> gens2 = new ArrayList<Genero>();
            gens2.add(genDeportes);

            Juego juego1 = new Juego();
            juego1.setTitulo("Red Dead Redemption 2");
            juego1.setDescripcion("Un juego de acción y aventura en un mundo abierto.");
            juego1.setPlataforma(pc);
            juego1.setCreadoPorUsuario(false);
            juego1.setDesarrollador(desarrollador1);
            juego1.setGeneros(gens1); // Añadir géneros
            juegoDao.insert(juego1);

            Juego juego2 = new Juego();
            juego2.setTitulo("FIFA 23");
            juego2.setDescripcion("Un simulador de fútbol con modo carrera y multiplayer.");
            juego2.setPlataforma(switchConsole);
            juego2.setCreadoPorUsuario(false);
            juego2.setDesarrollador(desarrollador2);
            juego2.setGeneros(gens2); // Añadir género
            juegoDao.insert(juego2);

            // Crear la wishlist para los usuarios
            System.out.println("Crear wishlist para los usuarios");
            WishlistDaoImpl wishlistDao = new WishlistDaoImpl(session);

            Wishlist wishlist1 = new Wishlist();
            wishlist1.setUsuario(usuario1);
            wishlist1.setJuegos(List.of(juego1)); // Añadir el juego a la wishlist
            wishlistDao.insert(wishlist1);

            Wishlist wishlist2 = new Wishlist();
            wishlist2.setUsuario(usuario2);
            wishlist2.setJuegos(List.of(juego2)); // Añadir el juego a la wishlist
            wishlistDao.insert(wishlist2);
            System.out.println("-------------------");

            // Mostrar todos los juegos de la wishlist de Ana
            System.out.println("Mostrar todos los juegos de la wishlist de Ana");
            List<Wishlist> wishlistDeAna = wishlistDao.searchByUsuario(usuario1.getIdUsuario());
            for (Wishlist w : wishlistDeAna) {
                System.out.println("Juego en wishlist: " + w.getJuegos().get(0).getTitulo());
            }
            System.out.println("-------------------");

            // Mostrar todos los juegos de la wishlist de Carlos
            System.out.println("Mostrar todos los juegos de la wishlist de Carlos");
            List<Wishlist> wishlistDeCarlos = wishlistDao.searchByUsuario(usuario2.getIdUsuario());
            for (Wishlist w : wishlistDeCarlos) {
                System.out.println("Juego en wishlist: " + w.getJuegos().get(0).getTitulo());
            }
            System.out.println("-------------------");

            // Crear la biblioteca para los usuarios
            System.out.println("Crear biblioteca para los usuarios");
            BibliotecaDaoImpl biblioDao = new BibliotecaDaoImpl(session);

            Biblioteca b1 = new Biblioteca();
            b1.setUsuario(usuario1);
            b1.setJuego(juego1);
            biblioDao.insert(b1);

            Biblioteca b2 = new Biblioteca();
            b2.setUsuario(usuario2);
            b2.setJuego(juego2);
            biblioDao.insert(b2);
            System.out.println("-------------------");

            // Mostrar todos los juegos de la biblioteca de Ana
            System.out.println("Mostrar todos los juegos de la biblioteca de Ana");
            List<Biblioteca> bibliotecaDeAna = biblioDao.searchByUsuario(usuario1.getIdUsuario());
            for (Biblioteca b : bibliotecaDeAna) {
                System.out.println("Juego en biblioteca: " + b.getJuego().getTitulo());
            }
            System.out.println("-------------------");

            // Mostrar todos los juegos de la biblioteca de Carlos
            System.out.println("Mostrar todos los juegos de la biblioteca de Carlos");
            List<Biblioteca> bibliotecaDeCarlos = biblioDao.searchByUsuario(usuario2.getIdUsuario());
            for (Biblioteca b : bibliotecaDeCarlos) {
                System.out.println("Juego en biblioteca: " + b.getJuego().getTitulo());
            }
            System.out.println("-------------------");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HibernateUtil.closeSession();
        }
    }
}
