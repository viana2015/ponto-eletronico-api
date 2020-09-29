package com.jrcg.pontoeletronico.api.controllers;

import java.security.NoSuchAlgorithmException;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jrcg.pontoeletronico.api.dtos.CadastroPJDto;
import com.jrcg.pontoeletronico.api.entities.Empresa;
import com.jrcg.pontoeletronico.api.entities.Funcionario;
import com.jrcg.pontoeletronico.api.enums.PerfilEnum;
import com.jrcg.pontoeletronico.api.response.Response;
import com.jrcg.pontoeletronico.api.services.EmpresaService;
import com.jrcg.pontoeletronico.api.services.FuncionarioService;
import com.jrcg.pontoeletronico.api.utils.PasswordUtils;

@RestController
@RequestMapping("/api/cadastrar-pj")
@CrossOrigin(origins = "*")
public class CadastroPJController {

	private static final Logger log = LoggerFactory.getLogger(CadastroPJController.class);
	
	@Autowired
	private FuncionarioService funcionarioService;
	
	@Autowired
	private EmpresaService empresaService;
	
	public CadastroPJController() {
		
	}
	
	/**
	 * Cadastra uma pessoa juridica no sistema.
	 * 
	 * @param cadastroPjDto
	 * @param result
	 * @return ResponseEntity<Response<CadastroPJDto>>
	 * @throws NoSuchAlgorithmException
	 */
	
	@PostMapping
	public ResponseEntity<Response<CadastroPJDto>> cadastrar(@Valid @RequestBody CadastroPJDto cadastroPjDto,
			BindingResult result) throws NoSuchAlgorithmException {
		log.info("Cadastrando PJ: {}", cadastroPjDto.toString());
		Response<CadastroPJDto> response = new Response<CadastroPJDto>();
		
		validarDadosExistentes(cadastroPjDto, result);
		Empresa empresa = this.converterDtoParaEmpresa(cadastroPjDto);
		Funcionario funcionario = this.converterDtoParaFuncionario(cadastroPjDto, result);
		
		if (result.hasErrors()) {
			log.error("Erro validando dados de cadastro PJ: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
		this.empresaService.persistir(empresa);
		funcionario.setEmpresa(empresa);
		this.funcionarioService.persistir(funcionario);
		
		response.setData(this.converterCadastroPJDto(funcionario));
		return ResponseEntity.ok(response);
	}



	/**
	 * Verifica se a empresa ou funcionário existem na base de dados.
	 * 
	 * @param cadastroPjDto
	 * @param result
	 */
	private void validarDadosExistentes(@Valid CadastroPJDto cadastroPjDto, BindingResult result) {
		this.empresaService.buscarPorCnpj(cadastroPjDto.getCnpj())
		.ifPresent(emp -> result.addError(new ObjectError("empresa", "Empresa já existente.")));
		
		this.funcionarioService.buscarPorCpf(cadastroPjDto.getCpf())
		.ifPresent(func -> result.addError(new ObjectError("funcionario", "CPF já existente.")));
		
		this.funcionarioService.buscarPorEmail(cadastroPjDto.getEmail())
		.ifPresent(func -> result.addError(new ObjectError("funcionario", "Email já existente.")));
		
		
	}

	/**
	 * Converte os dados do DTO para empresa.
	 * 
	 * @param cadastroPjDto
	 * @return Empresa
	 */
	private Empresa converterDtoParaEmpresa(@Valid CadastroPJDto cadastroPjDto) {
		Empresa empresa = new Empresa();
		empresa.setCnpj(cadastroPjDto.getCnpj());
		empresa.setRazaoSocial(cadastroPjDto.getRazaoSocial());
		return empresa;
	}

	/**
	 * Converte os dados do DTO para funcionario.
	 * 
	 * @param cadastroPjDto
	 * @param result
	 * @return Funcionario
	 * @throws NoSuchAlgorithmException
	 */
	private Funcionario converterDtoParaFuncionario(@Valid CadastroPJDto cadastroPjDto, BindingResult result) throws NoSuchAlgorithmException {
		Funcionario funcionario = new Funcionario();
		funcionario.setNome(cadastroPjDto.getNome());
		funcionario.setEmail(cadastroPjDto.getEmail());
		funcionario.setCpf(cadastroPjDto.getCpf());
		funcionario.setPerfil(PerfilEnum.ROLE_ADMIN);
		funcionario.setSenha(PasswordUtils.gerarBCrypt(cadastroPjDto.getSenha()));
		
		return funcionario;
	}
	
	/**
	 * Popula o DTO com os dados de funcionario e empresa.
	 * 
	 * @param funcionario
	 * @return CadastroPJDto
	 */
	private CadastroPJDto converterCadastroPJDto(Funcionario funcionario) {
		CadastroPJDto cadastroPJDto =  new CadastroPJDto();
		cadastroPJDto.setId(funcionario.getId());
		cadastroPJDto.setNome(funcionario.getNome());
		cadastroPJDto.setEmail(funcionario.getEmail());
		cadastroPJDto.setCpf(funcionario.getCpf());
		cadastroPJDto.setRazaoSocial(funcionario.getEmpresa().getRazaoSocial());
		cadastroPJDto.setCnpj(funcionario.getEmpresa().getCnpj());
		
		return cadastroPJDto;
	}
	
	
}
