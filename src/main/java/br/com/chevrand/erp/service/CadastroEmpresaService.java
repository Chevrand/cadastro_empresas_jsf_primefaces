package br.com.chevrand.erp.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.chevrand.erp.model.Empresa;
import br.com.chevrand.erp.repository.Empresas;
import br.com.chevrand.erp.util.Transacional;

public class CadastroEmpresaService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Empresas empresas;
	
	@Transacional
	public void salvar(Empresa empresa) {
		empresas.save(empresa);
	}
	
	@Transacional
	public void excluir(Empresa empresa) {
		empresas.delete(empresa);
	}
}
