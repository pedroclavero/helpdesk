package com.pedro.helpdesk.domain.enums;

public enum Prioridade {
	//Adicionando valores fixos nas enumerações
	
	BAIXA(0,"BAIXA"), MEDIA(1, "MEDIA") , ALTA(2, "ALTA");
	
	private Integer cod;
	private String descricao;
	
	
	private Prioridade(Integer cod, String descricao) {
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

	public static Prioridade toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		
		for(Prioridade x : Prioridade.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Prioridade Inválida");
	}

	
	
	
}
