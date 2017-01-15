package br.com.singularideas.labs.knowhub.client.aboriginal.gui.util;

import java.util.List;

import javax.swing.DefaultListModel;

import br.com.singularideas.labs.knowhub.common.data.Item;

public class ItemListModel extends DefaultListModel<Item> {
	
	private static final long serialVersionUID = 1L;

	public ItemListModel(List<Item> items) {
        super();
        
        for (Item i : items) {
        		addElement(i);
        }
    }
 
}
