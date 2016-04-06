package com.logica;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.ContatoDao;
import com.model.Contato;

public class RemoveContatoLogic implements Logica {

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception {
		int id = Integer.parseInt(req.getParameter("id"));

		Contato contato = new Contato();
		contato.setId(id);

		ContatoDao dao = new ContatoDao();
		dao.exclui(contato);

		System.out.println("Excluindo contato... ");

		return "mvc?logica=ListaContatosLogic";
		// return "lista-contatos.jsp";
	}

}
