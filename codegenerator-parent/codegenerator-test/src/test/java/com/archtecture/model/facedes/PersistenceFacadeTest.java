package com.archtecture.model.facedes;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import junit.framework.TestCase;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.sistema.cdg.model.Template;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PersistenceFacadeTest extends TestCase {

	private static Long codigo;

	private static PersistenceFacadeLocal clientBean;

	@BeforeClass
	public static void setUpClass() throws NamingException {
		String lookupName = "/codegenerator-ear/architecture-ejb-1.0/PersistenceFacade!com.archtecture.model.facedes.PersistenceFacadeLocal";
		clientBean = InitialContext.doLookup(lookupName);
	}

	@AfterClass
	public static void tearDownClass() {
	}

	@Test
	public void test1Inserir() throws NamingException {
		
		setUpClass();
		
		try {

			Template template = new Template();
			template.setCodigoFonte("JUNIT TEST");
			template.setNome("JUNIT TEST");
			template = clientBean.inserir(template);

			Template templateAux = new Template();
			templateAux.setCodigo(template.getCodigo());
			templateAux = clientBean.carregar(template);

			assertNotNull(template);
			assertEquals(template.getCodigo(), templateAux.getCodigo());
			assertEquals(template.getCodigoFonte(), templateAux.getCodigoFonte());
			assertEquals(template.getNome(), templateAux.getNome());
			assertEquals(template.getTeplateTela(), templateAux.getTeplateTela());

			codigo = template.getCodigo();

		} catch (Exception e) {
			assertTrue(false);
		}
	}

	@Test
	public void test2Alterar() throws NamingException {

		setUpClass();
		
		try {

			Template template = new Template();
			template.setCodigo(codigo);
			template.setCodigoFonte("JUNIT TEST ALTERADO");
			template.setNome("JUNIT TEST ALTERADO");

			Template templateAux = clientBean.carregar(template);

			assertEquals(template.getCodigo(), templateAux.getCodigo());
			assertNotSame(template.getCodigoFonte(), templateAux.getCodigoFonte());
			assertNotSame(template.getNome(), templateAux.getNome());

			clientBean.atualizar(template);
			templateAux = clientBean.carregar(template);

			assertNotNull(templateAux);
			assertEquals(template.getCodigo(), templateAux.getCodigo());
			assertEquals(template.getCodigoFonte(), templateAux.getCodigoFonte());
			assertEquals(template.getNome(), templateAux.getNome());
			assertEquals(template.getTeplateTela(), templateAux.getTeplateTela());

		} catch (Exception e) {
			assertTrue(false);
		}
	}

	@Test
	public void test4Excluir() throws NamingException {

		setUpClass();
		
		try {

			Template template = new Template();
			template.setCodigo(codigo);

			clientBean.excluir(template);
			Template modelOld = clientBean.carregar(template);

			assertNull(modelOld);

		} catch (Exception e) {
			assertTrue(false);
		}
	}
}
