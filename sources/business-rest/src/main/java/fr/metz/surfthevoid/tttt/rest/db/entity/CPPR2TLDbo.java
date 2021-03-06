package fr.metz.surfthevoid.tttt.rest.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(
	name="CPPR_2_TL",
	uniqueConstraints={@UniqueConstraint(name="CPPR_2_TL_UK", columnNames={"CPPR_ID","TL_ID","POSITION"})})
public class CPPR2TLDbo extends GenericDbo {
	
	@Column(name="NEG", nullable=false)
	private boolean negative;
	
	@ManyToOne
	@JoinColumn(name="CPPR_ID" ,referencedColumnName="ID")
	private CompiledPeriodDbo cmpPeriod;
	
	@ManyToOne
	@JoinColumn(name="TL_ID" ,referencedColumnName="ID")
	private TimelineDbo timeline;
	
	@Column(name="POSITION", nullable=false)
	private Integer order;

	public boolean getNegative() {
		return negative;
	}

	public void setNegative(boolean negative) {
		this.negative = negative;
	}

	public CompiledPeriodDbo getCmpPeriod() {
		return cmpPeriod;
	}

	public void setCmpPeriod(CompiledPeriodDbo cmpPeriod) {
		this.cmpPeriod = cmpPeriod;
	}

	public TimelineDbo getTimeline() {
		return timeline;
	}

	public void setTimeline(TimelineDbo timeline) {
		this.timeline = timeline;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}
}
