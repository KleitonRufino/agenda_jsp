package com.logica;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.ContatoDao;
import com.model.Contato;

public class ListaContatosLogic implements Logica {

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception {
		List<Contato> contatos = new ContatoDao().getLista();

		req.setAttribute("contatos", contatos);

		return "/WEB-INF/jsp/lista-contatos.jsp";
	}

}
