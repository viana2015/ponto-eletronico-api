package com.jrcg.pontoeletronico.api.services;

import java.util.Optional;

import com.jrcg.pontoeletronico.api.entities.Funcionario;

public interface FuncionarioService {

	/**
	 * Persiste um funcionário na base de dados.
	 * 
	 * @param funcionario
	 * @return Funcionario
	 */
	Funcionario persistir(Funcionario funcionario);
	
	/**
	 * Buscar e retorna um funcionario dado um CPF.
	 * 
	 * @param cpf
	 * @return Optional<Funcionario>
	 */
	
	Optional<Funcionario> buscarPorCpf(String cpf);
	
	/**
	 * Rtorna um funcionario dado um email.
	 * 
	 * @param email
	 * @return Optional<Funcionario>
	 */
	Optional<Funcionario> buscarPorEmail(String email);
	
	/**
	 * Busca e retorna um funcioanrio por ID.
	 * 
	 * @param id
	 * @return Optional<Funcionario>
	 */
	Optional<Funcionario> buscarPorId(Long id);
}
