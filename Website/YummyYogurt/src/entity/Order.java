package entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/*create table Bestellung(
	    ID           int  primary key,
	    BenutzerID   int  not null,
	    Gesamtpreis  int  not null,
	    Bestelldatum timestamp not null,
	    constraint checkGesamtpreis check(Gesamtpreis > 0)
);*/

@Entity
@Table(name="Bestellung")
public class Order implements Serializable {
	
	private static final long serialVersionUID = 1019596392287525485L;

	@Id
	@Column(name="ID")
	private int orderID;
	
	@ManyToOne
	@JoinColumn(name="BenutzerID", nullable=false)
	private User user;
	
	@Column(name="Gesamtpreis", nullable=false)
	private int totalPrice;
	
	@Column(name="Bestelldatum", nullable=false)
	private LocalDateTime orderdate;

	public Order() {}

	public Order(int orderID, User user, int totalPrice, LocalDateTime orderdate) {
		this.orderID = orderID;
		this.user = user;
		this.totalPrice = totalPrice;
		this.orderdate = orderdate;
	}

	@Override
	public String toString() {
		return "Order [orderID=" + orderID + ", user=" + user + ", totalPrice=" + totalPrice + ", orderdate="
				+ orderdate + "]";
	}

	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public LocalDateTime getOrderdate() {
		return orderdate;
	}

	public void setOrderdate(LocalDateTime orderdate) {
		this.orderdate = orderdate;
	}

}
