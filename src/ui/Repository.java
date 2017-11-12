package ui;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import model.PlaneMeta;

public class Repository<T> {
	private Session session;
	private final Class<T> cls;
	public Repository(Class<T> cls) {
		this.session = Main.getSession();
		this.cls = cls;
	}
	
	public Repository(Class<T> cls,Session session) {
		this.session = session;
		this.cls = cls;
	}
	
	
	public List<T> selectall() {
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<T> c = (CriteriaQuery) criteriaBuilder.createQuery(PlaneMeta.class);
		Root<T> root = c.from(cls);
		c.select(root);
		return session.createQuery(c).getResultList();
	}
	
	public T selectone(int id) {
		return (T) session.get(cls, id);
	}
	
	public void insertOne(T obj) {
		Transaction transaction = session.beginTransaction();
		session.save(obj);
		transaction.commit();
	}
	
	public boolean deletetOne(int id) throws javax.persistence.PersistenceException{
		T obj = selectone(id);
		if(obj==null) {
			return false;
		}
		Transaction transaction = session.beginTransaction();
		session.delete(obj);
		transaction.commit();
		return true;
	}
}
