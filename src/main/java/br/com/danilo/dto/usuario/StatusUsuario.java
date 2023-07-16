package br.com.danilo.dto.usuario;

import br.com.danilo.enums.EnumStatus;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class StatusUsuario {
	private  EnumStatus status;
}
