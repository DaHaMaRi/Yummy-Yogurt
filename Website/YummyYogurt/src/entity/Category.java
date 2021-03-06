package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;


/*create table Zutatenkategorie(
	    ID    int         primary key,
	    Name  varchar(64) unique not null,
	    Preis int         not null,
	    constraint checkPreis check(Preis > 0)
);*/

@Entity
@Table(name="Zutatenkategorie")
@NamedQuery(name="Category.listAll", query="select c from Category c")
public final class Category implements Serializable {
	
	private static final long serialVersionUID = 7610617292935816641L;

	
	@Id
	@Column(name="ID")
	private int categoryID;
	
	@Column(name="Name", unique=true, nullable=false)
	private String name;
	
	@Column(name="preis", nullable=false)
	private int priceInCents;
	
	
	public Category() {}

	public Category(final int categoryID, final String name, final int priceInCents) {
		this.categoryID = categoryID;
		this.name = name;
		this.priceInCents = priceInCents;
	}

	
	@Override
	public String toString() {
		return "Category [categoryID=" + categoryID + ", name=" + name + ", priceInCents=" + priceInCents + "]";
	}

	@Override
	public boolean equals(Object object) {
		if(object == null) return false;
		if(this == object) return true;
		
		if(this.getClass() != object.getClass())
			return false;
		
		Category other = (Category) object;
		return Objects.equals(this.categoryID, other.getID())
			&& Objects.equals(this.name, other.getName());
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.categoryID, this.name);
	}

	
	public int getID() {
		return categoryID;
	}

	public String getName() {
		return name;
	}

	public int getPriceInCents() {
		return priceInCents;
	}

	public void setPriceInCents(final int priceInCents) {
		this.priceInCents = priceInCents;
	}
	
}
