package entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/*create table Bestellposition(
    BestellungID  int,
    YogurtID      int,
    primary key(BestellungID,YogurtID),
    
    Menge         int not null,
    constraint checkMenge check(Menge > 0)
);*/


@Entity
@Table(name="Bestellposition")
public class OrderItem implements Serializable {
	
	private static final long serialVersionUID = -632106431641713377L;

	@EmbeddedId
	private OrderItemID orderItemID;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="BestellungID", insertable=false, updatable=false)
	private Order order;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="YogurtID", insertable=false, updatable=false)
	private Yogurt yogurt;
	
	@Column(name="Menge", nullable=false)
	private int amount;

	public OrderItem() {}

	public OrderItem(Order order, Yogurt yogurt, int amount) {
		this.orderItemID = new OrderItemID(order.getOrderID(), yogurt.getYogurtID());
		this.order = order;
		this.yogurt = yogurt;
		this.amount = amount;
	}

	public OrderItemID getOrderItemID() {
		return orderItemID;
	}

	public void setOrderItemID(OrderItemID orderItemID) {
		this.orderItemID = orderItemID;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Yogurt getYogurt() {
		return yogurt;
	}

	public void setYogurt(Yogurt yogurt) {
		this.yogurt = yogurt;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

}
