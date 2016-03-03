package com.levins.my.contact.search;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Queue;

import com.levins.my.contact.ContactRecord;

public class ReadWriteModel {
	private List<ContactRecord> listOfCOntact;

	public List<ContactRecord> getListOfAnimal() {
		return listOfCOntact;
	}

	public void setListOfAnimal(List<ContactRecord> singleLine) {
		this.listOfCOntact = singleLine;
	}

	public static void writeNewFile(Queue<String> sorceToWrite,
			String outputFile) throws IOException {
		try (BufferedWriter bufferWrite = new BufferedWriter(new FileWriter(
				outputFile))) {
			while (sorceToWrite.size() > 0) {
				bufferWrite.write(sorceToWrite.poll());
			}
		}
	}

}
