package com.jrcg.pontoeletronico.api.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.jrcg.pontoeletronico.api.entities.Funcionario;
import com.jrcg.pontoeletronico.api.enums.PerfilEnum;

public class JwtUserFactory {

	private JwtUserFactory() {
		
	}
	
	/**
	 * Converte e gera um JwtUser com base nos dados de um funcionário.
	 * 
	 * @param funcionario
	 * @return JwtUser
	 */
	
	public static JwtUser create(Funcionario funcionario) {
		return new JwtUser(funcionario.getId(), funcionario.getEmail(), funcionario.getSenha(),
					mapToGrantedAuthorities(funcionario.getPerfil()));
	}
	
	/**
	 * Converter o perfil do ususario para o formato utilizado pelo Spring Security;
	 * 
	 * @param perfilEnum
	 * @return List<GrantedAuthority>
	 */
	
	private static List<GrantedAuthority>mapToGrantedAuthorities(PerfilEnum perfilEnum){
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(perfilEnum.toString()));
		return authorities;
	}
	
}
