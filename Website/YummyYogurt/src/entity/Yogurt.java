package entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;


/*create table Yogurt(
	    ID              int          primary key,
	    Name            varchar(64)  unique not null,
	    BenutzerID      int          not null,
	    Veröffentlicht  varchar(8)   not null,
);*/

@Entity
@NamedQuery(name="Yogurt.listAll", query="select y from Yogurt y")
public class Yogurt implements Serializable {
	
	private static final long serialVersionUID = -6134440095691454868L;

	@Id
	@Column(name="ID")
	private int yogurtID;
	
	@Column(name="Name", unique=true, nullable=false)
	private String name;
	
	@ManyToOne
	@JoinColumn(name="BenutzerID", nullable=false)
	private User owner;
	
	@Column(name="Veröffentlicht", nullable=false)
	private String visibility;
	
	@ManyToMany
	@JoinTable(name="Zutatenliste", joinColumns={@JoinColumn(name="YogurtID")}, 
		inverseJoinColumns={@JoinColumn(name="ZutatenID")})
	private List<Ingredient> recipe;

	public Yogurt() {}

	public Yogurt(int yogurtID, String name, User owner, String visibility) {
		this.yogurtID = yogurtID;
		this.name = name;
		this.owner = owner;
		this.visibility = visibility;
	}
	
	@Override
	public String toString() {
		return "Yogurt [yogurtID=" + yogurtID + ", name=" + name + ", visibility=" + visibility + "\n\towner=" + owner
				+ "\n\trecipe=" + recipe + "]";
	}

	public int getYogurtID() {
		return yogurtID;
	}

	public void setYogurtID(int yogurtID) {
		this.yogurtID = yogurtID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isVisible() {
		Boolean visibility = new Boolean(this.visibility);
		return visibility.booleanValue();
	}

	public void setVisibility(boolean visibility) {
		Boolean temp = new Boolean(visibility);
		this.visibility = temp.toString();
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public List<Ingredient> getRecipe() {
		return recipe;
	}

	public void setRecipe(List<Ingredient> recipe) {
		this.recipe = recipe;
	}

}
