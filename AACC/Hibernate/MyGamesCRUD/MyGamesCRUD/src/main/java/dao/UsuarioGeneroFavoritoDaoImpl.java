package dao;

import models.UsuarioGeneroFavorito;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class UsuarioGeneroFavoritoDaoImpl implements UsuarioGeneroFavoritoDaoInt {

    private Session session;

    public UsuarioGeneroFavoritoDaoImpl(Session session) {
        this.session = session;
    }

    @Override
    public void insert(UsuarioGeneroFavorito usuarioGeneroFavorito) {
        Transaction transaction = session.beginTransaction();
        session.merge(usuarioGeneroFavorito);
        transaction.commit();
    }
}
