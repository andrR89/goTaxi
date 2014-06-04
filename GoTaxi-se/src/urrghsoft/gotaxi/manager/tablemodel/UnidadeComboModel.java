package urrghsoft.gotaxi.manager.tablemodel;

import java.util.List;

import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;

import urrghsoft.gotaxi.taxigoweb.entidades.Unidade;

public class UnidadeComboModel implements ComboBoxModel{
	
	private List<Unidade> unidadeList = null;
	private Unidade unidade;
	public UnidadeComboModel(List<Unidade> list) {
		unidadeList = list;
	}
	

	@Override
	public void addListDataListener(ListDataListener arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object getElementAt(int arg0) {
		return unidadeList.get(arg0);
	}

	@Override
	public int getSize() {
		return unidadeList.size();
	}

	@Override
	public void removeListDataListener(ListDataListener arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object getSelectedItem() {
		return unidade;
	}

	@Override
	public void setSelectedItem(Object arg0) {
		unidade = (Unidade) arg0;
	}

}
