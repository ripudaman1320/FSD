package code.yummypizza.FSD.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="menu")
public class Menu {

	public Menu(long id, String pizza, String sides) {
		super();
		this.id = id;
		this.pizza = pizza;
		this.sides = sides;
	}
	
	public Menu() {}
	
	@Id
	private long id;
	
	@Column(name = "pizza")
	private String pizza;
	
	@Column(name = "sides")
	private String sides;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getPizza() {
		return pizza;
	}
	public void setPizza(String pizza) {
		this.pizza = pizza;
	}
	public String getSides() {
		return sides;
	}
	public void setSides(String sides) {
		this.sides = sides;
	}
	
	
}
