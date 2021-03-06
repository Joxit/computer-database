package com.excilys.formation.cdb.ui.cmd;

import javax.ws.rs.core.Response;

import com.excilys.formation.cdb.util.WebServiceUtils;

public class DeleteCompanyCmd implements ICommand {
	private final Long company;

	public DeleteCompanyCmd(Long id) {
		company = id;
	}

	@Override
	public void execute() {
		if (company == null) {
			System.out.println("Delete failed : Company is null");
			return;
		}
		Response response = WebServiceUtils.deleteCompanyResponse(company);
		if (response.getStatus() == 204) {
			System.out.println("Entry deleted." + company);
		} else {
			System.out.println("Sorry, something goes wrong. Status: "
					+ response.getStatus());
		}
		response.close();
	}

}
