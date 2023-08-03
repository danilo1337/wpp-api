package br.com.danilo.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import br.com.danilo.entity.id.PermissaoId;
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
@Entity
@Table(name = "tb_permissao")
public class Permissao implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
    @EmbeddedId
    private PermissaoId id;
    
	@Column(name = "username", insertable = false , updatable =false)
	private String username;
	
}
