package com.niceweatherjpa.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "mountain_range")
public class MountainRange {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;
	
//	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "parent_id")
	private MountainRange parent;
	
	@JsonIgnore
	@OneToMany(mappedBy = "parent", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<MountainRange> subranges;
	
	@JsonIgnore
	@OneToMany(mappedBy = "mountainRange", fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	private List<Location> locations;

	public MountainRange() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public MountainRange getParent() {
		return parent;
	}

	public void setParent(MountainRange parent) {
		this.parent = parent;
	}

	public List<MountainRange> getSubranges() {
		return subranges;
	}

	public void setSubranges(List<MountainRange> subranges) {
		this.subranges = subranges;
	}

	public void addSubrange(MountainRange mountainRange) {
		if (subranges == null) {
			subranges = new ArrayList<>();
		}
		if (!subranges.contains(mountainRange)) {
			subranges.add(mountainRange);
			mountainRange.setParent(this);
		}
	}

	public void removeSubrange(MountainRange mountainRange) {
		if (mountainRange != null && subranges.contains(mountainRange)) {
			subranges.remove(mountainRange);
			mountainRange.setParent(null);
		}
	}

	public List<Location> getLocations() {
		return locations;
	}

	public void setLocations(List<Location> locations) {
		this.locations = locations;
	}

	public void addLocation(Location location) {
		if (locations == null) {
			locations = new ArrayList<>();
		}
		if (!locations.contains(location)) {
			locations.add(location);
			location.setMountainRange(this);
		}
	}

	public void removeLocation(Location location) {
		if (location != null && locations.contains(location)) {
			locations.remove(location);
			location.setMountainRange(null);
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MountainRange other = (MountainRange) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("\n*** MountainRange ***\nid=");
		builder.append(id);
		builder.append("\nname=");
		builder.append(name);
		if (parent != null) {
			builder.append("\nparent.getId()=");
			builder.append(parent.getId());
		} else {
			builder.append("\nNO PARENT");
		}
		if (subranges != null && subranges.size() > 0) {
			builder.append("\nsubranges.size()=");
			builder.append(subranges.size());
		} else {
			builder.append("\nNO SUB RANGES");
		}
		if (locations != null && locations.size() > 0) {
			builder.append("\nlocations.size()=");
			builder.append(locations.size());
		} else {
			builder.append("\nNO LOCATIONS");
		}
		builder.append("\n*** END MountainRange ***");
		return builder.toString();
	}

}
