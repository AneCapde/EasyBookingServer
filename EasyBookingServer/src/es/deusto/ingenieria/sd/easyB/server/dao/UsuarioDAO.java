package es.deusto.ingenieria.sd.easyB.server.dao;

import java.util.ArrayList;

import javax.jdo.Extent;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

import es.deusto.ingenieria.sd.easyB.server.data.Usuario;

public class UsuarioDAO implements IUsuarioDAO {
	private PersistenceManagerFactory pmf;
	private static UsuarioDAO instance = null;
	public static UsuarioDAO getInstance() {
		if (instance == null) {
			instance = new UsuarioDAO();
		}		
		
		return instance;
	}
	
	public UsuarioDAO() {
		pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
	}
	
	@Override
	public void storeUsuario(Usuario usuario) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			System.out.println("   * Storing Usuario: " + usuario);
			pm.makePersistent(usuario);
			pm.flush();
			tx.commit();
		} catch (Exception ex) {
			System.out.println("   $ Error storing Usuario: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
	}

	@Override
	public Usuario getUsuario(String email) {
		PersistenceManager pm = pmf.getPersistenceManager();
		pm.getFetchPlan().setMaxFetchDepth(3);
		
		Transaction tx = pm.currentTransaction();
		Usuario usuario = null;
		try {
			System.out.println("   * Querying a Usario: " + email);

			tx.begin();
			Query<?> query = pm.newQuery("SELECT FROM " + Usuario.class.getName() + " WHERE email == '" + email + "'");
			query.setUnique(true);
			usuario = (Usuario) query.execute();
			tx.commit();

		} catch (Exception ex) {
			System.out.println("   $ Error retreiving an extent: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
		return usuario;
	}

	@Override
	public void updateUsuario(Usuario usuario) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			pm.makePersistent(usuario);
			tx.commit();
		} catch (Exception ex) {
			System.out.println("   $ Error retreiving an extent: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
		
	}

	@Override
	public ArrayList<Usuario> getUsuarios() {
		PersistenceManager pm = pmf.getPersistenceManager();
		pm.getFetchPlan().setMaxFetchDepth(3);

		Transaction tx = pm.currentTransaction();
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();

		try {
			System.out.println("   * Retrieving an Extent for Usuarios.");

			tx.begin();
			Extent<Usuario> extent = pm.getExtent(Usuario.class, true);
			
			for (Usuario usuario : extent) {
				usuarios.add(usuario);
			}
			tx.commit();
		} catch (Exception ex) {
			System.out.println("   $ Error retreiving an extent: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}
		return usuarios;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Usuario> getUsuarios(String condition) {
		PersistenceManager pm = pmf.getPersistenceManager();
		pm.getFetchPlan().setMaxFetchDepth(3);

		Transaction tx = pm.currentTransaction();
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();

		try {
			System.out.println("   * Executing a Query for Usuarios given a condition: " + condition);

			tx.begin();
			Extent<Usuario> extent = pm.getExtent(Usuario.class, true);
			Query<Usuario> query = pm.newQuery(extent, condition);

			for (Usuario usuario : (ArrayList<Usuario>) query.execute()) {
				usuarios.add(usuario);
			}

			tx.commit();
		} catch (Exception ex) {
			System.out.println("   $ Error retreiving an extent: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}

		return usuarios;
	}
	@Override
	public void deleteAllUsuarios() {
		System.out.println("- Cleaning the Usuarios from the DB...");
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			
			// Getting ready for removing objects - Remove Relationships between Usuario and Reserva
			Extent<Usuario> extentB = pm.getExtent(Usuario.class, true);
			
			for (Usuario u : extentB) {
				u.removeReservas();
			}
			// Updating the database so changes are considered before commit
			pm.flush();

			// Deleting All Usuarios - Reservas and Usuarios will be deleted due to 'delete on cascade'
			Query<Usuario> query2 = pm.newQuery(Usuario.class);
			System.out.println(" * '" + query2.deletePersistentAll() + "' usuarios deleted from the DB.");
			tx.commit();
		} catch (Exception ex) {
			System.err.println(" $ Error cleaning the DB: " + ex.getMessage());
			ex.printStackTrace();
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			if (pm != null && !pm.isClosed()) {
				pm.close();
			}
		}
	}

}
