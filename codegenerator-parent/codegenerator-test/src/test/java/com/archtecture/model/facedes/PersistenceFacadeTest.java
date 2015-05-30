package com.archtecture.model.facedes;

import javax.naming.InitialContext;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.sistema.cdg.model.Template;

public class PersistenceFacadeTest extends TestCase {

	private static String lookupName;

	@BeforeClass
	public static void beforeClass() {
		System.out.println("== JUNIT: BeforeClass");
	}

	@Before
	public static void before() {
		System.out.println("== JUNIT: Before");
	}

	@Test
	public void testClientBean() throws Exception {

		lookupName = "/codegenerator-ear/architecture-ejb-1.0/PersistenceFacade!com.archtecture.model.facedes.PersistenceFacadeLocal";

		PersistenceFacadeLocal clientBean = InitialContext.doLookup(lookupName);

		Template template = new Template();
		template.setCodigo(1L);

		template = clientBean.carregar(template);
		System.out.printf(template.getLabel());
		assertNotNull(template);
	}
}
