package fr.formation.proxibanquev3.presentation;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.formation.proxibanquev3.metier.AccountService;

public class AddCardServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Integer accountId = Integer.parseInt(req.getParameter("accountId"));
		if (AccountService.getInstance().linkNewCard(accountId, "premier")) {
			resp.sendRedirect(this.getServletContext().getContextPath() + "/index.html");			
		} else {
			this.getServletContext().getRequestDispatcher("/WEB-INF/views/card_error.jsp").forward(req, resp);
		}
	}

}
