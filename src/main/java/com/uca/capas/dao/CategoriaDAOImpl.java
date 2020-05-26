package com.uca.capas.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.uca.capas.domain.Categoria;

@Repository
public class CategoriaDAOImpl implements CategoriaDAO {

	@PersistenceContext(unitName = "capas")
	private EntityManager entityManager;
	
	@Override
	public List<Categoria> findAll() throws DataAccessException {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT * FROM public.cat_categoria");
		javax.persistence.Query q = entityManager.createNativeQuery(sb.toString(), Categoria.class);
		List<Categoria> resultset = q.getResultList();
		
		return resultset;
	}

	@Override
	public Categoria findOne(Integer codigoCategoria) throws DataAccessException {
		// TODO Auto-generated method stub
		Categoria categoria = entityManager.find(Categoria.class, codigoCategoria);
		return categoria;
	}

	@Override
	@Transactional
	public void save(Categoria categoria) throws DataAccessException {
		// TODO Auto-generated method stub
		try {
			if(categoria.getCodigoCategoria() == null) {
				entityManager.persist(categoria);
			}else {
				entityManager.merge(categoria);
				entityManager.flush();
			}
		}catch(Throwable e) {
			e.printStackTrace();
		}
	}

	@Override
	@Transactional
	public void delete(Integer codigoCategoria) throws DataAccessException {
		// TODO Auto-generated method stub
		Categoria categoria = entityManager.find(Categoria.class, codigoCategoria);
		entityManager.remove(categoria);
	}

}
