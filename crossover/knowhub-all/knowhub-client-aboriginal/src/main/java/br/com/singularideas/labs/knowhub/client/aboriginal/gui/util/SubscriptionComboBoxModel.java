package br.com.singularideas.labs.knowhub.client.aboriginal.gui.util;

import javax.swing.DefaultComboBoxModel;

import br.com.singularideas.labs.knowhub.common.data.Subscription;

public class SubscriptionComboBoxModel extends DefaultComboBoxModel<Subscription> {
	
	private static final long serialVersionUID = 1L;

	public SubscriptionComboBoxModel(Subscription [] subscriptions) {
        super(subscriptions);
    }
 
    @Override
    public Subscription getSelectedItem() {
        Subscription subscription = (Subscription) super.getSelectedItem();
        return subscription;
    }
}
