package br.com.danilo.entity.id;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class PermissaoId implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Column(name = "username")
    private String username;

    @Column(name = "permissao")
    private String permissao;

}