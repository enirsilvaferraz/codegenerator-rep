package com.architecture.view;

import java.util.ResourceBundle;

import javax.ejb.EJBTransactionRolledbackException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.PersistenceException;
import javax.transaction.RollbackException;

public final class UtilWeb {

	public static void enviarMensagem(MensagemEnum pMensagem, String... pParametros) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(pMensagem.getSeverity(), obterTextoMensagem(NomeProperties.MENSAGENS, pMensagem, pParametros), null));
	}

	public static String obterTextoMensagem(NomeProperties pNomeProperties, MensagemEnum pIdMensagem, String... pParametros) {

		String lMessageProperty = ResourceBundle.getBundle(pNomeProperties.name().toLowerCase()).getString(pIdMensagem.getSigla());

		for (int i = 0; i < pParametros.length; i++) {

			if (lMessageProperty.contains("{" + i + "}")) {
				lMessageProperty = lMessageProperty.replace("{" + i + "}", pParametros[i]);
			}
		}

		return lMessageProperty;
	}

	public static void tratarException(Exception e) {
		
		e.printStackTrace();

		// UtilException.obterMensagemException(e);

		if (e instanceof EJBTransactionRolledbackException) {

			if (e.getCause() instanceof RollbackException) {

				if (e.getCause().getCause() instanceof PersistenceException) {

					// if (e.getCause().getCause().getCause() instanceof
					// ConstraintViolationException) {

					String lExceptionMensage = e.getCause().getCause().getCause().getMessage().toString();

					// Column {0} cannot be null
					if (lExceptionMensage.startsWith("Column '") && lExceptionMensage.endsWith("' cannot be null")) {
						String lColumn = obterNomeColuna(lExceptionMensage.replace("Column '", "").replace("' cannot be null", ""));
						// UtilWeb.enviarMensagem(MensagemEnum.MSG_CAMPO_OBRIGATORIO,
						// lColumn);
					}

					else if (lExceptionMensage.startsWith("Duplicate entry '") && lExceptionMensage.endsWith("' for key 'PRIMARY'")) {

					}

					// org.hibernate.exception.ConstraintViolationException:
					// Cannot add or update a child row: a foreign key
					// constraint fails (`sgvdev`.`tbproduto`, CONSTRAINT
					// `FKPRODUTO_CATEGORIA` FOREIGN KEY (`CD_CATEGORIA`)
					// REFERENCES `tbcategoria` (`CD_CATEGORIA`))
					// org.hibernate.exception.DataException: Data truncation:
					// Data too long for column 'FL_PRE_CADASTRO' at row 1

					// PESQUISAR NO GOOGLE POR TIPO DE EXCEPTION
				}
				// }
			}
		}

		else {

			UtilWeb.enviarMensagem(MensagemEnum.ERRO_AMBIENTE);

		}
	}

	private static String obterNomeColuna(String replace) {

		if (replace.equals("CD_CATEGORIA")) {
			return "Categoria";
		}

		return null;
	}

//	public static void tratarException(NegocioException e) {
//
//		if (e.getMensagemErro() != null) {
//			UtilWeb.enviarMensagem(MensagemEnum.valueOf(e.getMensagemErro().toString()));
//		} else {
//			UtilWeb.enviarMensagem(MensagemEnum.ERRO_AMBIENTE);
//		}
//
//	}

	// private NegocioException tratarException(Throwable pErro, String pModel)
	// {
	//
	// if
	// (pErro.getClass().getName().equals("com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException"))
	// {
	//
	// try {
	//
	// Integer lVendorCode =
	// Integer.parseInt(pErro.getClass().getMethod("getErrorCode").invoke(pErro).toString());
	// TabelaErrosMySql lErrosMySql = TabelaErrosMySql.valueOf(lVendorCode);
	//
	// if (lErrosMySql != null) {
	// return new NegocioException(lErrosMySql.getMensagemNegocio());
	// }
	// else {
	// return new NegocioException(Mensagem.ERRO_AMBIENTE);
	// }
	//
	// }
	// catch (NumberFormatException e1) {
	// return new NegocioException(Mensagem.ERRO_AMBIENTE, e1);
	// }
	// catch (IllegalArgumentException e1) {
	// return new NegocioException(Mensagem.ERRO_AMBIENTE, e1);
	// }
	// catch (SecurityException e1) {
	// return new NegocioException(Mensagem.ERRO_AMBIENTE, e1);
	// }
	// catch (IllegalAccessException e1) {
	// return new NegocioException(Mensagem.ERRO_AMBIENTE, e1);
	// }
	// catch (InvocationTargetException e1) {
	// return new NegocioException(Mensagem.ERRO_AMBIENTE, e1);
	// }
	// catch (NoSuchMethodException e1) {
	// return new NegocioException(Mensagem.ERRO_AMBIENTE, e1);
	// }
	// }
	// else {
	// if (pErro.getCause() != null) {
	// return tratarException(pErro.getCause(), pModel);
	// }
	// else {
	// return new NegocioException(Mensagem.ERRO_AMBIENTE);
	// }
	// }
	// }
}
