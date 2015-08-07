package py.edu.uca.test.web.dto;

import java.sql.Date;


public class ParameterDTO {
	private Long id;
    private boolean active;
    private String description;
    private String label;
    private Integer type;
    private String value;
    private Long meta;
    private String imgurl;
    private Date finEstado;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Long getMeta() {
		return meta;
	}
	public void setMeta(Long meta) {
		this.meta = meta;
	}
	public String getImgurl() {
		return imgurl;
	}
	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}
	public Date getFinEstado() {
		return finEstado;
	}
	public void setFinEstado(Date fin_estado) {
		this.finEstado = fin_estado;
	}
    
}
