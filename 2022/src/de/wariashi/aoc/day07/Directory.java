package de.wariashi.aoc.day07;

import java.util.ArrayList;
import java.util.List;

public class Directory {
	private final List<Data> files = new ArrayList<>();
	private final String name;
	private final Directory parent;
	private final List<Directory> subfolders = new ArrayList<>();

	public Directory(String name, Directory parent) {
		this.name = name;
		this.parent = parent;
	}

	public void addData(Data data) {
		files.add(data);
	}

	public void addDirectory(Directory directory) {
		subfolders.add(directory);
	}

	public Directory getChild(String name) {
		return subfolders.stream().filter(dir -> dir.name.equals(name)).findAny().orElse(null);
	}

	public List<Data> getFiles() {
		return files;
	}

	public List<Directory> getSubfolders() {
		return subfolders;
	}

	public Directory getParent() {
		return parent;
	}

	public int getSize() {
		var size = 0;

		// add sum of files
		for (var file : files) {
			size += file.getSize();
		}

		// add subfolders
		for (var subfolder : subfolders) {
			size += subfolder.getSize();
		}

		return size;
	}

	public String toString() {
		return name;
	}
}
