package entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/*create table Zutatenkategorie(
	    ID    int         primary key,
	    Name  varchar(64) unique not null,
	    Preis int         not null,
	    constraint checkPreis check(Preis > 0)
);*/

@Entity
@Table(name="Zutatenkategorie")
@NamedQuery(name="Category.listAll", query="select c from Category c")
public class Category implements Serializable {
	
	private static final long serialVersionUID = 7610617292935816641L;

	@Id
	@Column(name="ID")
	private int categoryID;
	
	@Column(name="Name", unique=true, nullable=false)
	private String name;
	
	@Column(name="preis", nullable=false)
	private int priceInCents;
	
	public Category() {}

	public Category(int categoryID, String name, int priceInCents) {
		this.categoryID = categoryID;
		this.name = name;
		this.priceInCents = priceInCents;
	}

	@Override
	public String toString() {
		return "IngredientCategory [categoryID=" + categoryID + ", name=" + name 
					+ ", priceInCents=" + priceInCents + "]";
	}

	public int getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPriceInCents() {
		return priceInCents;
	}

	public void setPriceInCents(int priceInCents) {
		this.priceInCents = priceInCents;
	}
	
}
