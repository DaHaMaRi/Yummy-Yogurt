package entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/*create table Adresse(
	    ID          int         primary key,
	    Strasse     varchar(64) not null,
	    Hausnummer  varchar(64) not null,
	    Zusatz      varchar(64),
	    PLZ         varchar(64) not null,
	    Ort         varchar(64) not null
);*/

@Entity
@Table(name = "Adresse")
@NamedQuery(name = "Address.listAll", query = "select a from Address a")
public final class Address implements Serializable {

	private static final long serialVersionUID = 1816467445814784831L;
	private static int numberOfAddresses = 20;

	
	@Id
	@Column(name = "ID")
	private int addressID;

	@Column(name = "Strasse", nullable = false)
	private String streetname;

	@Column(name = "Hausnummer", nullable = false)
	private String streetnumber;

	@Column(name = "Zusatz")
	private String additional;

	@Column(name = "PLZ", nullable = false)
	private String postalCode;

	@Column(name = "Ort", nullable = false)
	private String city;

	
	public Address() {
	}

	public Address(final String streetname, final String streetnumber, final String additional, 
				   final String postalCode, final String city) {

		this.addressID = numberOfAddresses;
		this.streetname = streetname;
		this.streetnumber = streetnumber;
		this.additional = additional;
		this.postalCode = postalCode;
		this.city = city;
		numberOfAddresses++;
	}

	public Address(final int id, final String streetname, final String streetnumber, 
				   final String additional, final String postalCode, final String city) {

		this.addressID = id;
		this.streetname = streetname;
		this.streetnumber = streetnumber;
		this.additional = additional;
		this.postalCode = postalCode;
		this.city = city;
	}

	
	@Override
	public String toString() {
		return "Address [addressID=" + addressID + ", streetname=" + streetname + ", streetnumber=" + streetnumber
				+ ", additional=" + additional + ", postalCode=" + postalCode + ", city=" + city + "]";
	}

	@Override
	public boolean equals(Object object) {
		if (object == null)
			return false;
		if (this == object)
			return true;

		if (this.getClass() != object.getClass())
			return false;

		Address other = (Address) object;
		return Objects.equals(this.addressID, other.getID()) && Objects.equals(this.streetname, other.getStreetname())
				&& Objects.equals(this.streetnumber, other.getStreetnumber())
				&& Objects.equals(this.additional, other.getAdditional())
				&& Objects.equals(this.postalCode, other.getPostalCode()) && Objects.equals(this.city, other.getCity());
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.addressID, this.streetname, this.streetnumber, this.additional, this.postalCode,
				this.city);
	}

	
	public int getID() {
		return addressID;
	}

	public String getStreetname() {
		return streetname;
	}

	public String getStreetnumber() {
		return streetnumber;
	}

	public String getAdditional() {
		return additional;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public String getCity() {
		return city;
	}

}
