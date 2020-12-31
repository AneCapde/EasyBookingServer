package es.deusto.ingenieria.sd.easyB.server.jdo;

import javax.jdo.annotations.Inheritance;

import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;
@PersistenceCapable(detachable = "true")
@Inheritance(strategy = InheritanceStrategy.NEW_TABLE)
public abstract class SistemaPago {

}
