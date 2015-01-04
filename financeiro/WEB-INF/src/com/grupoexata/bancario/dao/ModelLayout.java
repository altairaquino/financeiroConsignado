package com.grupoexata.bancario.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import com.grupoexata.bancario.struts.bean.BeanGcomimport;
import com.grupoexata.bancario.struts.bean.BeanLayoutitem;
import com.grupoexata.bancario.utils.Utils;

public class ModelLayout {
	
	private static final String QR_LAYOUTITEM = "SELECT * FROM VW_LAYOUTITEM";
	private static final String QR_LAYOUTITEM_LITNIDTIP = "SELECT * FROM VW_LAYOUTITEM WHERE LITNIDTIP = ?";
	private static final String QR_LAYOUTCLASS = "SELECT * FROM VW_LAYOUTCLASS";
	private static final String QR_LAYOUTITEM_LITLINIT = "SELECT * FROM VW_LAYOUTITEM WHERE LITLINIT = 'T'";
	private static final String QR_GCOMIMPORT_TEMP_ADD = " INSERT INTO"
			+ " GCOMIMPORT("
			+ "  GCICTIPO, GCIDDATA, GCICCONTR, GCICCPF, GCICCLIEN,"
			+ "  GCICATEN, GCICVEND, GCINPLAN, GCINPROD, GCICTABL,"
			+ "  GCICCRDB, GCICAGEN, GCICESTD, GCIYVALR, GCINPERC,"
			+ "  GCICTEXT)" + " VALUES(" + "  ?,  ?,  ?,  ?,  ?,  ?,"
			+ "  ?,  ?,  ?,  ?,  ?,  ?," + "  ?,  ?,  ?,  ?)";

	public List<BeanLayoutitem> getLayoutitems() {
		List<BeanLayoutitem> l = null;
		try {
			PreparedStatement st = Banco.getConnection().prepareStatement(
					QR_LAYOUTITEM);
			l = Utils.getObjectsStr(st, BeanLayoutitem.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return l;
	}

	public List<BeanLayoutitem> getLayoutitems(String tipo) {
		List<BeanLayoutitem> l = null;
		try {
			PreparedStatement st = Banco.getConnection().prepareStatement(
					QR_LAYOUTITEM_LITNIDTIP);
			st.setString(1, tipo);
			l = Utils.getObjectsStr(st, BeanLayoutitem.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return l;
	}

	public List<BeanLayoutitem> getLayoutitemsLitlinit() {
		List<BeanLayoutitem> l = null;
		try {
			PreparedStatement st = Banco.getConnection().prepareStatement(
					QR_LAYOUTITEM_LITLINIT);
			l = Utils.getObjectsStr(st, BeanLayoutitem.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return l;
	}

	public List<BeanLayoutitem> getLayoutclasses() {
		List<BeanLayoutitem> l = null;
		try {
			PreparedStatement st = Banco.getConnection().prepareStatement(
					QR_LAYOUTCLASS);
			l = Utils.getObjectsStr(st, BeanLayoutitem.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return l;
	}

	public void setGcomimport(PreparedStatement st, BeanGcomimport gcomimport)
			throws SQLException {
		setString(st, 1, gcomimport.getGcictipo());
		setDate(st, 2, new java.sql.Date(gcomimport.getGciddata().getTime()));
		setString(st, 3, gcomimport.getGciccontr());
		setString(st, 4, gcomimport.getGciccpf());
		setString(st, 5, gcomimport.getGcicclien());
		setString(st, 6, gcomimport.getGcicaten());
		setString(st, 7, gcomimport.getGcicvend());
		setInt(st, 8, gcomimport.getGcinplan());
		setInt(st, 9, gcomimport.getGcinprod());
		setString(st, 10, gcomimport.getGcictabl());
		setString(st, 11, gcomimport.getGciccrdb());
		setString(st, 12, gcomimport.getGcicagen());
		setString(st, 13, gcomimport.getGcicestd());
		setDouble(st, 14, gcomimport.getGciyvalr());
		setDouble(st, 15, gcomimport.getGcinperc());
		setString(st, 16, gcomimport.getGcictext());
	}

	public void addGcomimport(BeanGcomimport gcomimport) {
		PreparedStatement st = null;
		try {
			st = Banco.getConnection().prepareStatement(QR_GCOMIMPORT_TEMP_ADD);
			setGcomimport(st, gcomimport);
			st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (st != null && st.isClosed()) {
					st.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void addGcomimports(List<BeanGcomimport> gcomimport) {
		PreparedStatement st = null;
		try {
			st = Banco.getConnection().prepareStatement(QR_GCOMIMPORT_TEMP_ADD);
			for (BeanGcomimport contrato : gcomimport) {
				setGcomimport(st, contrato);
				st.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (st != null && st.isClosed()) {
					st.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private void setTipo(PreparedStatement st, int posicao, Object obj, int tipo)
			throws SQLException {
		if (obj == null) {
			st.setNull(posicao, tipo);
		} else {
			st.setObject(posicao, obj);
		}
	}

	private void setString(PreparedStatement st, int posicao, String obj)
			throws SQLException {
		setTipo(st, posicao, obj, Types.VARCHAR);
	}

	private void setDate(PreparedStatement st, int posicao, java.sql.Date obj)
			throws SQLException {
		setTipo(st, posicao, obj, Types.DATE);
	}

	private void setInt(PreparedStatement st, int posicao, Integer obj)
			throws SQLException {
		setTipo(st, posicao, obj, Types.INTEGER);
	}

	private void setDouble(PreparedStatement st, int posicao, Double obj)
			throws SQLException {
		setTipo(st, posicao, obj, Types.DOUBLE);
	}

}
