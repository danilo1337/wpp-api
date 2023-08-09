package br.com.danilo.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EnumSimNao {
	S("Sim"), N("Não");

	private final String nome;

	@JsonCreator
	public static EnumSimNao fromString(String value) {
		for (EnumSimNao enumValue : EnumSimNao.values()) {
			if (enumValue.nome.equals(value)) {
				return enumValue;
			}
		}
		throw new IllegalArgumentException("Valor inválido para EnumSimNao: " + value);
	}

	@JsonValue
	@Override
	public String toString() {
		return getNome();
	}
	
	public char toValue() {
		return getNome().charAt(0);
	}

}
