package com.jrcg.pontoeletronico.api.services.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jrcg.pontoeletronico.api.entities.Funcionario;
import com.jrcg.pontoeletronico.api.repositories.FuncionarioRepository;
import com.jrcg.pontoeletronico.api.services.FuncionarioService;

@Service
public class FuncionarioServiceImpl implements FuncionarioService {

	private static final Logger log = LoggerFactory.getLogger(FuncionarioServiceImpl.class);
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	public Funcionario persistir(Funcionario funcionario) {
		log.info("persistindo funcionário: {}", funcionario);
		return this.funcionarioRepository.save(funcionario);
	}
	
	public Optional<Funcionario> buscarPorCpf(String cpf) {
		log.info("Buscando funcionário por CPF: {}", cpf);
		return Optional.ofNullable(this.funcionarioRepository.findByCpf(cpf));
	}
	
	public Optional<Funcionario> buscarPorEmail(String email) {
		log.info("Buscando funcionário por email: {}", email);
		return Optional.ofNullable(this.funcionarioRepository.findByEmail(email));
	}
	
	public Optional<Funcionario> buscarPorId(Long id) {
		log.info("Buscando funcionário por ID: {}", id);
		return this.funcionarioRepository.findById(id);
	}
}
