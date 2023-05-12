package br.com.chevrand.erp.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.chevrand.erp.model.RamoAtividade;

public class RamoAtividades implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;

	public RamoAtividades() {

	}

	public RamoAtividades(EntityManager manager) {
		this.manager = manager;
	}
	
	public List<RamoAtividade> pesquisar(String descricao) {
		String jpql = "from RamoAtividade where descricao like :descricao";
		
		TypedQuery<RamoAtividade> query = manager.createQuery(jpql, RamoAtividade.class);
		query.setParameter("descricao", descricao + "%");
		
		return query.getResultList();
	}
}
