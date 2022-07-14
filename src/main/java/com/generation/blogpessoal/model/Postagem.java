package com.generation.blogpessoal.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table( name = "tb_postagens" ) //CREATE TABLE tb_postagens();
public class Postagem {

	@Id // Primary Key (id)
	@GeneratedValue( strategy = GenerationType.IDENTITY) // AUTO_INCREMENT
	private Long id;
	
	//@NotBlank só funciona para Strings e não aceita espaço em branco
	@NotBlank( message = "O atributo Título é Obrigatório e não pode utilizar espaços em branco!")//NOT NULL
	@Size ( min = 5, max = 100, message = "O atributo Titulo Deve conter no mínimo 05 e no máximo 100 caractéres")
	private String titulo;
	
	@NotNull( message = "O atributo Texto é Obrigatório")
	@Size ( min = 10, max = 1000, message = "O atributo Texto Deve conter no mínimo 05 e no máximo 100 caractéres")
	private String texto;
	
	@UpdateTimestamp //Usa a data do Windows
	private LocalDateTime data;
	
	private long gostei;
	
	@ManyToOne
	@JsonIgnoreProperties ("postagem")
	private Tema tema;
	
	@ManyToOne
	@JsonIgnoreProperties("postagem")
	private Usuario usuario;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}
	
	public Tema getTema() {
		return tema;
	}

	public void setTema(Tema tema) {
		this.tema = tema;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public long getGostei() {
		return gostei;
	}

	public void setGostei(long gostei) {
		this.gostei = gostei;
	}
	
}

