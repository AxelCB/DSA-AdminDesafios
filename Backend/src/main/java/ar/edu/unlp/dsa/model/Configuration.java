package ar.edu.unlp.dsa.model;

import javax.persistence.*;

@Entity
public class Configuration {
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="configuration_id_seq")
    @SequenceGenerator(name="configuration_id_seq", sequenceName="configuration_id_seq", allocationSize=1)
    private Long id;

    private String name;
    
    private String value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Configuration(String name) {
		this.name = name;
	}

	public Configuration() {
	}

}
