package br.com.chevrand.erp.controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.PrimeFaces;

import br.com.chevrand.erp.converter.RamoAtividadeConverter;
import br.com.chevrand.erp.model.Empresa;
import br.com.chevrand.erp.model.RamoAtividade;
import br.com.chevrand.erp.model.TipoEmpresa;
import br.com.chevrand.erp.repository.Empresas;
import br.com.chevrand.erp.repository.RamoAtividades;
import br.com.chevrand.erp.service.CadastroEmpresaService;
import br.com.chevrand.erp.util.FacesMessages;

@Named
@ViewScoped
public class GestaoEmpresasBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private List<Empresa> listaEmpresas;
	
	private String termoPesquisa = "";	
	
	private RamoAtividadeConverter ramoAtividadeConverter;
	
	private Empresa empresa;

	@Inject
	private Empresas empresas;
	
	@Inject
	private FacesMessages facesMessages;
	
	@Inject
	private RamoAtividades ramoAtividades;
	
	@Inject
	private CadastroEmpresaService cadastroEmpresaService;
	
	public void getAllEmpresas() {
		listaEmpresas = empresas.getAll();		
	}
	
	public void pesquisar() {
		listaEmpresas = empresas.findByTermo(termoPesquisa);
		
		if (listaEmpresas.isEmpty()) {
			facesMessages.addInfoMessage("Sua consulta não retornou registros!");
			PrimeFaces.current().ajax().update("messages");
		}
	}
	
	public void prepararNovaEmpresa() {
		empresa = new Empresa();
	}
	
	public void prepararEdicaoEmpresa() {
		ramoAtividadeConverter = new RamoAtividadeConverter(Arrays.asList(empresa.getRamoAtividade()));
	}
	
	public void salvar() {
		cadastroEmpresaService.salvar(empresa);		
		facesMessages.addInfoMessage("Empresa cadastrada com sucesso!");
		PrimeFaces.current().ajax().update(Arrays.asList(
				"frm:empresasDataTable", "frm:messages"));
		this.pesquisar();
	}
	
	public void excluir() {
		cadastroEmpresaService.excluir(empresa);
		empresa = null;
		facesMessages.addInfoMessage("Empresa excluída com sucesso!");
		PrimeFaces.current().ajax().update(Arrays.asList(
				"frm:empresasDataTable", "frm:messages"));
		this.pesquisar();
	}
	
	public List<RamoAtividade> completarRamoAtividade(String termo) {
		List<RamoAtividade> listaRamoAtividade = ramoAtividades.pesquisar(termo);
		ramoAtividadeConverter = new RamoAtividadeConverter(listaRamoAtividade);
		
		return listaRamoAtividade;
	}
	
	public boolean isPesquisaActive() {
		return termoPesquisa != null && !termoPesquisa.isEmpty();
	}
	
	public boolean isEmpresaSelected() {
		return empresa != null && null != empresa.getId();
	}
	
	public List<Empresa> getListaEmpresas() {
		return listaEmpresas;
	}
	
	public String getTermoPesquisa() {
		return termoPesquisa;
	}	
	
	public void setTermoPesquisa(String termoPesquisa) {
		this.termoPesquisa = termoPesquisa;
	}
	
	public TipoEmpresa[] getTiposEmpresa() {
		return TipoEmpresa.values();
	}
	
	public RamoAtividadeConverter getRamoAtividadeConverter() {
		return ramoAtividadeConverter;
	}
	
	public Empresa getEmpresa() {
		return empresa;
	}
	
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
}
