package com.excilys.formation.cdb.ui.cmd;

import java.io.IOException;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.excilys.formation.cdb.model.Computer;
import com.excilys.formation.cdb.service.ICompanyService;
import com.excilys.formation.cdb.service.IComputerService;
import com.excilys.formation.util.Utils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class TestCmd {

	private IComputerService computerService = Utils.context
			.getBean(IComputerService.class);

	private ICompanyService companyService = Utils.context
			.getBean(ICompanyService.class);

	@BeforeClass
	public static void setUp() throws IOException {
		new Utils().loadDatabase();
	}

	@Before
	public void before() {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	@AfterClass
	public static void reset() throws IOException {
		new Utils().unloadDatabase();
	}

	@Test
	public void ListComputerTest() {
		ICommand cmd = new ListComputerCmd();
		cmd.execute();
	}

	@Test
	public void ListCompanyTest() {
		ICommand cmd = new ListCompanyCmd();
		cmd.execute();
	}

	@Test
	public void ShowComputerTest() {
		List<Computer> l = computerService.findAll();
		ICommand cmd = new ComputerDetailsCmd(l.get(
				(int) (Math.random() * l.size())).getId());
		cmd.execute();
	}

	@Test
	public void CreateUpdateDeleteComputerTest() {
		ICommand cmd1 = new CreateComputerCmd(new Computer("Joxit", null, null,
				companyService.find(17l)));
		cmd1.execute();
		Computer computer = computerService.find(c -> (c.getName() != null)
				&& c.getName().equals("Joxit"));
		assert (computer != null);
		computer.setName("Joxit42");
		ICommand cmd2 = new UpdateComputerCmd(computer);
		cmd2.execute();
		assert (computerService.find(c -> c.getName().equals("Joxit42")) != null);
		assert (computerService.find(c -> c.getName().equals("Joxit")) == null);
		ICommand cmd3 = new DeleteComputerCmd(computer.getId());
		cmd3.execute();
		assert (computerService.find(c -> c.getName().equals("Joxit42")) == null);
	}

	@Test
	public void invalidComputerDetail() {
		Long l = -1l;
		ICommand cmd = new ComputerDetailsCmd(l);
		cmd.execute();
	}

}