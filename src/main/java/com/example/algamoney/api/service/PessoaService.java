package com.example.algamoney.api.service;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.algamoney.api.model.Pessoa;
import com.example.algamoney.api.repository.PessoaRepository;

@Service
public class PessoaService {
	@Autowired
	private PessoaRepository pessoaRepository;

	public Pessoa atualizar(Long codigo, Pessoa pessoa) {

		Pessoa obj = buscarPessoa(codigo);
		BeanUtils.copyProperties(pessoa, obj, "codigo");
		return pessoaRepository.save(obj);
	}

	public void atualizarPropriedadeAtivo(Long codigo, @Valid Boolean ativo) {
		Pessoa obj = buscarPessoa(codigo);
		obj.setAtivo(ativo);
		pessoaRepository.save(obj);
	}

	private Pessoa buscarPessoa(Long codigo) {
		Pessoa obj = pessoaRepository.findById(codigo).orElseThrow(() -> new EmptyResultDataAccessException(1));
		return obj;
	}
}
