package br.com.danilo.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EnumStatus {
	
	ATIVADO("ATIVADO"), DESATIVADO("DESATIVADO");

	private final String descricao;

	@JsonCreator
	public static EnumStatus fromString(String value) {
		for (EnumStatus enumValue : EnumStatus.values()) {
			if (enumValue.descricao.equals(value)) {
				return enumValue;
			}
		}
		throw new IllegalArgumentException("Valor inválido para EnumStatus: " + value);
	}

	@JsonValue
	@Override
	public String toString() {
		return getDescricao();
	}

}
