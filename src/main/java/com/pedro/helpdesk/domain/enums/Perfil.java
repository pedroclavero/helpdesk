package com.pedro.helpdesk.domain.enums;

public enum Perfil {
	//Adicionando valores fixos nas enumerações
	
	ADMIM(0,"ROLE_ADMIM"), CLIENTE(1, "ROLE_CLIENTE") , TECNICO(2, "ROLE_TECNICO");
	
	private Integer cod;
	private String descricao;
	
	
	private Perfil(Integer cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public Integer getCod() {
		return cod;
	}

	public static Perfil toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		
		for(Perfil x : Perfil.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Perfil Inválido");
	}

	
	
	
}
