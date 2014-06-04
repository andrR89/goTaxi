package urrghsoft.gotaxi.manager.tablemodel;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import urrghsoft.gotaxi.taxigoweb.entidades.Corrida;

public class CorridaTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 3230353210308724872L;
	private List<Corrida> corridasList;
	private String[] colunas = {"ID corrida", "Passageiro", "Hora solicitacao", "Endereco", "Unidade de atendimento"};
	
	public CorridaTableModel(List<Corrida> corridas) {
		if(null == corridas){
			corridasList = new ArrayList<Corrida>();
		}else{
			corridasList = corridas;
		}
		fireTableDataChanged();
	}
	 public void update(List<Corrida> lista){
		 corridasList.clear();
		 corridasList.addAll(lista);
		 fireTableDataChanged();
	 }
	
	@Override
	public int getColumnCount() {
		return colunas.length;
	}
	@Override
	public int getRowCount() {
		return corridasList.size();
	}

	@Override
	public Object getValueAt(int row, int column) {
		try{
			if(column == 0){
				 return corridasList.get(row).getId();
			}
			else if(column == 1){
				 return corridasList.get(row).getPassageiro();
			}
			else if(column == 2){
				return corridasList.get(row).getHoraSolicitacao();
			} 
			else if(column == 3){
				return corridasList.get(row).getLogradouro();
			} 
			else if(column == 4){
				return corridasList.get(row).getUnidade(); 
			} 
		}catch(NullPointerException e){
			return "Valor nulo";
		}
		return "Coluna invalida";
	}
	
	public Corrida getValueAt(int row) {
		return corridasList.get(row);
	}
	
	
	@Override
	public String getColumnName(int column) {
		return colunas[column];
	}
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		try{
			return getValueAt(0, columnIndex).getClass();
		}catch(NullPointerException e){
			return String.class;
		}
	}
	

}
