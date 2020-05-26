package com.uca.capas.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.uca.capas.domain.Categoria;
import com.uca.capas.domain.Libro;

@Repository
public class LibroDAOImpl implements LibroDAO{

	@PersistenceContext(unitName = "capas")
	private EntityManager entityManager;
	
	@Override
	public List<Libro> findAll() throws DataAccessException {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT * FROM public.cat_libro");
		javax.persistence.Query q = entityManager.createNativeQuery(sb.toString(), Libro.class);
		List<Libro> resultset = q.getResultList();
		
		return resultset;
	}

	@Override
	public Libro findOne(Integer codigoLibro) throws DataAccessException {
		// TODO Auto-generated method stub
		Libro libro = entityManager.find(Libro.class, codigoLibro);
		return libro;
	}

	@Override
	@Transactional
	public void save(Libro libro) throws DataAccessException {
		// TODO Auto-generated method stub
		try {
			if(libro.getCodigoLibro() == null) {
				entityManager.persist(libro);
			}else {
				entityManager.merge(libro);
				entityManager.flush();
			}
		}catch(Throwable e) {
			e.printStackTrace();
		}
	}

	@Override
	@Transactional
	public void delete(Integer codigoLibro) throws DataAccessException {
		// TODO Auto-generated method stub
		Libro libro = entityManager.find(Libro.class, codigoLibro);
		entityManager.remove(libro);
	}

}
