package br.com.chevrand.erp.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.chevrand.erp.model.Empresa;

public class Empresas implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;

	public Empresas() {

	}

	public Empresas(EntityManager manager) {
		this.manager = manager;
	}

	public Empresa findById(Long id) {
		return manager.find(Empresa.class, id);
	}

	public List<Empresa> findByTermo(String termo) {
		String jpql = "from Empresa where razaoSocial like :termo";
		
		TypedQuery<Empresa> query = manager
				.createQuery(jpql, Empresa.class);
		
		query.setParameter("termo", termo + "%");
		
		return query.getResultList();
	}
	
	public List<Empresa> getAll() {
		return manager.createQuery("from Empresa", Empresa.class).getResultList();
	}

	public Empresa save(Empresa empresa) {
		return manager.merge(empresa);
	}

	public void delete(Empresa empresa) {
		empresa = findById(empresa.getId());
		manager.remove(empresa);
	}
}
