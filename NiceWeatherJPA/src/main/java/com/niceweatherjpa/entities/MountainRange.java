package com.niceweatherjpa.entities;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "mountain_range")
public class MountainRange {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "parent_id")
	private MountainRange parent;

	@OneToMany(mappedBy = "parent")
	@LazyCollection(LazyCollectionOption.FALSE)
	private Set<MountainRange> subranges;
	
	@JsonIgnore
	@OneToMany(mappedBy = "mountainRange")
	@LazyCollection(LazyCollectionOption.FALSE)
	private Set<Location> locations;

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
	
	public Integer getParentId() {
		if (parent != null) {
			return parent.getId();
		} else {
			return null;
		}
	}
	
	public String getParentName() {
		if (parent != null) {
			return parent.getName();
		} else {
			return "NO PARENT";
		}
	}

	public Set<MountainRange> getSubranges() {
		return subranges;
	}

	public void setSubranges(Set<MountainRange> subranges) {
		this.subranges = subranges;
	}

	public void addSubrange(MountainRange mountainRange) {
		if (subranges == null) {
			subranges = new LinkedHashSet<>();
		}
		if (!subranges.contains(mountainRange)) {
			subranges.add(mountainRange);
			mountainRange.setParent(this);
		}
	}

	public void removeSubrange(MountainRange mountainRange) {
		if (mountainRange != null && subranges.contains(mountainRange)) {
			subranges.remove(mountainRange);
			if (mountainRange.getParent() != null && mountainRange.getParent().equals(this)) {
				mountainRange.setParent(null);
			}
		}
	}
	
	public Integer getLocationCount() {
		if (locations != null && locations.size() > 0) {
			return locations.size();
		} else {
			return null;
		}
	}
	
	public Set<Location> getLocations() {
		return locations;
	}

	public void setLocations(Set<Location> locations) {
		this.locations = locations;
	}
	
	public void addLocation(Location location) {
		if (locations == null) {
			locations = new LinkedHashSet<>();
		}
		if (!locations.contains(location)) {
			locations.add(location);
			location.setMountainRange(this);
		}
	}
	
	public void removeLocation(Location location) {
		if (location != null && locations.contains(location)) {
			locations.remove(location);
			if (location.getMountainRange() != null && location.getMountainRange().equals(this)) {
				location.setMountainRange(null);
			}
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
		
		// if MountainRange has parent
		if (parent != null) {
			// print name and id of parent
			builder.append("\nParent: ");
			builder.append(parent.getName());
			builder.append(" (id: " + parent.getId() + ")");
		} else {
			builder.append("\nNO PARENT");
		}
		
		// if MountainRange has subranges
		if (subranges != null && subranges.size() > 0) {
			// print name and id for each subrange
			Iterator<MountainRange> it = subranges.iterator();
			int count = 0;
			
			while (it.hasNext()) {
				MountainRange subrange = it.next();
				count++;
				
				builder.append("\nSubrange " + count + ": ");
				builder.append(subrange.getName());
				builder.append(" (id: " + subrange.getId() + ")");
			}
		} else {
			builder.append("\nNO SUB RANGES");
		}
		
		builder.append("\n*** END MountainRange ***");
		return builder.toString();
	}

}
