package br.com.chevrand.erp.converter;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import br.com.chevrand.erp.model.RamoAtividade;

public class RamoAtividadeConverter implements Converter {
	
	private List<RamoAtividade> listaRamoAtividade;

	public RamoAtividadeConverter(List<RamoAtividade> listaRamoAtividade) {
		this.listaRamoAtividade = listaRamoAtividade;
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (null == value) return null;
		
		return listaRamoAtividade
				.stream()
				.filter(ramo -> ramo.getId().equals(Long.valueOf(value)))
				.findFirst()
				.orElse(null);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (null == value) return null;
		
		RamoAtividade ramoAtividade = (RamoAtividade) value;
		
		return ramoAtividade.getId().toString();
	}

}
