package com.grupoexata.auditoria.dao;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import com.grupoexata.auditoria.bean.BeanContratoquestao;
import com.grupoexata.auditoria.bean.BeanOpcao;
import com.grupoexata.auditoria.bean.BeanQuestao;
import com.grupoexata.auditoria.bean.BeanQuestionario;
import com.grupoexata.bancario.utils.Utils;

public class ModelQuestionario extends Model implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8802867888634316301L;
	private static final String QR_QUESTIONARIO_LIST = "SELECT * FROM VW_QUESTIONARIO";
	private static final String QR_QUESTIONARIO_BY_ID = "SELECT * FROM VW_QUESTIONARIO WHERE QTNNCODG = ?";
	private static final String QR_QUESTAO_LIST_BY_QSTNCGQTN = "SELECT * FROM VW_QUESTAO WHERE QSTNCGQTN = ? AND QSTNCGQST IS NULL";
	private static final String QR_QUESTAO_LIST_BY_QSTNCGQST = "SELECT * FROM VW_QUESTAO WHERE QSTNCGQST = ?";
	private static final String QR_QUESTAO_BY_ID = "SELECT * FROM VW_QUESTAO WHERE QSTNCODG = ?";
	private static final String QR_OPCAO_LIST_BY_OPCNCGGPO = "SELECT * FROM VW_OPCAO WHERE OPCNCGGPO = ?";
	private static final String QR_ADD_QUESTAO = "EXECUTE PROCEDURE SP_ADD_CONTRATOQUESTAO( ?, ?, ?, ?, ?);";
	
	public static ModelQuestionario getIntance(){
		return new ModelQuestionario();
	}
	
	public List<BeanQuestionario> list(){
		return getList(BeanQuestionario.class, QR_QUESTIONARIO_LIST);
	}
	
	public BeanQuestionario get(int qtnncodg){
		return getById(BeanQuestionario.class, QR_QUESTIONARIO_BY_ID, qtnncodg);
	}
	public BeanQuestao getQuestao(int qstncodg){
		return getById(BeanQuestao.class, QR_QUESTAO_BY_ID, qstncodg);
	}
	
	public List<BeanQuestao> listQuestaoByQstncgqtn(int qstncgqtn){
		return getListByFk(BeanQuestao.class, QR_QUESTAO_LIST_BY_QSTNCGQTN, qstncgqtn);
	}
	
	public List<BeanQuestao> listQuestaoByQstncgqst(int qstncgqst){
		return getListByFk(BeanQuestao.class, QR_QUESTAO_LIST_BY_QSTNCGQST, qstncgqst);
	}

	public List<BeanOpcao> listOpcaoByOpcncggpo(int opcncggpo){
		return getListByFk(BeanOpcao.class, QR_OPCAO_LIST_BY_OPCNCGGPO, opcncggpo);
	}
	
	public BeanQuestionario getRecusive(int qtnncodg){
		BeanQuestionario questionario = get(qtnncodg);
		List<BeanQuestao> questaos = listQuestaoByQstncgqtn(qtnncodg);
		for (BeanQuestao questao : questaos) {
//			if(Utils.exists(questao.getQstncggpo())){
//				questao.setOpcaos(listOpcaoByOpcncggpo(Integer.parseInt(questao.getQstncggpo())));
//			}else{
//				questao.setOpcaos(new ArrayList<BeanOpcao>());
//			}
//			questao.setQuestaos(listQuestaoByQstncgqst(Integer.parseInt(questao.getQstncodg())));
			getQuestaoRecusive(questao);
		}
		questionario.setQuestaos(questaos);
		return questionario;
	}
	
	public void getQuestaoRecusive(BeanQuestao questao){
		if(Utils.exists(questao.getQstncggpo())){
			questao.setOpcaos(listOpcaoByOpcncggpo(Integer.parseInt(questao.getQstncggpo())));
		}else{
			questao.setOpcaos(new ArrayList<BeanOpcao>());
		}
		questao.setQuestaos(listQuestaoByQstncgqst(Integer.parseInt(questao.getQstncodg())));
		for (BeanQuestao questao1 : questao.getQuestaos()) {
			getQuestaoRecusive(questao1);
		}
	}
	
	public void addQuestao(BeanContratoquestao ctq){
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_ADD_QUESTAO);
			st.setInt(1, Integer.parseInt(ctq.getCtqncgen()));
			st.setInt(2, Integer.parseInt(ctq.getCtqncgct()));
			st.setInt(3, Integer.parseInt(ctq.getCtqncgcqn()));
			st.setInt(4, Integer.parseInt(ctq.getCtqncgqtn()));
			st.setInt(5, Integer.parseInt(ctq.getCtqncgopc()));
			st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try{
				if(st != null && st.isClosed()){
					st.close();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}	
	}
	
	public void addQuestaos(List<BeanContratoquestao> list){
		PreparedStatement st = null;
		try {
			st = getConnection().prepareStatement(QR_ADD_QUESTAO);
			for (BeanContratoquestao ctq : list) {
				st.setInt(1, Integer.parseInt(ctq.getCtqncgen()));
				st.setInt(2, Integer.parseInt(ctq.getCtqncgct()));
				st.setInt(3, Integer.parseInt(ctq.getCtqncgqtn()));
				st.setInt(4, Integer.parseInt(ctq.getCtqncgqst()));
				st.setInt(5, Integer.parseInt(ctq.getCtqncgopc()));
				st.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try{
				if(st != null && st.isClosed()){
					st.close();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}	
	}
	
}
