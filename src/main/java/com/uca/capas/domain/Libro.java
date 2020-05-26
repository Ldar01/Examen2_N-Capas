package com.uca.capas.domain;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(schema = "public", name = "cat_libro")
public class Libro {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "c_libro")
	public Integer codigoLibro;
	
	@Column(name = "s_titulo")
	@Size(message = "El campo sobrepasa la cantidad de 500 caracteres", max = 500)
	@NotEmpty(message = "“El campo no puede estar vacío")
	public String titulo;
	
	@Column(name = "s_autor")
	@Size(message = "El campo sobrepasa la cantidad de 150 caracteres", max = 150)
	@NotEmpty(message = "“El campo no puede estar vacío")
	public String autor;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "c_categoria")
	private Categoria categoria;

	@Column(name = "f_ingreso")
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@CreationTimestamp
	private LocalDate fechaIngreso;

	@Column(name = "b_estado")
	public Boolean estado;
	
	@Column(name = "s_isbn")
	@Size(message = "El campo sobrepasa la cantidad de 10 caracteres", max = 10)
	@NotEmpty(message = "“El campo no puede estar vacío")
	public String isbn;
	
	public Libro(){}
	
	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public Integer getCodigoLibro() {
		return codigoLibro;
	}
	
	public void setCodigoLibro(Integer codigoLibro) {
		this.codigoLibro = codigoLibro;
	}
	
	public String getTitulo() {
		return titulo;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public String getAutor() {
		return autor;
	}
	
	public void setAutor(String autor) {
		this.autor = autor;
	}
	
	
	
	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	@DateTimeFormat(pattern="dd-MMM-YYYY")
	public LocalDate getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(LocalDate fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public Boolean getEstado() {
		return estado;
	}
	
	public void setEstado(Boolean estado) {
		this.estado = estado;
	}
	
	public String getEstadoDelegate() {
		if(this.estado == null) {
			return "";
		}else {
			return estado == true ?"Activo":"Inactivo";
		}
	}
	
}
