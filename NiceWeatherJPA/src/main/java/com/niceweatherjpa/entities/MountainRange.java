package com.niceweatherjpa.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "mountain_range")
public class MountainRange {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;

	@ManyToOne
	@JoinColumn(name = "parent_id")
	private MountainRange parent;

	@OneToMany(mappedBy = "parent")
	private List<MountainRange> subRangeList;

	@OneToMany(mappedBy = "mountainRange")
	private List<Location> locationList;

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

	public List<MountainRange> getSubRangeList() {
		return subRangeList;
	}

	public void setSubRangeList(List<MountainRange> subRangeList) {
		this.subRangeList = subRangeList;
	}

	public void addSubRange(MountainRange mountainRange) {
		if (subRangeList == null) {
			subRangeList = new ArrayList<>();
		}
		if (!subRangeList.contains(mountainRange)) {
			subRangeList.add(mountainRange);
			mountainRange.setParent(this);
		}
	}

	public void removeSubRange(MountainRange mountainRange) {
		if (mountainRange != null && subRangeList.contains(mountainRange)) {
			subRangeList.remove(mountainRange);
			mountainRange.setParent(null);
		}
	}

	public List<Location> getLocationList() {
		return locationList;
	}

	public void setLocationList(List<Location> locationList) {
		this.locationList = locationList;
	}

	public void addLocation(Location location) {
		if (locationList == null) {
			locationList = new ArrayList<>();
		}
		if (!locationList.contains(location)) {
			locationList.add(location);
			location.setMountainRange(this);
		}
	}

	public void removeLocation(Location location) {
		if (location != null && locationList.contains(location)) {
			locationList.remove(location);
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
		if (subRangeList != null && subRangeList.size() > 0) {
			builder.append("\nsubRangeList.size()=");
			builder.append(subRangeList.size());
		} else {
			builder.append("\nNO SUB RANGES");
		}
		if (locationList != null && locationList.size() > 0) {
			builder.append("\nlocationList.size()=");
			builder.append(locationList.size());
		} else {
			builder.append("\nNO LOCATIONS");
		}
		builder.append("\n*** END MountainRange ***");
		return builder.toString();
	}

}
